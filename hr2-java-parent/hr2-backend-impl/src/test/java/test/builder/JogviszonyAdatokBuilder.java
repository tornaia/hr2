package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.backend.api.enumeration.JogviszonyMegszunesenekModja;
import hu.interconnect.hr.domain.JogviszonyAdatok;

public class JogviszonyAdatokBuilder extends Builder<JogviszonyAdatok>  {

	private Date jogviszonyKezdete;
	
	private Date jogviszonyVege;
	
	private JogviszonyMegszunesenekModja jogviszonyMegszunesenekModja;
	
	private Date probaidoVege;
	
	private Date munkaszerzodesLejar;
	
	private Allomanymod allomanymod;
	
	public JogviszonyAdatokBuilder jogviszonyKezdete(String datum) {
		this.jogviszonyKezdete = parseNap(datum);
		return this;
	}
	
	public JogviszonyAdatokBuilder jogviszonyVege(String datum) {
		this.jogviszonyVege = parseNap(datum);
		return this;
	}
	
	public JogviszonyAdatokBuilder probaidoVege(String datum) {
		this.probaidoVege = parseNap(datum);
		return this;
	}
	
	public JogviszonyAdatokBuilder allomanymod(Allomanymod allomanymod) {
		this.allomanymod = allomanymod;
		return this;
	}
	
	@Override
	public JogviszonyAdatok letrehoz() {
		return new JogviszonyAdatok(jogviszonyKezdete, jogviszonyVege, jogviszonyMegszunesenekModja, probaidoVege, munkaszerzodesLejar, allomanymod);
	}
}
