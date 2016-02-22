package hu.interconnect.hr.module.reports.attendance;

import java.util.List;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.Szemelyitorzs;

public class SzemelyitorzsAktivEsNemTartozikHozzaAktivFelhasznalo implements java.util.function.Predicate<Szemelyitorzs> {

	private List<Felhasznalo> aktivFelhasznalok;

	public SzemelyitorzsAktivEsNemTartozikHozzaAktivFelhasznalo(List<Felhasznalo> aktivFelhasznalok) {
		this.aktivFelhasznalok = aktivFelhasznalok;
	}
	
	@Override
	public boolean test(Szemelyitorzs szemelyitorzs) {
		boolean isSzemelyitorzsAktiv = szemelyitorzs.getJogviszonyAdatok().getAllomanymod() == Allomanymod.AKTIV;
		boolean hasAktivFelhasznaloiFiok = aktivFelhasznalok.contains(szemelyitorzs);
		return isSzemelyitorzsAktiv && !hasAktivFelhasznaloiFiok;
	}
}
