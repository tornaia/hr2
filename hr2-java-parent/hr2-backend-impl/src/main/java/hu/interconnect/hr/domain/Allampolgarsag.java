package hu.interconnect.hr.domain;

import static hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus.ALLAMPOLGARSAG;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;

@Entity
@DiscriminatorValue(value="ALLAMPOLGARSAG")
public class Allampolgarsag extends ErtekkeszletElem {

	public Allampolgarsag(String megnevezesMagyar, String megnevezesAngol) {
		super(ALLAMPOLGARSAG, megnevezesMagyar, megnevezesAngol);
	}
	
	@Override
	public ErtekkeszletElemTipus getTipus() {
		return ALLAMPOLGARSAG;
	}
	
	public Allampolgarsag(int id) {
		super(id);
	}

	@SuppressWarnings("unused")
	private Allampolgarsag() {
	}
}
