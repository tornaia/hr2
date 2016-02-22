package hu.interconnect.hr.domain;

import static hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus.SZERVEZETI_EGYSEG;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="SZERVEZETI_EGYSEG")
public class SzervezetiEgyseg extends ErtekkeszletElem {

	public SzervezetiEgyseg(String megnevezesMagyar, String megnevezesAngol) {
		super(SZERVEZETI_EGYSEG, megnevezesMagyar, megnevezesAngol);
	}
	
	public SzervezetiEgyseg(int id) {
		super(id);
	}
	
	@SuppressWarnings("unused")
	private SzervezetiEgyseg() {
	}
}
