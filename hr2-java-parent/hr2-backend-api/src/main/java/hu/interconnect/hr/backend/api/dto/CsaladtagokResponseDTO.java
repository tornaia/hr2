package hu.interconnect.hr.backend.api.dto;

import java.util.Date;
import java.util.List;

public class CsaladtagokResponseDTO {

	public List<CsaladtagDTO> csaladtagok;
	
	public CsaladtagokResponseDTO(List<CsaladtagDTO> atvettEszkozok) {
		this.csaladtagok = atvettEszkozok;
	}
	
	public static class CsaladtagDTO {
		
		public int id;
		
		public String nev;
		
		public Date szuletesiDatum;

		public String taj;

		public String megjegyzes;
		
		public CsaladtagDTO(int id, String nev, Date szuletesiDatum, String taj, String megjegyzes) {
			this.id = id;
			this.nev = nev;
			this.szuletesiDatum = szuletesiDatum;
			this.taj = taj;
			this.megjegyzes = megjegyzes;
		}
	}
}
