package hu.interconnect.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hu.interconnect.common.MailSender;
import hu.interconnect.hr.domain.IdozitettFuttatas;

public abstract class AbstractIdozitettFeladat {

	private static final Log LOG = LogFactory.getLog(AbstractIdozitettFeladat.class);

	public static final String CRON_KIFEJEZES_HONAP_15_NAPJAN_14_ORAKOR = "0 0 14 15 1/1 ?";
	public static final String CRON_KIFEJEZES_HONAP_ELSO_NAPJAN_EJFELKOR = "0 0 0 1 1/1 ?";
	public static final String CRON_KIFEJEZES_NAPONTA_EJFELKOR = "0 0 0 1/1 * ?";

	@Autowired
	protected MailSender mailSender;

	@Autowired
	private IdozitettFuttatasEsemenyLetrehozo idozitettFuttatasEsemenyLetrehozo;

	public final String getAzonosito() {
		return getClass().getSimpleName();
	}

	public final void vegrehajtEsEredmenytRogzitEsLeveletKuld() {
		LOG.info("Scheduled task started: " + getAzonosito());
		
		IdozitettFuttatas futtatas;
		try {
			futtatas = idozitettFuttatasEsemenyLetrehozo.futtatastKezd(this);
		} catch (Exception e) {
			LOG.error("Nem sikerult az idozitett feladat futtatas inditasa", e);
			sendMail(e);
			return;
		}
		
		try {
			String eredmeny = feladatotVegrehajt();
			idozitettFuttatasEsemenyLetrehozo.futtatastSikeresenBefejez(futtatas, eredmeny);
		} catch (Exception e) {
			LOG.error("Hiba tortent az idozitett feladat futtatasa kozben", e);
			try {
				idozitettFuttatasEsemenyLetrehozo.futtatastSikertelenulBefejez(futtatas, e);
			} catch (Exception e2) {
				LOG.error("Nem sikerult a sikertelen idozitett feladat futtatas befejezese", e2);
				sendMail(e2);
			}
			sendMail(e);
		}
		LOG.info("Scheduled task finished: " + getAzonosito());
	}

	protected abstract String feladatotVegrehajt();

	public abstract String getCronKifejezes();

	private void sendMail(Exception e) {
		if (mailSender != null) {
			try {
				mailSender.sendVaratlanHibaMail("Varatlan hiba tortent a szerveren - AbstractIdozitettFeladat", e);
			} catch (Exception e2) {
				LOG.error("Sikertelen levelkuldes a sikertelen idozitett feladat futtatasrol", e2);
			}
		} else {
			LOG.fatal("Nincs beallitva a levelkuldes");
		}
	}
}
