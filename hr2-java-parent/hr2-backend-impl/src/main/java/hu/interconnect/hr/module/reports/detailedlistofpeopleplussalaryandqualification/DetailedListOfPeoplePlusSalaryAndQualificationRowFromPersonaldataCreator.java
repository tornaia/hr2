package hu.interconnect.hr.module.reports.detailedlistofpeopleplussalaryandqualification;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.sort;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.google.common.collect.Lists;

import hu.interconnect.hr.domain.Kepzettseg;
import hu.interconnect.hr.domain.MunkakoriHistorikusAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;
import hu.interconnect.hr.module.reports.personaldata.PersonalDataRowFromPersonalDataCreator;

public class DetailedListOfPeoplePlusSalaryAndQualificationRowFromPersonaldataCreator implements Function<Szemelyitorzs, List<Map<String, Object>>> {

	@Override
	public List<Map<String, Object>> apply(Szemelyitorzs szemelyitorzs) {
		List<Map<String, Object>> sorok = newArrayList();
		
		List<Kepzettseg> kepzettsegek = newArrayList(szemelyitorzs.getKepzettsegek() != null ? szemelyitorzs.getKepzettsegek() : Lists.<Kepzettseg>newArrayList());
		sort(kepzettsegek, new KepzettsegEvComparator());
		List<MunkakoriHistorikusAdat> munkakoriHistorikusAdatok = newArrayList(szemelyitorzs.getMunkakoriBesorolas().getMunkakoriHistorikusAdatok());
		sort(munkakoriHistorikusAdatok, new MunkakoriHistorikusAdatDatumComparator());
		
		for (int i=0;i<1 || i<kepzettsegek.size() || i<munkakoriHistorikusAdatok.size();i++) {
			NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
			
			sor.getMap().putAll(new PersonalDataRowFromPersonalDataCreator().apply(szemelyitorzs));
			
			sor.put("kepzettseg_tipus", null);
			sor.put("kepzettseg_megnevezes", null);
			sor.put("kepzettseg_mod_fok", null);
			sor.putDateWithYear("kepzettseg_ev", null);
			sor.putDate("kepzettseg_ervenyesseg_vege", null);
			sor.put("kepzettseg_megjegyzes", null);
			if (i < kepzettsegek.size()) {
				Kepzettseg kepzettseg = kepzettsegek.get(i);
				sor.put("kepzettseg_tipus", kepzettseg.getTipus());
				sor.put("kepzettseg_megnevezes", kepzettseg.getMegnevezes());
				sor.put("kepzettseg_mod_fok", kepzettseg.getModFok());
				sor.putDateWithYear("kepzettseg_ev", kepzettseg.getEv());
				sor.putDate("kepzettseg_ervenyesseg_vege", kepzettseg.getErvenyessegVege());
				sor.put("kepzettseg_megjegyzes", kepzettseg.getMegjegyzes());
			}
			
			sor.putDate("fizetesi_adat_datum", null);
			sor.putErtekkeszletElem("fizetesi_adat_feor", null);
			sor.put("fizetesi_adat_fizetes", null);
			if (i < munkakoriHistorikusAdatok.size()) {
				MunkakoriHistorikusAdat munkakoriHistorikusAdat = munkakoriHistorikusAdatok.get(i);
				sor.putDate("fizetesi_adat_datum", munkakoriHistorikusAdat.getDatum());
				sor.putErtekkeszletElem("fizetesi_adat_feor", munkakoriHistorikusAdat.getfEOR());
				sor.put("fizetesi_adat_fizetes", munkakoriHistorikusAdat.getFizetes());
			}
			
			sorok.add(sor.getMap());
		}
		
		return sorok;
	}
}
