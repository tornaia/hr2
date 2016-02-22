package hu.interconnect.hr.backend.api.dto;

public class AtvettEszkozEditDTO {

	private int id;
	
	private String megnevezes;
	
	private String megjegyzes;
	
	private String eredetiNev;
	
	private byte[] adat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
