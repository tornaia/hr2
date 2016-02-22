package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

public class SzabadsagFelhasznalasDeleteDTO {
	
	private int tsz;

	private Date kezdet;
	
	private Date veg;
	
	public int getTsz() {
		return tsz;
	}

	public void setTsz(int tsz) {
		this.tsz = tsz;
	}

	public Date getKezdet() {
		return kezdet;
	}

	public void setKezdet(Date kezdet) {
		this.kezdet = kezdet;
	}

	public Date getVeg() {
		return veg;
	}

	public void setVeg(Date veg) {
		this.veg = veg;
	}
}
