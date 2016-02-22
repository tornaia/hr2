package hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static hu.interconnect.util.DateUtils.napFormaz;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.time.DateUtils.addMonths;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import hu.interconnect.common.KuldendoLevel;
import hu.interconnect.hr.domain.OrvosiVizsgalat;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.util.VelocityUtils;

public class SzemelyitorzsbolEsedekesOrvosiVizsgalatLeveletEloallito implements Function<Szemelyitorzs, KuldendoLevel> {

	private String template;

	public SzemelyitorzsbolEsedekesOrvosiVizsgalatLeveletEloallito(String template) {
		this.template = template;
	}

	@Override
	public KuldendoLevel apply(Szemelyitorzs szemelyitorzs) {
		String szemelyitorzsEmail = szemelyitorzs.getSzemelyiAdatok().getEmail();

		OrvosiVizsgalat orvosiVizsgalat = szemelyitorzs.getOrvosiVizsgalat();
		Date utolsoOrvosiVizsgalatIdopontja = orvosiVizsgalat.getUtolsoOrvosiVizsgalatIdopontja();
		int gyakorisag = orvosiVizsgalat.getGyakorisag();
		Date kovetkezoOrvosiVizsgalatIdopontja = addMonths(utolsoOrvosiVizsgalatIdopontja, gyakorisag);
		
		Map<String, Object> adatok = newHashMap();
		adatok.put("kovetkezoOrvosiVizsgalatIdopontja", napFormaz(kovetkezoOrvosiVizsgalatIdopontja));
		
		boolean isSzemelyitorzsEmailFilledOut = isNotBlank(szemelyitorzsEmail);
		adatok.put("isSzemelyitorzsEmailFilledOut", isSzemelyitorzsEmailFilledOut);

		String tartalom = VelocityUtils.generate(template, adatok);

		List<String> cimzettek = newArrayList();
		if (isSzemelyitorzsEmailFilledOut) {
			cimzettek.add(szemelyitorzsEmail);
		}

		return new KuldendoLevel(cimzettek, "Orvosi vizsgálat lejár - " + szemelyitorzs.getTeljesNev(), tartalom);
	}
}