package hu.interconnect.hr.backend.api.dto;

import java.util.List;

import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;

public class ErtekkeszletElemekResponseDTO {

	public List<ErtekkeszletElemDTO> ertekkeszletElemek;
	
	public ErtekkeszletElemekResponseDTO(List<ErtekkeszletElemDTO> ertekkeszletElemek) {
		this.ertekkeszletElemek = ertekkeszletElemek;
	}
	
	public static class ErtekkeszletElemDTO {
		
		public int id;
		
		public ErtekkeszletElemTipus tipus;

		public String megnevezesMagyar;

		public String megnevezesAngol;
		
		public ErtekkeszletElemDTO(int id, ErtekkeszletElemTipus tipus, String megnevezesMagyar, String megnevezesAngol) {
			this.id = id;
			this.tipus = tipus;
			this.megnevezesMagyar = megnevezesMagyar;
			this.megnevezesAngol = megnevezesAngol;
		}
	}
}
