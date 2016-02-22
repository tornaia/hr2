package hu.interconnect.hr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.backend.api.enumeration.JogviszonyMegszunesenekModja;

@Embeddable
public class JogviszonyAdatok {

	@Column(name = "JOGVISZONY_KEZDETE")
	@Temporal(TemporalType.DATE)
	private Date jogviszonyKezdete;
	
	@Column(name = "JOGVISZONY_VEGE")
	@Temporal(TemporalType.DATE)
	private Date jogviszonyVege;
	
	@Column(name="JOGVISZONY_MEGSZUNESENEK_MODJA")
	@Enumerated(EnumType.STRING)
	private JogviszonyMegszunesenekModja jogviszonyMegszunesenekModja;
	
	@Column(name = "PROBAIDO_VEGE")
	@Temporal(TemporalType.DATE)
	private Date probaidoVege;
	
	@Column(name = "MUNKASZERZODES_LEJAR")
	@Temporal(TemporalType.DATE)
	private Date munkaszerzodesLejar;
	
	@Column(name="ALLOMANYMOD")
	@Enumerated(EnumType.STRING)
	private Allomanymod allomanymod;
	
	public JogviszonyAdatok(Date jogviszonyKezdete, Date jogviszonyVege, JogviszonyMegszunesenekModja jogviszonyMegszunesenekModja, Date probaidoVege, Date munkaszerzodesLejar, Allomanymod allomanymod) {
		this.jogviszonyKezdete = jogviszonyKezdete;
		this.jogviszonyVege = jogviszonyVege;
		this.jogviszonyMegszunesenekModja = jogviszonyMegszunesenekModja;
		this.probaidoVege = probaidoVege;
		this.munkaszerzodesLejar = munkaszerzodesLejar;
		this.allomanymod = allomanymod;
	}

	public Date getJogviszonyKezdete() {
		return jogviszonyKezdete;
	}

	public Date getJogviszonyVege() {
		return jogviszonyVege;
	}

	public JogviszonyMegszunesenekModja getJogviszonyMegszunesenekModja() {
		return jogviszonyMegszunesenekModja;
	}

	public Date getProbaidoVege() {
		return probaidoVege;
	}

	public Date getMunkaszerzodesLejar() {
		return munkaszerzodesLejar;
	}

	public Allomanymod getAllomanymod() {
		return allomanymod;
	}

	@SuppressWarnings("unused")
	private JogviszonyAdatok() {
	}
}
