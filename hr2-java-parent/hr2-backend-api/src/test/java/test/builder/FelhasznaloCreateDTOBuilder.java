package test.builder;

import hu.interconnect.hr.backend.api.dto.FelhasznaloCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;

public class FelhasznaloCreateDTOBuilder extends Builder<FelhasznaloCreateDTO> {

	private String nev;

	private String jelszo;

	private Szerep szerep;

	private Integer tsz;

	private boolean enabled;
	
	private Locale locale;

	public FelhasznaloCreateDTOBuilder nev(String nev) {
		this.nev = nev;
		return this;
	}

	public FelhasznaloCreateDTOBuilder jelszo(String jelszo) {
		this.jelszo = jelszo;
		return this;
	}

	public FelhasznaloCreateDTOBuilder szerep(Szerep szerep) {
		this.szerep = szerep;
		return this;
	}

	public FelhasznaloCreateDTOBuilder tsz(Integer tsz) {
		this.tsz = tsz;
		return this;
	}

	public FelhasznaloCreateDTOBuilder enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public FelhasznaloCreateDTOBuilder locale(Locale locale) {
		this.locale = locale;
		return this;
	}

	@Override
	public FelhasznaloCreateDTO letrehoz() {
		FelhasznaloCreateDTO dto = new FelhasznaloCreateDTO();
		dto.setNev(nev);
		dto.setSzerep(szerep);
		dto.setJelszo(jelszo);
		dto.setTsz(tsz);
		dto.setEnabled(enabled);
		dto.setLocale(locale);
		return dto;
	}
}
