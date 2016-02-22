package hu.interconnect.hr.module.reports.detailedlistofpeople;

import static hu.interconnect.util.DateUtils.aktualisIdo;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import hu.interconnect.hr.domain.JogviszonyAdatok;
import hu.interconnect.hr.domain.MunkakoriBesorolas;
import hu.interconnect.hr.domain.MunkakoriHistorikusAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;

public class DetailedListOfPeopleRowFromPersonaldataCreator implements Function<Szemelyitorzs, Map<String, Object>> {

	@Override
	public Map<String, Object> apply(Szemelyitorzs szemelyitorzs) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		
		sor.put("nev", szemelyitorzs.getTeljesNev());
		
		JogviszonyAdatok jogviszonyAdatok = szemelyitorzs.getJogviszonyAdatok();
		sor.putDate("jogviszony_kezdete", jogviszonyAdatok.getJogviszonyKezdete());
		sor.putDate("jogviszony_vege", jogviszonyAdatok.getJogviszonyVege());
		
		MunkakoriHistorikusAdat aktualisMunkakoriHistorikusAdat = getAktualisMunkakoriHistorikusAdat(szemelyitorzs);
		sor.put("aktualis_havi_munkaber", "");
		if (aktualisMunkakoriHistorikusAdat != null) {
			sor.put("aktualis_havi_munkaber", aktualisMunkakoriHistorikusAdat.getFizetes());
		}
		
		MunkakoriBesorolas munkakoriBesorolas = szemelyitorzs.getMunkakoriBesorolas();
		sor.putErtekkeszletElem("koltseghely", munkakoriBesorolas.getKoltseghely());
		sor.putErtekkeszletElem("munkakor", munkakoriBesorolas.getMunkakor());
		
		return sor.getMap();
	}
	
	private static MunkakoriHistorikusAdat getAktualisMunkakoriHistorikusAdat(Szemelyitorzs szemelyitorzs) {
		MunkakoriHistorikusAdat ervenyes = null;
		
		Date aktualisIdo = aktualisIdo();
		for (MunkakoriHistorikusAdat munkakoriHistorikusAdat : szemelyitorzs.getMunkakoriBesorolas().getMunkakoriHistorikusAdatok()) {
			if (aktualisIdo.after(munkakoriHistorikusAdat.getDatum()) && (ervenyes == null || ervenyes.getDatum().before(munkakoriHistorikusAdat.getDatum()))) {
				ervenyes = munkakoriHistorikusAdat;
			}
		}
		return ervenyes;
	}
}
