package hu.interconnect.hr.backend.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.backend.api.enumeration.CsaladiAllapot;
import hu.interconnect.hr.backend.api.enumeration.JogviszonyMegszunesenekModja;
import hu.interconnect.hr.backend.api.enumeration.KozteruletTipus;
import hu.interconnect.hr.backend.api.enumeration.LakcimAktualis;
import hu.interconnect.hr.backend.api.enumeration.MunkakorJellege;
import hu.interconnect.hr.backend.api.enumeration.Nem;

public class SzemelyitorzsCreateDTO {

	private int tsz;

	private SzemelyiAdatokDTO szemelyiAdatok;

	private MunkakoriBesorolasDTO munkakoriBesorolas;

	private JogviszonyAdatokDTO jogviszonyAdatok;

	private CsaladDTO csalad;

	private OrvosiVizsgalatDTO orvosiVizsgalat;

	private SzabadsagnyilvantartasDTO szabadsagnyilvantartas;
	
	public int getTsz() {
		return tsz;
	}

	public void setTsz(int tsz) {
		this.tsz = tsz;
	}

	public SzemelyiAdatokDTO getSzemelyiAdatok() {
		return szemelyiAdatok;
	}

	public void setSzemelyiAdatok(SzemelyiAdatokDTO szemelyiAdatok) {
		this.szemelyiAdatok = szemelyiAdatok;
	}

	public MunkakoriBesorolasDTO getMunkakoriBesorolas() {
		return munkakoriBesorolas;
	}

	public void setMunkakoriBesorolas(MunkakoriBesorolasDTO munkakoriBesorolas) {
		this.munkakoriBesorolas = munkakoriBesorolas;
	}

	public JogviszonyAdatokDTO getJogviszonyAdatok() {
		return jogviszonyAdatok;
	}

	public void setJogviszonyAdatok(JogviszonyAdatokDTO jogviszonyAdatok) {
		this.jogviszonyAdatok = jogviszonyAdatok;
	}

	public CsaladDTO getCsalad() {
		return csalad;
	}

	public void setCsalad(CsaladDTO csalad) {
		this.csalad = csalad;
	}

	public OrvosiVizsgalatDTO getOrvosiVizsgalat() {
		return orvosiVizsgalat;
	}

	public void setOrvosiVizsgalat(OrvosiVizsgalatDTO orvosiVizsgalat) {
		this.orvosiVizsgalat = orvosiVizsgalat;
	}

	public SzabadsagnyilvantartasDTO getSzabadsagnyilvantartas() {
		return szabadsagnyilvantartas;
	}

	public void setSzabadsagnyilvantartas(SzabadsagnyilvantartasDTO szabadsagnyilvantartas) {
		this.szabadsagnyilvantartas = szabadsagnyilvantartas;
	}

	public static class SzemelyiAdatokDTO {
		
		private String vezeteknev;

		private String keresztnev;

		private Nem nem;

		private Integer allampolgarsag;

		private Date szuletesiDatum;

		private String szuletesiHely;

		private String szuletesiOrszag;

		private String szuletesiNev;

		private String szuletesiNevAnyja;

		private String adoazonositoJel;

		private String taj;

		private String szemelyiIgazolvanySzam;

		private Date szemelyiIgazolvanySzamLejarat;

		private String utlevelSzam;

		private Date utlevelSzamLejarat;

		private String jogositvanySzam;

		private Date jogositvanySzamLejarat;

		private String telefon;

		private String mobil;
		
		private String email;

		private LakcimAktualis lakcimAktualis;

		private LakcimDTO lakcimAllando;

		private LakcimDTO lakcimIdeiglenes;
		
		public String getVezeteknev() {
			return vezeteknev;
		}

		public void setVezeteknev(String vezeteknev) {
			this.vezeteknev = vezeteknev;
		}

		public String getKeresztnev() {
			return keresztnev;
		}

		public void setKeresztnev(String keresztnev) {
			this.keresztnev = keresztnev;
		}

