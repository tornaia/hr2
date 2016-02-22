package hu.interconnect.hr.domain;

import static hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus.FOGLALKOZASI_VISZONY;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="FOGLALKOZASI_VISZONY")
public class FoglalkozasiViszony extends ErtekkeszletElem {

	public FoglalkozasiViszony(String megnevezesMagyar, String megnevezesAngol) {
		super(FOGLALKOZASI_VISZONY, megnevezesMagyar, megnevezesAngol);
	}
	
	public FoglalkozasiViszony(int id) {
		super(id);
	}
	
	@SuppressWarnings("unused")
	private FoglalkozasiViszony() {
	}
}
