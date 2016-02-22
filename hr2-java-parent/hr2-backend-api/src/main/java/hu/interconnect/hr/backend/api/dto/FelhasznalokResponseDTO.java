package hu.interconnect.hr.backend.api.dto;

import java.util.List;

import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;

public class FelhasznalokResponseDTO {

	public List<FelhasznaloDTO> felhasznalok;
	
	public FelhasznalokResponseDTO(List<FelhasznaloDTO> felhasznalok) {
		this.felhasznalok = felhasznalok;
	}
	
	public static class FelhasznaloDTO {
		
		public int id;

		public String nev;

		public Szerep szerep;

		public String vezeteknev;
		
		public String keresztnev;

		public boolean enabled;

		public Locale locale;
		
		public FelhasznaloDTO(int id, String nev, Szerep szerep, String vezeteknev, String keresztnev, boolean enabled, Locale locale) {
			this.id = id;
			this.nev = nev;
			this.szerep = szerep;
			this.vezeteknev = vezeteknev;
			this.keresztnev = keresztnev;
			this.enabled = enabled;
			this.locale = locale;
		}
	}
}