		public Nem getNem() {
			return nem;
		}

		public void setNem(Nem nem) {
			this.nem = nem;
		}

		public Integer getAllampolgarsag() {
			return allampolgarsag;
		}

		public void setAllampolgarsag(Integer allampolgarsag) {
			this.allampolgarsag = allampolgarsag;
		}

		public Date getSzuletesiDatum() {
			return szuletesiDatum;
		}

		public void setSzuletesiDatum(Date szuletesiDatum) {
			this.szuletesiDatum = szuletesiDatum;
		}

		public String getSzuletesiHely() {
			return szuletesiHely;
		}

		public void setSzuletesiHely(String szuletesiHely) {
			this.szuletesiHely = szuletesiHely;
		}

		public String getSzuletesiOrszag() {
			return szuletesiOrszag;
		}

		public void setSzuletesiOrszag(String szuletesiOrszag) {
			this.szuletesiOrszag = szuletesiOrszag;
		}

		public String getSzuletesiNev() {
			return szuletesiNev;
		}

		public void setSzuletesiNev(String szuletesiNev) {
			this.szuletesiNev = szuletesiNev;
		}

		public String getSzuletesiNevAnyja() {
			return szuletesiNevAnyja;
		}

		public void setSzuletesiNevAnyja(String szuletesiNevAnyja) {
			this.szuletesiNevAnyja = szuletesiNevAnyja;
		}

		public String getAdoazonositoJel() {
			return adoazonositoJel;
		}

		public void setAdoazonositoJel(String adoazonositoJel) {
			this.adoazonositoJel = adoazonositoJel;
		}

		public String getTaj() {
			return taj;
		}

		public void setTaj(String taj) {
			this.taj = taj;
		}

		public String getSzemelyiIgazolvanySzam() {
			return szemelyiIgazolvanySzam;
		}

		public void setSzemelyiIgazolvanySzam(String szemelyiIgazolvanySzam) {
			this.szemelyiIgazolvanySzam = szemelyiIgazolvanySzam;
		}

		public Date getSzemelyiIgazolvanySzamLejarat() {
			return szemelyiIgazolvanySzamLejarat;
		}

		public void setSzemelyiIgazolvanySzamLejarat(Date szemelyiIgazolvanySzamLejarat) {
			this.szemelyiIgazolvanySzamLejarat = szemelyiIgazolvanySzamLejarat;
		}

		public String getUtlevelSzam() {
			return utlevelSzam;
		}

		public void setUtlevelSzam(String utlevelSzam) {
			this.utlevelSzam = utlevelSzam;
		}

		public Date getUtlevelSzamLejarat() {
			return utlevelSzamLejarat;
		}

		public void setUtlevelSzamLejarat(Date utlevelSzamLejarat) {
			this.utlevelSzamLejarat = utlevelSzamLejarat;
		}

		public String getJogositvanySzam() {
			return jogositvanySzam;
		}

		public void setJogositvanySzam(String jogositvanySzam) {
			this.jogositvanySzam = jogositvanySzam;
		}

		public Date getJogositvanySzamLejarat() {
			return jogositvanySzamLejarat;
		}

		public void setJogositvanySzamLejarat(Date jogositvanySzamLejarat) {
			this.jogositvanySzamLejarat = jogositvanySzamLejarat;
		}

		public String getTelefon() {
			return telefon;
		}

		public void setTelefon(String telefon) {
			this.telefon = telefon;
		}

		public String getMobil() {
			return mobil;
		}

