package hu.interconnect.hr.module.personaldata.vacations;

import static hu.interconnect.util.DateUtils.azonosHonap;

import java.util.function.Predicate;

import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;
import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.util.Month;

public class AdottJelleguEsAdottHaviSzabadsagokPredicate implements Predicate<Szabadsag> {

	private Month honap;
	private FelhasznaltSzabadnapJelleg jelleg;
	
	public AdottJelleguEsAdottHaviSzabadsagokPredicate(Month honap, FelhasznaltSzabadnapJelleg jelleg) {
		this.honap = honap;
		this.jelleg = jelleg;
	}
	
	@Override
	public boolean test(Szabadsag input) {
		boolean azonosHonap = azonosHonap(honap.getTime(), input.getDatum());
		boolean azonosJelleg = jelleg == input.getJelleg();
		return azonosHonap && azonosJelleg;
	}
}
