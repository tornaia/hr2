package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;
import static hu.interconnect.util.DateUtils.parseOraPerc;

import java.util.Date;

import hu.interconnect.hr.backend.api.dto.JelenletiAdatDTO;
import hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus;

public class JelenletiAdatDTOBuilder extends Builder<JelenletiAdatDTO> {

	private Date datum;
	
	private JelenletiAdatTipus tipus;
	
	private Date kezdet;
	
	private Date veg;
	
	private Date ledolgozott;
	
	private Date to50;
	
	private Date to100;
	
	private Date m30;
	
	public JelenletiAdatDTOBuilder datum(String datumStr) {
		return datum(parseNap(datumStr));
	}
	
	public JelenletiAdatDTOBuilder datum(Date datum) {
		this.datum = datum;
		return this;
	}
	
	public JelenletiAdatDTOBuilder tipus(JelenletiAdatTipus tipus) {
		this.tipus = tipus;
		return this;
	}
	
	public JelenletiAdatDTOBuilder kezdet(String kezdetStr) {
		this.kezdet = parseOraPerc(kezdetStr);
		return this;
	}
	
	public JelenletiAdatDTOBuilder veg(String vegStr) {
		this.veg = parseOraPerc(vegStr);
		return this;
	}
	
	public JelenletiAdatDTOBuilder ledolgozott(String ledolgozottStr) {
		this.ledolgozott = parseOraPerc(ledolgozottStr);
		return this;
	}
	
	public JelenletiAdatDTOBuilder to50(String to50Str) {
		this.to50 = parseOraPerc(to50Str);
		return this;
	}
	
	public JelenletiAdatDTOBuilder to100(String to100Str) {
		this.to100 = parseOraPerc(to100Str);
		return this;
	}
	
	public JelenletiAdatDTOBuilder m30(String m30Str) {
		this.m30 = parseOraPerc(m30Str);
		return this;
	}
	
	@Override
	public JelenletiAdatDTO letrehoz() {
		JelenletiAdatDTO dto = new JelenletiAdatDTO();
		dto.setDatum(datum);
		dto.setTipus(tipus);
		dto.setKezdet(kezdet);
		dto.setVeg(veg);
		dto.setLedolgozott(ledolgozott);
		dto.setTo50(to50);
		dto.setTo100(to100);
		dto.setM30(m30);
		return dto;
	}
}
