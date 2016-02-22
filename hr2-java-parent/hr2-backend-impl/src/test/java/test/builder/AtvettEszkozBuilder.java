package test.builder;

import hu.interconnect.hr.domain.AtvettEszkoz;
import hu.interconnect.hr.domain.Szemelyitorzs;

public class AtvettEszkozBuilder extends Builder<AtvettEszkoz> {

	private Szemelyitorzs szemelyitorzs;
	
	private String megnevezes;
	
	private String megjegyzes;
	
	private String eredetiNev;
	
	private byte[] adat;
	
	public AtvettEszkozBuilder szemelyitorzs(Szemelyitorzs szemelyitorzs) {
		this.szemelyitorzs = szemelyitorzs;
		return this;
	}
	
	public AtvettEszkozBuilder megnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
		return this;
	}
	
	public AtvettEszkozBuilder megjegyzes(String megjegyzes) {
		this.megjegyzes = megjegyzes;
		return this;
	}
	
	public AtvettEszkozBuilder eredetiNev(String eredetiNev) {
		this.eredetiNev = eredetiNev;
		return this;
	}
	
	public AtvettEszkozBuilder adat(byte[] adat) {
		this.adat = adat;
		return this;
	}
	
	@Override
	public AtvettEszkoz letrehoz() {
		return new AtvettEszkoz(szemelyitorzs, megnevezes, megjegyzes, eredetiNev, adat);
	}
}
