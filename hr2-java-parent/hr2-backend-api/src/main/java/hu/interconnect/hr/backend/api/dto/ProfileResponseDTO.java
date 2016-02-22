package hu.interconnect.hr.backend.api.dto;

import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;

public class ProfileResponseDTO {

	public String azonosito;

	public Szerep szerep;

	public String sztVezeteknev;
	
	public String sztKeresztnev;

	public Locale locale;
	
	public ProfileResponseDTO(String azonosito, Szerep szerep, String sztVezeteknev, String sztKeresztnev, Locale locale) {
		this.azonosito = azonosito;
		this.szerep = szerep;
		this.sztVezeteknev = sztVezeteknev;
		this.sztKeresztnev = sztKeresztnev;
		this.locale = locale;
	}
	
}