		public void setMobil(String mobil) {
			this.mobil = mobil;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public LakcimAktualis getLakcimAktualis() {
			return lakcimAktualis;
		}

		public void setLakcimAktualis(LakcimAktualis lakcimAktualis) {
			this.lakcimAktualis = lakcimAktualis;
		}

		public LakcimDTO getLakcimAllando() {
			return lakcimAllando;
		}

		public void setLakcimAllando(LakcimDTO lakcimAllando) {
			this.lakcimAllando = lakcimAllando;
		}

		public LakcimDTO getLakcimIdeiglenes() {
			return lakcimIdeiglenes;
		}

		public void setLakcimIdeiglenes(LakcimDTO lakcimIdeiglenes) {
			this.lakcimIdeiglenes = lakcimIdeiglenes;
		}

		public static class LakcimDTO {
			
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

			public String getIranyitoszam() {
				return iranyitoszam;
			}

			public void setIranyitoszam(String iranyitoszam) {
				this.iranyitoszam = iranyitoszam;
			}

			public String getTelepules() {
				return telepules;
			}

			public void setTelepules(String telepules) {
				this.telepules = telepules;
			}

			public String getKerulet() {
				return kerulet;
			}

			public void setKerulet(String kerulet) {
				this.kerulet = kerulet;
			}

			public String getKozteruletNev() {
				return kozteruletNev;
			}

			public void setKozteruletNev(String kozteruletNev) {
				this.kozteruletNev = kozteruletNev;
			}

			public KozteruletTipus getKozteruletTipus() {
				return kozteruletTipus;
			}

			public void setKozteruletTipus(KozteruletTipus kozteruletTipus) {
				this.kozteruletTipus = kozteruletTipus;
			}

			public String getKozteruletSzam() {
				return kozteruletSzam;
			}

			public void setKozteruletSzam(String kozteruletSzam) {
				this.kozteruletSzam = kozteruletSzam;
			}

			public String getEpulet() {
				return epulet;
			}

			public void setEpulet(String epulet) {
				this.epulet = epulet;
			}

			public String getLepcsohaz() {
				return lepcsohaz;
			}

			public void setLepcsohaz(String lepcsohaz) {
				this.lepcsohaz = lepcsohaz;
			}

			public String getEmelet() {
				return emelet;
			}

			public void setEmelet(String emelet) {
				this.emelet = emelet;
			}

			public String getAjto() {
				return ajto;
			}

			public void setAjto(String ajto) {
				this.ajto = ajto;
			}
		}
	}
	
	public static class MunkakoriBesorolasDTO {

		private Integer szervezetiEgyseg;
		
		private MunkakorJellege munkakorJellege;
		
		private Integer koltseghely;
		
		private Integer foglalkozasiViszony;
		
		private Integer foglalkoztatasJellege;
		
		private Integer fEOR;
		
		private Integer munkakor;
		
		private boolean uzemanyagElszamolas;
		
		private BigDecimal munkaidoNapi;
		
		private BigDecimal munkaidoHeti;

		public Integer getSzervezetiEgyseg() {
			return szervezetiEgyseg;
		}

		public void setSzervezetiEgyseg(Integer szervezetiEgyseg) {
			this.szervezetiEgyseg = szervezetiEgyseg;
		}

		public MunkakorJellege getMunkakorJellege() {
			return munkakorJellege;
		}

		public void setMunkakorJellege(MunkakorJellege munkakorJellege) {
			this.munkakorJellege = munkakorJellege;
		}

		public Integer getKoltseghely() {
			return koltseghely;
		}

		public void setKoltseghely(Integer koltseghely) {
			this.koltseghely = koltseghely;
		}

		public Integer getFoglalkozasiViszony() {
			return foglalkozasiViszony;
		}

		public void setFoglalkozasiViszony(Integer foglalkozasiViszony) {
			this.foglalkozasiViszony = foglalkozasiViszony;
		}

		public Integer getFoglalkoztatasJellege() {
			return foglalkoztatasJellege;
		}

		public void setFoglalkoztatasJellege(Integer foglalkoztatasJellege) {
			this.foglalkoztatasJellege = foglalkoztatasJellege;
		}

		public Integer getfEOR() {
			return fEOR;
		}

		public void setfEOR(Integer fEOR) {
			this.fEOR = fEOR;
		}

		public Integer getMunkakor() {
			return munkakor;
		}

		public void setMunkakor(Integer munkakor) {
			this.munkakor = munkakor;
		}

