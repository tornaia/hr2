package hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat;

import static org.apache.commons.io.IOUtils.toInputStream;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.interconnect.ftp.UFTPClient;
import hu.interconnect.ftp.UFTPParameters;
import hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus;
import hu.interconnect.hr.dao.RendszerParameterDAO;
import hu.interconnect.hr.domain.RendszerParameter;
import hu.interconnect.hr.module.scheduledtasks.KuldendoSMSAllomany;

@Component
public class SMSKuldo {

	private static final Log LOG = LogFactory.getLog(SMSKuldo.class);

	@Autowired
	private RendszerParameterDAO rendszerParameterDAO;

	public void feltolt(KuldendoSMSAllomanyok kuldendoSMSAllomanyok) throws IOException {
		RendszerParameter ftpSMSHost = rendszerParameterDAO.findByTipus(RendszerParameterTipus.FTP_SMS_HOSZT);
		RendszerParameter ftpSMSPort = rendszerParameterDAO.findByTipus(RendszerParameterTipus.FTP_SMS_PORT);
		RendszerParameter ftpSMSFelhasznalonev = rendszerParameterDAO.findByTipus(RendszerParameterTipus.FTP_SMS_FELHASZNALONEV);
		RendszerParameter ftpSMSJelszo = rendszerParameterDAO.findByTipus(RendszerParameterTipus.FTP_SMS_JELSZO);

		String hoszt = ftpSMSHost.getErtek();
		int port = Integer.parseInt(ftpSMSPort.getErtek());
		String felhasznalonev = ftpSMSFelhasznalonev.getErtek();
		String jelszo = ftpSMSJelszo.getErtek();

		UFTPClient ftpClient = new UFTPClient(new UFTPParameters(hoszt, port, felhasznalonev, jelszo));

		try {
			ftpClient.connect();

			for (KuldendoSMSAllomany kuldendoSMSAllomany : kuldendoSMSAllomanyok) {
				LOG.info("Megprobalom elkuldeni: " + kuldendoSMSAllomany.toString());
				String filenev = kuldendoSMSAllomany.getFilenev();
				InputStream tartalom = toInputStream(kuldendoSMSAllomany.getTartalom());
				ftpClient.store(filenev, tartalom);
			}
		} catch (IOException e) {
			LOG.error("Hiba tortent az SMS allomany FTP-re torteno masolasa soran!", e);
			throw e;
		} finally {
			ftpClient.logout();
		}
	}
}
