package hu.interconnect.hr.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.interconnect.hr.backend.api.enumeration.LakcimAktualis;
import hu.interconnect.hr.backend.api.enumeration.Nem;

@Embeddable
public class SzemelyiAdatok {

	@Column(name="VEZETEKNEV")
	private String vezeteknev;

	@Column(name="KERESZTNEV")
	private String keresztnev;

	@Column(name="NEM")
	@Enumerated(EnumType.STRING)
	private Nem nem;

	@ManyToOne
	@JoinColumn(name="ALLAMPOLGARSAG")
	private Allampolgarsag allampolgarsag;

	@Column(name = "SZULETESI_DATUM")
	@Temporal(TemporalType.DATE)
	private Date szuletesiDatum;

	@Column(name="SZULETESI_HELY")
	private String szuletesiHely;

	@Column(name="SZULETESI_ORSZAG")
	private String szuletesiOrszag;

	@Column(name="SZULETESI_NEV")
	private String szuletesiNev;

	@Column(name="SZULETESI_NEV_ANYJA")
	private String szuletesiNevAnyja;

	@Column(name="ADOAZONOSITO_JEL")
	private String adoazonositoJel;

	@Column(name="TAJ")
	private String taj;

	@Column(name="SZEMELYI_IGAZOLVANY_SZAM")
	private String szemelyiIgazolvanySzam;

	@Column(name = "SZEMELYI_IGAZOLVANY_SZAM_LEJARAT")
	@Temporal(TemporalType.DATE)
	private Date szemelyiIgazolvanySzamLejarat;

	@Column(name="UTLEVEL_SZAM")
	private String utlevelSzam;

	@Column(name = "UTLEVEL_SZAM_LEJARAT")
	@Temporal(TemporalType.DATE)
	private Date utlevelSzamLejarat;

	@Column(name="JOGOSITVANY_SZAM")
	private String jogositvanySzam;

	@Column(name = "JOGOSITVANY_SZAM_LEJARAT")
	@Temporal(TemporalType.DATE)
	private Date jogositvanySzamLejarat;

	@Column(name="TELEFON")
	private String telefon;

	@Column(name="MOBIL")
	private String mobil;

	@Column(name="EMAIL")
	private String email;

	@Column(name="LAKCIM_AKTUALIS")
	@Enumerated(EnumType.STRING)
	private LakcimAktualis lakcimAktualis;

	@Embedded
	@AttributeOverrides( {
        @AttributeOverride(name="iranyitoszam", column=@Column(name="LAKCIM_ALLANDO_IRANYITOSZAM")),
        @AttributeOverride(name="telepules", column=@Column(name="LAKCIM_ALLANDO_TELEPULES")),
        @AttributeOverride(name="kerulet", column=@Column(name="LAKCIM_ALLANDO_KERULET")),
        @AttributeOverride(name="kozteruletNev", column=@Column(name="LAKCIM_ALLANDO_KOZTERULET_NEV")),
        @AttributeOverride(name="kozteruletTipus", column=@Column(name="LAKCIM_ALLANDO_KOZTERULET_TIPUS")),
        @AttributeOverride(name="kozteruletSzam", column=@Column(name="LAKCIM_ALLANDO_KOZTERULET_SZAM")),
        @AttributeOverride(name="epulet", column=@Column(name="LAKCIM_ALLANDO_EPULET")),
        @AttributeOverride(name="lepcsohaz", column=@Column(name="LAKCIM_ALLANDO_LEPCSOHAZ")),
        @AttributeOverride(name="emelet", column=@Column(name="LAKCIM_ALLANDO_EMELET")),
        @AttributeOverride(name="ajto", column=@Column(name="LAKCIM_ALLANDO_AJTO"))
    })
	private Lakcim lakcimAllando;

	@Embedded
	@AttributeOverrides( {
        @AttributeOverride(name="iranyitoszam", column=@Column(name="LAKCIM_IDEIGLENES_IRANYITOSZAM")),
        @AttributeOverride(name="telepules", column=@Column(name="LAKCIM_IDEIGLENES_TELEPULES")),
        @AttributeOverride(name="kerulet", column=@Column(name="LAKCIM_IDEIGLENES_KERULET")),
        @AttributeOverride(name="kozteruletNev", column=@Column(name="LAKCIM_IDEIGLENES_KOZTERULET_NEV")),
        @AttributeOverride(name="kozteruletTipus", column=@Column(name="LAKCIM_IDEIGLENES_KOZTERULET_TIPUS")),
        @AttributeOverride(name="kozteruletSzam", column=@Column(name="LAKCIM_IDEIGLENES_KOZTERULET_SZAM")),
        @AttributeOverride(name="epulet", column=@Column(name="LAKCIM_IDEIGLENES_EPULET")),
        @AttributeOverride(name="lepcsohaz", column=@Column(name="LAKCIM_IDEIGLENES_LEPCSOHAZ")),
        @AttributeOverride(name="emelet", column=@Column(name="LAKCIM_IDEIGLENES_EMELET")),
        @AttributeOverride(name="ajto", column=@Column(name="LAKCIM_IDEIGLENES_AJTO"))
    })
	private Lakcim lakcimIdeiglenes;

