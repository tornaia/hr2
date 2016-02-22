package hu.interconnect.hr.domain;

import static hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus.KOLTSEGHELY;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="KOLTSEGHELY")
public class Koltseghely extends ErtekkeszletElem {

	public Koltseghely(String megnevezesMagyar, String megnevezesAngol) {
		super(KOLTSEGHELY, megnevezesMagyar, megnevezesAngol);
	}
	
	public Koltseghely(int id) {
		super(id);
	}
	
	@SuppressWarnings("unused")
	private Koltseghely() {
	}
}
