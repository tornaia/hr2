package hu.interconnect.hr.domain;

import static hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus.FOGLALKOZTATAS_JELLEGE;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="FOGLALKOZTATAS_JELLEGE")
public class FoglalkoztatasJellege extends ErtekkeszletElem {

	public FoglalkoztatasJellege(String megnevezesMagyar, String megnevezesAngol) {
		super(FOGLALKOZTATAS_JELLEGE, megnevezesMagyar, megnevezesAngol);
	}
	
	public FoglalkoztatasJellege(int id) {
		super(id);
	}
	
	@SuppressWarnings("unused")
	private FoglalkoztatasJellege() {
	}
}
