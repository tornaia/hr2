package hu.interconnect.hr.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import hu.interconnect.hr.backend.api.enumeration.CsaladiAllapot;

@Embeddable
public class Csalad {

	@Column(name="CSALADI_ALLAPOT")
	@Enumerated(EnumType.STRING)
	private CsaladiAllapot csaladiAllapot;
	
	@OrderBy(value="id")
	@OneToMany(mappedBy="szemelyitorzs", cascade={CascadeType.REMOVE})
	private Set<Csaladtag> csaladtagok;
	
	public Csalad(CsaladiAllapot csaladiAllapot) {
		this.csaladiAllapot = csaladiAllapot;
	}

	public void merge(Csalad csalad) {
		this.csaladiAllapot = csalad.csaladiAllapot;
		this.csaladtagok = csalad.csaladtagok;
	}
	
	public CsaladiAllapot getCsaladiAllapot() {
		return csaladiAllapot;
	}

	public Set<Csaladtag> getCsaladtagok() {
		return csaladtagok;
	}

	@SuppressWarnings("unused")
	private Csalad() {
	}
}
