package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

import hu.interconnect.hr.backend.api.enumeration.KivetelnapTipus;

public class KivetelnapCreateDTO {

	private Date datum;
	
	private KivetelnapTipus tipus;

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public KivetelnapTipus getTipus() {
		return tipus;
	}

	public void setTipus(KivetelnapTipus tipus) {
		this.tipus = tipus;
	}
}
