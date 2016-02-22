package hu.interconnect.hr.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import hu.interconnect.hr.backend.api.enumeration.KozteruletTipus;

@Embeddable
public class Lakcim {

	@Column(name="IRANYITOSZAM")
	private String iranyitoszam;

	@Column(name="TELEPULES")
	private String telepules;

	@Column(name="KERULET")
	private String kerulet;

	@Column(name="KOZTERULET_NEV")
	private String kozteruletNev;

	@Column(name="KOZTERULET_TIPUS")
	@Enumerated(EnumType.STRING)
	private KozteruletTipus kozteruletTipus;

	@Column(name="KOZTERULET_SZAM")
	private String kozteruletSzam;

	@Column(name="EPULET")
	private String epulet;

	@Column(name="LEPCSOHAZ")
	private String lepcsohaz;

	@Column(name="EMELET")
	private String emelet;

	@Column(name="AJTO")
	private String ajto;

	public Lakcim(String iranyitoszam, String telepules, String kerulet, String kozteruletNev, KozteruletTipus kozteruletTipus, String kozteruletSzam, String epulet, String lepcsohaz, String emelet, String ajto) {
		this.iranyitoszam = iranyitoszam;
		this.telepules = telepules;
		this.kerulet = kerulet;
		this.kozteruletNev = kozteruletNev;
		this.kozteruletTipus = kozteruletTipus;
		this.kozteruletSzam = kozteruletSzam;
		this.epulet = epulet;
		this.lepcsohaz = lepcsohaz;
		this.emelet = emelet;
		this.ajto = ajto;
	}

	public String getIranyitoszam() {
		return iranyitoszam;
	}

	public String getTelepules() {
		return telepules;
	}

	public String getKerulet() {
		return kerulet;
	}

	public String getKozteruletNev() {
		return kozteruletNev;
	}

	public KozteruletTipus getKozteruletTipus() {
		return kozteruletTipus;
	}

	public String getKozteruletSzam() {
		return kozteruletSzam;
	}

	public String getEpulet() {
		return epulet;
	}

	public String getLepcsohaz() {
		return lepcsohaz;
	}

	public String getEmelet() {
		return emelet;
	}

	public String getAjto() {
		return ajto;
	}

	@SuppressWarnings("unused")
	private Lakcim() {
	}
}
