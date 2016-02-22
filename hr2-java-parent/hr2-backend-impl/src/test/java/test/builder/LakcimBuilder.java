package test.builder;

import hu.interconnect.hr.backend.api.enumeration.KozteruletTipus;
import hu.interconnect.hr.domain.Lakcim;

public class LakcimBuilder extends Builder<Lakcim>  {

	private String iranyitoszam;
	
	private String telepules;
	
	private String kerulet;
	
	private String kozteruletNev;
	
	private KozteruletTipus kozteruletTipus;
	
	private String kozteruletSzam;
	
	private String epulet;
	
	private String lepcsohaz;
	
	private String emelet;
	
	private String ajto;
	
	public LakcimBuilder iranyitoszam(String iranyitoszam) {
		this.iranyitoszam = iranyitoszam;
		return this;
	}
	
	public LakcimBuilder telepules(String telepules) {
		this.telepules = telepules;
		return this;
	}
	
	public LakcimBuilder kerulet(String kerulet) {
		this.kerulet = kerulet;
		return this;
	}
	
	public LakcimBuilder kozteruletNev(String kozteruletNev) {
		this.kozteruletNev = kozteruletNev;
		return this;
	}
	
	public LakcimBuilder kozteruletTipus(KozteruletTipus kozteruletTipus) {
		this.kozteruletTipus = kozteruletTipus;
		return this;
	}
	
	public LakcimBuilder kozteruletSzam(String kozteruletSzam) {
		this.kozteruletSzam = kozteruletSzam;
		return this;
	}
	
	public LakcimBuilder epulet(String epulet) {
		this.epulet = epulet;
		return this;
	}
	
	public LakcimBuilder lepcsohaz(String lepcsohaz) {
		this.lepcsohaz = lepcsohaz;
		return this;
	}
	
	public LakcimBuilder emelet(String emelet) {
		this.emelet = emelet;
		return this;
	}
	
	public LakcimBuilder ajto(String ajto) {
		this.ajto = ajto;
		return this;
	}
	
	@Override
	public Lakcim letrehoz() {
		return new Lakcim(iranyitoszam, telepules, kerulet, kozteruletNev, kozteruletTipus, kozteruletSzam, epulet, lepcsohaz, emelet, ajto);
	}
}
