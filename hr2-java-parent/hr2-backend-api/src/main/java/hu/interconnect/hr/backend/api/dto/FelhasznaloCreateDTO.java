package hu.interconnect.hr.backend.api.dto;

import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;

public class FelhasznaloCreateDTO {

	private String nev;

	private Szerep szerep;
	
	private String jelszo;

	private Integer tsz;

	private boolean enabled;

	private Locale locale;
	
	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public Szerep getSzerep() {
		return szerep;
	}

	public void setSzerep(Szerep szerep) {
		this.szerep = szerep;
	}

	public String getJelszo() {
		return jelszo;
	}

	public void setJelszo(String jelszo) {
		this.jelszo = jelszo;
	}
	
	public Integer getTsz() {
		return tsz;
	}

	public void setTsz(Integer tsz) {
		this.tsz = tsz;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
