package hu.interconnect.hr.domain;

import static hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus.MUNKAKOR;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="MUNKAKOR")
public class Munkakor extends ErtekkeszletElem {

	public Munkakor(String megnevezesMagyar, String megnevezesAngol) {
		super(MUNKAKOR, megnevezesMagyar, megnevezesAngol);
	}
	
	public Munkakor(int id) {
		super(id);
	}
	
	@SuppressWarnings("unused")
	private Munkakor() {
	}
}