	public SzemelyiAdatok(String vezeteknev, String keresztnev, Nem nem, Allampolgarsag allampolgarsag, Date szuletesiDatum, String szuletesiHely, String szuletesiOrszag, String szuletesiNev, String szuletesiNevAnyja, String adoazonositoJel, String taj, String szemelyiIgazolvanySzam, Date szemelyiIgazolvanySzamLejarat, String utlevelSzam, Date utlevelSzamLejarat, String jogositvanySzam, Date jogositvanySzamLejarat, String telefon, String mobil, String email, LakcimAktualis lakcimAktualis, Lakcim lakcimAllando, Lakcim lakcimIdeiglenes) {
		this.vezeteknev = vezeteknev;
		this.keresztnev = keresztnev;
		this.nem = nem;
		this.allampolgarsag = allampolgarsag;
		this.szuletesiDatum = szuletesiDatum;
		this.szuletesiHely = szuletesiHely;
		this.szuletesiOrszag = szuletesiOrszag;
		this.szuletesiNev = szuletesiNev;
		this.szuletesiNevAnyja = szuletesiNevAnyja;
		this.adoazonositoJel = adoazonositoJel;
		this.taj = taj;
		this.szemelyiIgazolvanySzam = szemelyiIgazolvanySzam;
		this.szemelyiIgazolvanySzamLejarat = szemelyiIgazolvanySzamLejarat;
		this.utlevelSzam = utlevelSzam;
		this.utlevelSzamLejarat = utlevelSzamLejarat;
		this.jogositvanySzam = jogositvanySzam;
		this.jogositvanySzamLejarat = jogositvanySzamLejarat;
		this.telefon = telefon;
		this.mobil = mobil;
		this.email = email;
		this.lakcimAktualis = lakcimAktualis;
		this.lakcimAllando = lakcimAllando;
		this.lakcimIdeiglenes = lakcimIdeiglenes;
	}

	public String getTeljesNev() {
		boolean vanVezeteknev = vezeteknev != null && !vezeteknev.isEmpty();
		boolean vanKeresztnev = keresztnev != null && !keresztnev.isEmpty();

		StringBuilder sb = new StringBuilder();

		if (vanVezeteknev) {
			sb.append(vezeteknev);
		}
		if (vanKeresztnev && vanVezeteknev) {
			sb.append(" ");
		}
		if (vanKeresztnev) {
			sb.append(keresztnev);
		}

		return sb.toString();
	}

	public String getVezeteknev() {
		return vezeteknev;
	}

	public String getKeresztnev() {
		return keresztnev;
	}

	public Nem getNem() {
		return nem;
	}

	public Allampolgarsag getAllampolgarsag() {
		return allampolgarsag;
	}

	public Date getSzuletesiDatum() {
		return szuletesiDatum;
	}

	public String getSzuletesiHely() {
		return szuletesiHely;
	}

	public String getSzuletesiOrszag() {
		return szuletesiOrszag;
	}

	public String getSzuletesiNev() {
		return szuletesiNev;
	}

	public String getSzuletesiNevAnyja() {
		return szuletesiNevAnyja;
	}

	public String getAdoazonositoJel() {
		return adoazonositoJel;
	}

	public String getTaj() {
		return taj;
	}

	public String getSzemelyiIgazolvanySzam() {
		return szemelyiIgazolvanySzam;
	}

	public Date getSzemelyiIgazolvanySzamLejarat() {
		return szemelyiIgazolvanySzamLejarat;
	}

	public String getUtlevelSzam() {
		return utlevelSzam;
	}

	public Date getUtlevelSzamLejarat() {
		return utlevelSzamLejarat;
	}

	public String getJogositvanySzam() {
		return jogositvanySzam;
	}

	public Date getJogositvanySzamLejarat() {
		return jogositvanySzamLejarat;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getMobil() {
		return mobil;
	}

	public String getEmail() {
		return email;
	}

	public LakcimAktualis getLakcimAktualis() {
		return lakcimAktualis;
	}

	public Lakcim getLakcimAllando() {
		return lakcimAllando;
	}

	public Lakcim getLakcimIdeiglenes() {
		return lakcimIdeiglenes;
	}

	@SuppressWarnings("unused")
	private SzemelyiAdatok() {
	}
}
