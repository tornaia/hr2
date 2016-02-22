package hu.interconnect.hr.module.reports.vacation;

import static hu.interconnect.util.DateUtils.napFormaz;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;
import hu.interconnect.hr.domain.Szabadsag;

public class SzabadsagExcelKivettSzabadnapMezotSzabadsagokbolEloallito implements Function<List<Szabadsag>, String> {

	private FelhasznaltSzabadnapJelleg jelleg;
	private Date honap;

	public SzabadsagExcelKivettSzabadnapMezotSzabadsagokbolEloallito(FelhasznaltSzabadnapJelleg jelleg, Date honap) {
		this.jelleg = jelleg;
		this.honap = honap;
	}
	
	@Override
	public String apply(List<Szabadsag> egeszEvesSzabadsagok) {
		return egeszEvesSzabadsagok.stream()
				.filter(new AdottHaviSzabadsagPredicate(honap))
				.filter(sz -> sz.getJelleg() == jelleg)
				.map(sz -> napFormaz(sz.getDatum()))
				.collect(Collectors.joining(", "));
	}
}
