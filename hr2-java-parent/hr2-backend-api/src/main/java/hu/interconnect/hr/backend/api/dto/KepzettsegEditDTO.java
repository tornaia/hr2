package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

public class KepzettsegEditDTO {
	
	private int id;
	
	private String tipus;
	
	private String megnevezes;
	
	private String modFok;
	
	private Date ev;
	
	private Date ervenyessegVege;
	
	private String megjegyzes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public String getMegnevezes() {
		return megnevezes;
	}

	public void setMegnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
	}

	public String getModFok() {
		return modFok;
	}

	public void setModFok(String modFok) {
		this.modFok = modFok;
	}

	public Date getEv() {
		return ev;
	}

	public void setEv(Date ev) {
		this.ev = ev;
	}

	public Date getErvenyessegVege() {
		return ervenyessegVege;
	}

	public void setErvenyessegVege(Date ervenyessegVege) {
		this.ervenyessegVege = ervenyessegVege;
	}

	public String getMegjegyzes() {
		return megjegyzes;
	}

	public void setMegjegyzes(String megjegyzes) {
		this.megjegyzes = megjegyzes;
	}
	
	
}
