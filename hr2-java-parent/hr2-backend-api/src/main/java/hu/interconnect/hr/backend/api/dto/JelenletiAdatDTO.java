package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

import hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus;

public class JelenletiAdatDTO {

	private Date datum;
	
	private JelenletiAdatTipus tipus;
	
	private Date kezdet;
	
	private Date veg;
	
	private Date ledolgozott;
	
	private Date to50;
	
	private Date to100;
	
	private Date m30;

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public JelenletiAdatTipus getTipus() {
		return tipus;
	}

	public void setTipus(JelenletiAdatTipus tipus) {
		this.tipus = tipus;
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

	public Date getLedolgozott() {
		return ledolgozott;
	}

	public void setLedolgozott(Date ledolgozott) {
		this.ledolgozott = ledolgozott;
	}

	public Date getTo50() {
		return to50;
	}

	public void setTo50(Date to50) {
		this.to50 = to50;
	}

	public Date getTo100() {
		return to100;
	}

	public void setTo100(Date to100) {
		this.to100 = to100;
	}

	public Date getM30() {
		return m30;
	}

	public void setM30(Date m30) {
		this.m30 = m30;
	}
}
