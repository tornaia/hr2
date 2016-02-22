package test.builder;

import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.Szemelyitorzs;

public class FelhasznaloBuilder extends Builder<Felhasznalo>  {

	private String nev;

	private String jelszo;

	private Szerep szerep;

	private Szemelyitorzs szemelyitorzs;

	private boolean enabled;
	
	private Locale locale;

	public FelhasznaloBuilder nev(String nev) {
		this.nev = nev;
		return this;
	}

	public FelhasznaloBuilder jelszo(String jelszo) {
		this.jelszo = jelszo;
		return this;
	}

	public FelhasznaloBuilder szerep(Szerep szerep) {
		this.szerep = szerep;
		return this;
	}

	public FelhasznaloBuilder szemelyitorzs(Szemelyitorzs szemelyitorzs) {
		this.szemelyitorzs = szemelyitorzs;
		return this;
	}

	public FelhasznaloBuilder enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public FelhasznaloBuilder locale(Locale locale) {
		this.locale = locale;
		return this;
	}

	@Override
	public Felhasznalo letrehoz() {
		return new Felhasznalo(nev, szerep, jelszo, szemelyitorzs, enabled, locale);
	}
}
