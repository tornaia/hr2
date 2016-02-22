package hu.interconnect.hr.backend.api.dto;

import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;

public class FelhasznaloResponseDTO {

	public int id;

	public String nev;

	public Szerep szerep;

	public Integer tsz;

	public boolean enabled;

	public Locale locale;
	
	public FelhasznaloResponseDTO(int id, String nev, Szerep szerep, Integer tsz, boolean enabled, Locale locale) {
		this.id = id;
		this.nev = nev;
		this.szerep = szerep;
		this.tsz = tsz;
		this.enabled = enabled;
		this.locale = locale;
	}
}
