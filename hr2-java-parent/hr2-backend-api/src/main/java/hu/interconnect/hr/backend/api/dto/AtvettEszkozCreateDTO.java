package hu.interconnect.hr.backend.api.dto;

public class AtvettEszkozCreateDTO {

	private int tsz;
	
	private String megnevezes;
	
	private String megjegyzes;
	
	private String eredetiNev;
	
	private byte[] adat;

	public int getTsz() {
		return tsz;
	}

	public void setTsz(int tsz) {
		this.tsz = tsz;
	}

	public String getMegnevezes() {
		return megnevezes;
	}

	public void setMegnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
	}

	public String getMegjegyzes() {
		return megjegyzes;
	}

	public void setMegjegyzes(String megjegyzes) {
		this.megjegyzes = megjegyzes;
	}

	public String getEredetiNev() {
		return eredetiNev;
	}

	public void setEredetiNev(String eredetiNev) {
		this.eredetiNev = eredetiNev;
	}

	public byte[] getAdat() {
		return adat;
	}

	public void setAdat(byte[] adat) {
		this.adat = adat;
	}
}
