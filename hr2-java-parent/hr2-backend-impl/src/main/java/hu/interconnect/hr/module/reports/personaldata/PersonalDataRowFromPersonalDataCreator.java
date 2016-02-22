package hu.interconnect.hr.module.reports.personaldata;

import java.util.Map;
import java.util.function.Function;

import hu.interconnect.hr.domain.Lakcim;
import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;

public class PersonalDataRowFromPersonalDataCreator implements Function<Szemelyitorzs, Map<String, Object>> {

	@Override
	public Map<String, Object> apply(Szemelyitorzs szemelyitorzs) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		
		int tsz = szemelyitorzs.getTsz();
		sor.put("tsz", tsz);
		
		SzemelyiAdatok szemelyiAdatok = szemelyitorzs.getSzemelyiAdatok();
		sor.put("vezeteknev", szemelyiAdatok.getVezeteknev());
		sor.put("keresztnev", szemelyiAdatok.getKeresztnev());
		sor.putEnum("nem", szemelyiAdatok.getNem());
		sor.putErtekkeszletElem("allampolgarsag", szemelyiAdatok.getAllampolgarsag());
		sor.putDate("szuletesi_datum", szemelyiAdatok.getSzuletesiDatum());
		sor.put("szuletesi_hely", szemelyiAdatok.getSzuletesiHely());
		sor.put("szuletesi_orszag", szemelyiAdatok.getSzuletesiOrszag());
		sor.put("szuletesi_nev", szemelyiAdatok.getSzuletesiNev());
		sor.put("szuletesi_nev_anyja", szemelyiAdatok.getSzuletesiNevAnyja());
		sor.put("adoazonosito_jel", szemelyiAdatok.getAdoazonositoJel());
		sor.put("taj", szemelyiAdatok.getTaj());
		sor.put("szemelyi_igazolvany_szam", szemelyiAdatok.getSzemelyiIgazolvanySzam());
		sor.putDate("szemelyi_igazolvany_szam_lejarat", szemelyiAdatok.getSzemelyiIgazolvanySzamLejarat());
		sor.put("utlevel_szam", szemelyiAdatok.getUtlevelSzam());
		sor.putDate("utlevel_szam_lejarat", szemelyiAdatok.getUtlevelSzamLejarat());
		sor.put("telefon", szemelyiAdatok.getTelefon());
		sor.put("mobil", szemelyiAdatok.getMobil());
		sor.put("email", szemelyiAdatok.getEmail());
		sor.putEnum("aktualis_lakcim", szemelyiAdatok.getLakcimAktualis());
		Lakcim lakcimAllando = szemelyiAdatok.getLakcimAllando() == null ? new Lakcim(null, null, null, null, null, null, null, null, null, null) : szemelyiAdatok.getLakcimAllando();
		sor.put("allando_lakcim_iranyitoszam", lakcimAllando.getIranyitoszam());
		sor.put("allando_lakcim_telepules", lakcimAllando.getTelepules());
		sor.put("allando_lakcim_kerulet", lakcimAllando.getKerulet());
		sor.put("allando_lakcim_kozterulet_nev", lakcimAllando.getKozteruletNev());
		sor.putEnum("allando_lakcim_kozterulet_tipus", lakcimAllando.getKozteruletTipus());
		sor.put("allando_lakcim_kozterulet_szam", lakcimAllando.getKozteruletSzam());
		sor.put("allando_lakcim_epulet", lakcimAllando.getEpulet());
		sor.put("allando_lakcim_lepcsohaz", lakcimAllando.getLepcsohaz());
		sor.put("allando_lakcim_emelet", lakcimAllando.getEmelet());
		sor.put("allando_lakcim_ajto", lakcimAllando.getAjto());
		Lakcim lakcimIdeiglenes = szemelyiAdatok.getLakcimIdeiglenes() == null ? new Lakcim(null, null, null, null, null, null, null, null, null, null) : szemelyiAdatok.getLakcimIdeiglenes();
		sor.put("ideiglenes_lakcim_iranyitoszam", lakcimIdeiglenes.getIranyitoszam());
		sor.put("ideiglenes_lakcim_telepules", lakcimIdeiglenes.getTelepules());
		sor.put("ideiglenes_lakcim_kerulet", lakcimIdeiglenes.getKerulet());
		sor.put("ideiglenes_lakcim_kozterulet_nev", lakcimIdeiglenes.getKozteruletNev());
		sor.putEnum("ideiglenes_lakcim_kozterulet_tipus", lakcimIdeiglenes.getKozteruletTipus());
		sor.put("ideiglenes_lakcim_kozterulet_szam", lakcimIdeiglenes.getKozteruletSzam());
		sor.put("ideiglenes_lakcim_epulet", lakcimIdeiglenes.getEpulet());
		sor.put("ideiglenes_lakcim_lepcsohaz", lakcimIdeiglenes.getLepcsohaz());
		sor.put("ideiglenes_lakcim_emelet", lakcimIdeiglenes.getEmelet());
		sor.put("ideiglenes_lakcim_ajto", lakcimIdeiglenes.getAjto());
		
		return sor.getMap();
	}
}
