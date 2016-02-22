package hu.interconnect.hr.backend.api.dto;

import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;

public class ErtekkeszletElemCreateDTO {

	private ErtekkeszletElemTipus tipus;
	
	private String megnevezesMagyar;
	
	private String megnevezesAngol;

	public ErtekkeszletElemTipus getTipus() {
		return tipus;
	}

	public void setTipus(ErtekkeszletElemTipus tipus) {
		this.tipus = tipus;
	}

	public String getMegnevezesMagyar() {
		return megnevezesMagyar;
	}

	public void setMegnevezesMagyar(String megnevezesMagyar) {
		this.megnevezesMagyar = megnevezesMagyar;
	}

	public String getMegnevezesAngol() {
		return megnevezesAngol;
	}

	public void setMegnevezesAngol(String megnevezesAngol) {
		this.megnevezesAngol = megnevezesAngol;
	}
}
