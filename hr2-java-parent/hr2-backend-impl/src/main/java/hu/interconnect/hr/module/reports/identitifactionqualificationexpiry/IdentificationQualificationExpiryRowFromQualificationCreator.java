package hu.interconnect.hr.module.reports.identitifactionqualificationexpiry;

import static hu.interconnect.util.DateUtils.isBetween;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.Lists;

import hu.interconnect.hr.domain.Kepzettseg;
import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;

public class IdentificationQualificationExpiryRowFromQualificationCreator implements Function<Szemelyitorzs, List<Map<String, Object>>> {

	private Date kezdet;
	
	private Date veg;

	public IdentificationQualificationExpiryRowFromQualificationCreator(Date kezdet, Date veg) {
		this.kezdet = kezdet;
		this.veg = veg;
	}
	
	@Override
	public List<Map<String, Object>> apply(Szemelyitorzs szemelyitorzs) {
		List<Map<String, Object>> szemelyitorzsSorok = Lists.newArrayList();
		
		String teljesNev = szemelyitorzs.getTeljesNev();
		SzemelyiAdatok szemelyiAdatok = szemelyitorzs.getSzemelyiAdatok();
		
		Date szemelyiIgazolvanySzamLejarat = szemelyiAdatok != null ? szemelyiAdatok.getSzemelyiIgazolvanySzamLejarat() : null;
		if (isBetween(szemelyiIgazolvanySzamLejarat, kezdet, veg)) {
			szemelyitorzsSorok.add(createRow(teljesNev, "személyi igazolvány", szemelyiIgazolvanySzamLejarat).getMap());
		}

		Date utlevelSzamLejarat = szemelyiAdatok != null ? szemelyiAdatok.getUtlevelSzamLejarat() : null;
		if (isBetween(utlevelSzamLejarat, kezdet, veg)) {
			szemelyitorzsSorok.add(createRow(teljesNev, "útlevél", utlevelSzamLejarat).getMap());
		}

		Date jogositvanyLejarat = szemelyiAdatok != null ? szemelyiAdatok.getJogositvanySzamLejarat() : null;
		if (isBetween(jogositvanyLejarat, kezdet, veg)) {
			szemelyitorzsSorok.add(createRow(teljesNev, "jogosítvány", jogositvanyLejarat).getMap());
		}

		for (Kepzettseg kepzettseg : szemelyitorzs.getKepzettsegek()) {
			if (isBetween(kepzettseg.getErvenyessegVege(), kezdet, veg)) {
				szemelyitorzsSorok.add(createRow(teljesNev, kepzettseg.getMegnevezes(), kepzettseg.getErvenyessegVege()).getMap());
			}
		}
		
		return szemelyitorzsSorok;
	}
	
	private static NullValueToEmptyStringMap createRow(String nev, String megnevezes, Date ervenyessegVege) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		sor.put("nev", nev);
		sor.put("megnevezes", megnevezes);
		sor.putDate("ervenyesseg_vege", ervenyessegVege);
		return sor;
	}
}
