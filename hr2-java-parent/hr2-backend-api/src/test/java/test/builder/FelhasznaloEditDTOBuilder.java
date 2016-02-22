package test.builder;

import hu.interconnect.hr.backend.api.dto.FelhasznaloEditDTO;
import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;

public class FelhasznaloEditDTOBuilder extends Builder<FelhasznaloEditDTO> {

	private String nev;

	private String jelszo;

	private Szerep szerep;

	private Integer tsz;

	private boolean enabled;
	
	private Locale locale;

	public FelhasznaloEditDTOBuilder nev(String nev) {
		this.nev = nev;
		return this;
	}

	public FelhasznaloEditDTOBuilder jelszo(String jelszo) {
		this.jelszo = jelszo;
		return this;
	}

	public FelhasznaloEditDTOBuilder szerep(Szerep szerep) {
		this.szerep = szerep;
		return this;
	}

	public FelhasznaloEditDTOBuilder tsz(Integer tsz) {
		this.tsz = tsz;
		return this;
	}

	public FelhasznaloEditDTOBuilder enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public FelhasznaloEditDTOBuilder locale(Locale locale) {
		this.locale = locale;
		return this;
	}

	@Override
	public FelhasznaloEditDTO letrehoz() {
		FelhasznaloEditDTO dto = new FelhasznaloEditDTO();
		dto.setNev(nev);
		dto.setSzerep(szerep);
		dto.setJelszo(jelszo);
		dto.setTsz(tsz);
		dto.setEnabled(enabled);
		dto.setLocale(locale);
		return dto;
	}
}
