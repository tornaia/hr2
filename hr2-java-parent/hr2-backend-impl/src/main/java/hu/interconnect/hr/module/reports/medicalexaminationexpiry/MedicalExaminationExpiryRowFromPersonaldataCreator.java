package hu.interconnect.hr.module.reports.medicalexaminationexpiry;

import static hu.interconnect.util.DateUtils.getCalendar;
import static hu.interconnect.util.DateUtils.napFormaz;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;

public class MedicalExaminationExpiryRowFromPersonaldataCreator implements Function<Szemelyitorzs, Map<String, Object>> {

	@Override
	public Map<String, Object> apply(Szemelyitorzs szemelyitorzs) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		
		sor.put("nev", szemelyitorzs.getTeljesNev());
		
		String kovetkezoOrvosiVizsgalatIdopontja = "";
		Date utolsoOrvosiVizsgalatIdopontja = szemelyitorzs.getOrvosiVizsgalat().getUtolsoOrvosiVizsgalatIdopontja();
		if (utolsoOrvosiVizsgalatIdopontja != null) {
			Calendar calendar = getCalendar(utolsoOrvosiVizsgalatIdopontja);
			int gyakorisag = szemelyitorzs.getOrvosiVizsgalat().getGyakorisag();
			calendar.add(Calendar.MONTH, gyakorisag);
			kovetkezoOrvosiVizsgalatIdopontja = napFormaz(calendar.getTime());
		}
			
		sor.put("kovetkezo_orvosi_vizsgalat_idopontja", kovetkezoOrvosiVizsgalatIdopontja);
		return sor.getMap();
	}
	
}	
