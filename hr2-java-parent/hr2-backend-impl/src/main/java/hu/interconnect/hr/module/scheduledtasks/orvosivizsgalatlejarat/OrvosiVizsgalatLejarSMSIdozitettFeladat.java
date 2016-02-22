package hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat;

import static hu.interconnect.util.DateUtils.aktualisIdo;
import static org.apache.commons.lang.time.DateUtils.addMonths;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.scheduler.AbstractIdozitettFeladat;

@Component
public class OrvosiVizsgalatLejarSMSIdozitettFeladat extends AbstractIdozitettFeladat {

	@Autowired
	private KovetkezoHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsekMeghatarozo kovetkezoHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsekMeghatarozo;

	@Autowired
	private SMSKuldo smsKuldo;

	@Scheduled(cron = CRON_KIFEJEZES_HONAP_15_NAPJAN_14_ORAKOR)
	public void leveletKuld() {
		vegrehajtEsEredmenytRogzitEsLeveletKuld();
	}

	@Override
	public String getCronKifejezes() {
		return CRON_KIFEJEZES_HONAP_15_NAPJAN_14_ORAKOR;
	}

	@Override
	protected String feladatotVegrehajt() {
		Date kovetkezoHonap = addMonths(aktualisIdo(), 1);

		Iterable<Szemelyitorzs> kovetkezoHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsek = kovetkezoHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsekMeghatarozo.getAdottHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsek(kovetkezoHonap);

		KuldendoSMSAllomanyok kuldendoSMSAllomanyok = new SzemelyitorzsekbolEsedekesOrvosiVizsgalatSMSAllomanyokatEloallito(kovetkezoHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsek).letrehoz();

		if (kuldendoSMSAllomanyok.isEmpty()) {
			return "Nincs mit küldeni!";
		}
		
		try {
			smsKuldo.feltolt(kuldendoSMSAllomanyok);
		} catch (IOException e) {
			return "Hiba! Nem sikerült az állományt FTP-re másolni: " + e.toString();
		}

		return String.format("Tartalom: %s", kuldendoSMSAllomanyok.toString());
	}
}
