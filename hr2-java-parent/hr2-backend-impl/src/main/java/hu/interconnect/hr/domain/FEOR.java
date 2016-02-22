package hu.interconnect.hr.domain;

import static hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus.FEOR;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="FEOR")
public class FEOR extends ErtekkeszletElem {

	public FEOR(String megnevezesMagyar, String megnevezesAngol) {
		super(FEOR, megnevezesMagyar, megnevezesAngol);
	}
	
	public FEOR(int id) {
		super(id);
	}
	
	@SuppressWarnings("unused")
	private FEOR() {
	}
}
