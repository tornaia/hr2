package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;
import static hu.interconnect.util.DateUtils.parseOraPerc;

import java.util.Date;

import hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus;
import hu.interconnect.hr.domain.JelenletiAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;

public class JelenletiAdatBuilder extends Builder<JelenletiAdat> {

	private Szemelyitorzs szemelyitorzs;

	private Date datum;
	
	private JelenletiAdatTipus tipus;
	
	private Date kezdet;
	
	private Date veg;
	
	private Date ledolgozott;
	
	private Date to50;
	
	private Date to100;
	
	private Date m30;
	
	public JelenletiAdatBuilder szemelyitorzs(Szemelyitorzs szemelyitorzs) {
		this.szemelyitorzs = szemelyitorzs;
		return this;
	}
	
	public JelenletiAdatBuilder datum(String datumStr) {
		return datum(parseNap(datumStr));
	}
	
	public JelenletiAdatBuilder datum(Date datum) {
		this.datum = datum;
		return this;
	}
	
	public JelenletiAdatBuilder tipus(JelenletiAdatTipus tipus) {
		this.tipus = tipus;
		return this;
	}
	
	public JelenletiAdatBuilder kezdet(String kezdetStr) {
		this.kezdet = parseOraPerc(kezdetStr);
		return this;
	}
	
	public JelenletiAdatBuilder veg(String vegStr) {
		this.veg = parseOraPerc(vegStr);
		return this;
	}
	
	public JelenletiAdatBuilder ledolgozott(String ledolgozottStr) {
		this.ledolgozott = parseOraPerc(ledolgozottStr);
		return this;
	}
	
	public JelenletiAdatBuilder to50(String to50Str) {
		this.to50 = parseOraPerc(to50Str);
		return this;
	}
	
	public JelenletiAdatBuilder to100(String to100Str) {
		this.to100 = parseOraPerc(to100Str);
		return this;
	}
	
	public JelenletiAdatBuilder m30(String m30Str) {
		this.m30 = parseOraPerc(m30Str);
		return this;
	}
	
	@Override
	public JelenletiAdat letrehoz() {
		return new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30);
	}
}
