package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.backend.api.enumeration.KivetelnapTipus;
import hu.interconnect.hr.domain.Kivetelnap;

public class KivetelnapBuilder extends Builder<Kivetelnap> {

	private Date datum;
	
	private KivetelnapTipus tipus;
	
	public KivetelnapBuilder datum(Date datum) {
		this.datum = datum;
		return this;
	}
	
	public KivetelnapBuilder datum(String datum) {
		this.datum = parseNap(datum);
		return this;
	}
	
	public KivetelnapBuilder tipus(KivetelnapTipus tipus) {
		this.tipus = tipus;
		return this;
	}
	
	@Override
	public Kivetelnap letrehoz() {
		return new Kivetelnap(datum, tipus);
	}
}
