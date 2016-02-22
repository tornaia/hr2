package hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat;

import static hu.interconnect.util.DateUtils.azonosHonap;
import static org.apache.commons.lang.time.DateUtils.addMonths;

import java.util.Date;
import java.util.function.Predicate;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.domain.OrvosiVizsgalat;
import hu.interconnect.hr.domain.Szemelyitorzs;

public class AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicate implements Predicate<Szemelyitorzs> {

	private Date honap;

	public AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicate(Date honap) {
		this.honap = honap;
	}
	
	@Override
	public boolean test(Szemelyitorzs szemelyitorzs) {
		if (szemelyitorzs.getJogviszonyAdatok().getAllomanymod() != Allomanymod.AKTIV) {
			return false;
		}
		
		OrvosiVizsgalat orvosiVizsgalat = szemelyitorzs.getOrvosiVizsgalat();
		
		int gyakorisag = orvosiVizsgalat.getGyakorisag();
		Date utolsoOrvosiVizsgalatIdopontja = orvosiVizsgalat.getUtolsoOrvosiVizsgalatIdopontja();
		if (utolsoOrvosiVizsgalatIdopontja == null) {
			return false;
		}
		Date kovetkezoOrvosiVizsgalatIdopontja = addMonths(utolsoOrvosiVizsgalatIdopontja, gyakorisag);
		return azonosHonap(honap, kovetkezoOrvosiVizsgalatIdopontja);
	}
}
