package hu.interconnect.hr.backend.api.dto;

import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;

public class ErtekkeszletElemResponseDTO {

	public int id;
	
	public ErtekkeszletElemTipus tipus;

	public String megnevezesMagyar;

	public String megnevezesAngol;
	
	public ErtekkeszletElemResponseDTO(int id, ErtekkeszletElemTipus tipus, String megnevezesMagyar, String megnevezesAngol) {
		this.id = id;
		this.tipus = tipus;
		this.megnevezesMagyar = megnevezesMagyar;
		this.megnevezesAngol = megnevezesAngol;
	}
	
}
