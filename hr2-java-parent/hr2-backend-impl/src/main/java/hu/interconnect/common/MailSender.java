package hu.interconnect.common;

import static hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus.ADMIN_BCC_EMAILCIMEK;
import static hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus.ADMIN_CC_EMAILCIMEK;
import static hu.interconnect.util.ArrayUtils.removeNullOrEmptyElements;
import static java.util.Arrays.asList;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;

import hu.interconnect.hr.dao.RendszerParameterDAO;
import hu.interconnect.hr.domain.RendszerParameter;

public class MailSender {

	private static final Log LOG = LogFactory.getLog(MailSender.class);

	private org.springframework.mail.MailSender internalMailSender;
	private RendszerParameterDAO rendszerParameterDAO;
	private String subjectPrefix;
	private String from;
	private boolean mailDeflect;
	private String mailDeflectTo;

	public void setInternalMailSender(org.springframework.mail.MailSender internalMailSender) {
		this.internalMailSender = internalMailSender;
	}

	public void setRendszerParameterDAO(RendszerParameterDAO rendszerParameterDAO) {
		this.rendszerParameterDAO = rendszerParameterDAO;
	}

	public void setSubjectPrefix(String subjectPrefix) {
		this.subjectPrefix = subjectPrefix;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setMailDeflect(boolean mailDeflect) {
		this.mailDeflect = mailDeflect;
	}
	
	public void setMailDeflectTo(String mailDeflectTo) {
		this.mailDeflectTo = mailDeflectTo;
	}

	public void sendMail(String[] to, String subject, String msg) {
		SimpleMailMessage message = null;
		try {
			message = createMessage(to, subject, msg);
			sendMail(message);
		} catch (Exception e) {
			handleException(e, message);
		}
	}

	public void sendMail(KuldendoLevel kuldendoLevel) {
		sendMail(kuldendoLevel.getCimzettek(), kuldendoLevel.getTargy(), kuldendoLevel.getTartalom());
	}

	public void sendMails(List<KuldendoLevel> kuldendoLevelek) {
		kuldendoLevelek.stream().forEach(this::sendMail);
	}

	public void sendVaratlanHibaMail(String subject, Throwable t) {
		String stackTrace = ExceptionUtils.getFullStackTrace(t);
		SimpleMailMessage message = createMessage(new String[0], subject, stackTrace);
		message.setTo(new String[0]);
		message.setCc(new String[0]);

		sendMail(message);
	}

	private SimpleMailMessage createMessage(String[] to, String subject, String tartalom) {
		String prefixedSubject = subjectPrefix == null || subjectPrefix.isEmpty() ? subject : subjectPrefix + " - " + subject;
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);

		String[] valodiToCimek = removeNullOrEmptyElements(to);
		message.setTo(valodiToCimek);
		message.setSubject(prefixedSubject);
		message.setText(tartalom);

		RendszerParameter ccEmailCimekParam = rendszerParameterDAO.findByTipus(ADMIN_CC_EMAILCIMEK);
		String[] ccEmailCimek = ccEmailCimekParam.getErtek().split(";");
		String[] valodiCcCimek = removeNullOrEmptyElements(ccEmailCimek);
		message.setCc(valodiCcCimek);

		RendszerParameter bccEmailCimekParam = rendszerParameterDAO.findByTipus(ADMIN_BCC_EMAILCIMEK);
		String[] bccEmailCimek = bccEmailCimekParam.getErtek().split(";");
		String[] valodiBccCimek = removeNullOrEmptyElements(bccEmailCimek);
		message.setBcc(valodiBccCimek);

		return message;
	}

	private void handleException(Exception e, SimpleMailMessage message) {
		if (message == null) {
			LOG.error("NULL levelet nem lehet elkuldeni");
			return;
		}

		String messageStr = message.toString();
		LOG.error("Hiba tortent a level kuldese során: " + messageStr, e);

		try {
			String stackTrace = ExceptionUtils.getStackTrace(e);
			message.setTo(new String[0]);
			message.setCc(new String[0]);

			RendszerParameter bccEmailCimekParam = rendszerParameterDAO.findByTipus(ADMIN_BCC_EMAILCIMEK);
			String[] bccEmailCimek = bccEmailCimekParam.getErtek().split(";");
			message.setBcc(bccEmailCimek);

			if (bccEmailCimek.length == 0) {
				LOG.error("Nincs BCC cimzett, igy nem tudok kit ertesiteni a hibarol");
				return;
			}
			message.setText(message.getText() + "\\" + messageStr + "\\n" + stackTrace);
			
			sendMail(message);
		} catch (Exception e2) {
			LOG.fatal("Nem sikerult a level kuldese a sikertelen levelkuldesrol sem", e2);
		}
	}
	
	private void sendMail(SimpleMailMessage message) {
		if (mailDeflect) {
			message.setTo(mailDeflectTo);
			message.setCc(new String[0]);
			message.setBcc(new String[0]);
		}
		LOG.info("Levelkuldés. From: " + message.getFrom() + ", to: " + asList(message.getTo()) + ", cc: " + asList(message.getCc()) + ", bcc: " + asList(message.getBcc()) + ", subject: " + message.getSubject() + ", text: " + message.getText());
		internalMailSender.send(message);
	}
}
