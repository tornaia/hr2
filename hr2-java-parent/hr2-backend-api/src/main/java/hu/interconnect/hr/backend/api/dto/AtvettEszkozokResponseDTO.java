package hu.interconnect.hr.backend.api.dto;

import java.util.List;

public class AtvettEszkozokResponseDTO {

	public List<AtvettEszkozDTO> atvettEszkozok;
	
	public AtvettEszkozokResponseDTO(List<AtvettEszkozDTO> atvettEszkozok) {
		this.atvettEszkozok = atvettEszkozok;
	}
	
	public static class AtvettEszkozDTO {
		
		public int id;
		
		public String megnevezes;
		
		public String megjegyzes;
		
		public String eredetiNev;
		
		public AtvettEszkozDTO(int id, String megnevezes, String megjegyzes, String eredetiNev) {
			this.id = id;
			this.megnevezes = megnevezes;
			this.megjegyzes = megjegyzes;
			this.eredetiNev = eredetiNev;
		}
	}
}
