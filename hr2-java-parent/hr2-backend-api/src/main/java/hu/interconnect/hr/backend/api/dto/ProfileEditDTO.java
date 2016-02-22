package hu.interconnect.hr.backend.api.dto;

import hu.interconnect.hr.backend.api.enumeration.Locale;

public class ProfileEditDTO {

	private String azonosito;

	private Locale locale;
	
	private String jelszo;

	public String getAzonosito() {
		return azonosito;
	}

	public void setAzonosito(String azonosito) {
		this.azonosito = azonosito;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getJelszo() {
		return jelszo;
	}

	public void setJelszo(String jelszo) {
		this.jelszo = jelszo;
	}
}
