package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

public class CsaladtagEditDTO {

	private int id;
	
	private String nev;
	
	private Date szuletesiDatum;
	
	private String taj;
	
	private String megjegyzes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public Date getSzuletesiDatum() {
		return szuletesiDatum;
	}

	public void setSzuletesiDatum(Date szuletesiDatum) {
		this.szuletesiDatum = szuletesiDatum;
	}

	public String getTaj() {
		return taj;
	}

	public void setTaj(String taj) {
		this.taj = taj;
	}

	public String getMegjegyzes() {
		return megjegyzes;
	}

	public void setMegjegyzes(String megjegyzes) {
		this.megjegyzes = megjegyzes;
	}
}
