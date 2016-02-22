package test.builder;

import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;

public class ErtekkeszletElemCreateDTOBuilder extends Builder<ErtekkeszletElemCreateDTO> {

	private ErtekkeszletElemTipus tipus;
	
	private String megnevezesMagyar;
	
	private String megnevezesAngol;
	
	public ErtekkeszletElemCreateDTOBuilder tipus(ErtekkeszletElemTipus tipus) {
		this.tipus = tipus;
		return this;
	}
	
	public ErtekkeszletElemCreateDTOBuilder megnevezesMagyar(String megnevezesMagyar) {
		this.megnevezesMagyar = megnevezesMagyar;
		return this;
	}
	
	public ErtekkeszletElemCreateDTOBuilder megnevezesAngol(String megnevezesAngol) {
		this.megnevezesAngol = megnevezesAngol;
		return this;
	}
	
	@Override
	public ErtekkeszletElemCreateDTO letrehoz() {
		ErtekkeszletElemCreateDTO dto = new ErtekkeszletElemCreateDTO();
		dto.setTipus(tipus);
		dto.setMegnevezesMagyar(megnevezesMagyar);
		dto.setMegnevezesAngol(megnevezesAngol);
		return dto;
	}

}
