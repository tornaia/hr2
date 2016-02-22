package hu.interconnect.hr.module.reports.vacation;

import static hu.interconnect.util.DateUtils.azonosHonap;

import java.util.Date;
import java.util.function.Predicate;

import hu.interconnect.hr.domain.Szabadsag;

public class AdottHaviSzabadsagPredicate implements Predicate<Szabadsag> {

	private Date honap;

	public AdottHaviSzabadsagPredicate(Date honap) {
		this.honap = honap;
	}
	
	@Override
	public boolean test(Szabadsag szabadsag) {
		return azonosHonap(honap, szabadsag.getDatum());
	}
}
