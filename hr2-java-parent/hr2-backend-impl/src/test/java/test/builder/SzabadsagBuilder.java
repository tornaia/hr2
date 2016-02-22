package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;
import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.hr.domain.Szemelyitorzs;

public class SzabadsagBuilder extends Builder<Szabadsag> {

	private Szemelyitorzs szemelyitorzs;
	
	private Date nap;
	
	private FelhasznaltSzabadnapJelleg jelleg;
	
	public SzabadsagBuilder szemelyitorzs(Szemelyitorzs szemelyitorzs) {
		this.szemelyitorzs = szemelyitorzs;
		return this;
	}

	public SzabadsagBuilder nap(String napStr) {
		return nap(parseNap(napStr));
	}
	
	public SzabadsagBuilder nap(Date day) {
		this.nap = day;
		return this;
	}
	
	public SzabadsagBuilder jelleg(FelhasznaltSzabadnapJelleg jelleg) {
		this.jelleg = jelleg;
		return this;
	}
	
	@Override
	public Szabadsag letrehoz() {
		return new Szabadsag(szemelyitorzs, nap, jelleg);
	}
}