		public boolean isUzemanyagElszamolas() {
			return uzemanyagElszamolas;
		}

		public void setUzemanyagElszamolas(boolean uzemanyagElszamolas) {
			this.uzemanyagElszamolas = uzemanyagElszamolas;
		}

		public BigDecimal getMunkaidoNapi() {
			return munkaidoNapi;
		}

		public void setMunkaidoNapi(BigDecimal munkaidoNapi) {
			this.munkaidoNapi = munkaidoNapi;
		}

		public BigDecimal getMunkaidoHeti() {
			return munkaidoHeti;
		}

		public void setMunkaidoHeti(BigDecimal munkaidoHeti) {
			this.munkaidoHeti = munkaidoHeti;
		}
	}
	
	public static class JogviszonyAdatokDTO {

		private Date jogviszonyKezdete;
		
		private Date jogviszonyVege;
		
		private JogviszonyMegszunesenekModja jogviszonyMegszunesenekModja;
		
		private Date probaidoVege;
		
		private Date munkaszerzodesLejar;
		
		private Allomanymod allomanymod;

		public Date getJogviszonyKezdete() {
			return jogviszonyKezdete;
		}

		public void setJogviszonyKezdete(Date jogviszonyKezdete) {
			this.jogviszonyKezdete = jogviszonyKezdete;
		}

		public Date getJogviszonyVege() {
			return jogviszonyVege;
		}

		public void setJogviszonyVege(Date jogviszonyVege) {
			this.jogviszonyVege = jogviszonyVege;
		}

		public JogviszonyMegszunesenekModja getJogviszonyMegszunesenekModja() {
			return jogviszonyMegszunesenekModja;
		}

		public void setJogviszonyMegszunesenekModja(JogviszonyMegszunesenekModja jogviszonyMegszunesenekModja) {
			this.jogviszonyMegszunesenekModja = jogviszonyMegszunesenekModja;
		}

		public Date getProbaidoVege() {
			return probaidoVege;
		}

		public void setProbaidoVege(Date probaidoVege) {
			this.probaidoVege = probaidoVege;
		}

		public Date getMunkaszerzodesLejar() {
			return munkaszerzodesLejar;
		}

		public void setMunkaszerzodesLejar(Date munkaszerzodesLejar) {
			this.munkaszerzodesLejar = munkaszerzodesLejar;
		}

		public Allomanymod getAllomanymod() {
			return allomanymod;
		}

		public void setAllomanymod(Allomanymod allomanymod) {
			this.allomanymod = allomanymod;
		}
	}
	
	public static class CsaladDTO {
		
		private CsaladiAllapot csaladiAllapot;

		public CsaladiAllapot getCsaladiAllapot() {
			return csaladiAllapot;
		}

		public void setCsaladiAllapot(CsaladiAllapot csaladiAllapot) {
			this.csaladiAllapot = csaladiAllapot;
		}
	}
	
	public static class OrvosiVizsgalatDTO {
		
		private int gyakorisag;

		private Date utolsoOrvosiVizsgalatIdopontja;

		public int getGyakorisag() {
			return gyakorisag;
		}

		public void setGyakorisag(int gyakorisag) {
			this.gyakorisag = gyakorisag;
		}

		public Date getUtolsoOrvosiVizsgalatIdopontja() {
			return utolsoOrvosiVizsgalatIdopontja;
		}

		public void setUtolsoOrvosiVizsgalatIdopontja(Date utolsoOrvosiVizsgalatIdopontja) {
			this.utolsoOrvosiVizsgalatIdopontja = utolsoOrvosiVizsgalatIdopontja;
		}
	}
	
	public static class SzabadsagnyilvantartasDTO {
		
		private int megvaltottSzabadsag;

		public int getMegvaltottSzabadsag() {
			return megvaltottSzabadsag;
		}

		public void setMegvaltottSzabadsag(int megvaltottSzabadsag) {
			this.megvaltottSzabadsag = megvaltottSzabadsag;
		}
	}
}
