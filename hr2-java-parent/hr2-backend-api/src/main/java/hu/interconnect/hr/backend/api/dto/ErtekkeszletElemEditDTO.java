package hu.interconnect.hr.backend.api.dto;

public class ErtekkeszletElemEditDTO {

	private int id;
	
	private String megnevezesMagyar;
	
	private String megnevezesAngol;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMegnevezesMagyar() {
		return megnevezesMagyar;
	}

	public void setMegnevezesMagyar(String megnevezesMagyar) {
		this.megnevezesMagyar = megnevezesMagyar;
	}

	public String getMegnevezesAngol() {
		return megnevezesAngol;
	}

	public void setMegnevezesAngol(String megnevezesAngol) {
		this.megnevezesAngol = megnevezesAngol;
	}
}
