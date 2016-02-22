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

public class SzemelyitorzsResponseDTO {

	public int tsz;

	public SzemelyiAdatokDTO szemelyiAdatok;

	public MunkakoriBesorolasDTO munkakoriBesorolas;

	public JogviszonyAdatokDTO jogviszonyAdatok;

	public CsaladDTO csalad;

	public OrvosiVizsgalatDTO orvosiVizsgalat;

	public SzabadsagnyilvantartasDTO szabadsagnyilvantartas;
	
	public SzemelyitorzsResponseDTO(int tsz, SzemelyiAdatokDTO szemelyiAdatok, MunkakoriBesorolasDTO munkakoriBesorolas, JogviszonyAdatokDTO jogviszonyAdatok, CsaladDTO csalad, OrvosiVizsgalatDTO orvosiVizsgalat, SzabadsagnyilvantartasDTO szabadsagnyilvantartas) {
		this.tsz = tsz;
		this.szemelyiAdatok = szemelyiAdatok;
		this.munkakoriBesorolas = munkakoriBesorolas;
		this.jogviszonyAdatok = jogviszonyAdatok;
		this.csalad = csalad;
		this.orvosiVizsgalat = orvosiVizsgalat;
		this.szabadsagnyilvantartas = szabadsagnyilvantartas;
	}
	
	public static class SzemelyiAdatokDTO {
		
		public String vezeteknev;

		public String keresztnev;

		public Nem nem;

		public Integer allampolgarsag;

		public Date szuletesiDatum;

		public String szuletesiHely;

		public String szuletesiOrszag;

		public String szuletesiNev;

		public String szuletesiNevAnyja;

		public String adoazonositoJel;

		public String taj;

		public String szemelyiIgazolvanySzam;

		public Date szemelyiIgazolvanySzamLejarat;

		public String utlevelSzam;

		public Date utlevelSzamLejarat;

		public String jogositvanySzam;

		public Date jogositvanySzamLejarat;

		public String telefon;

		public String mobil;
		
		public String email;

		public LakcimAktualis lakcimAktualis;

		public LakcimDTO lakcimAllando;

		public LakcimDTO lakcimIdeiglenes;
		
		public SzemelyiAdatokDTO(String vezeteknev, String keresztnev, Nem nem, Integer allampolgarsag, Date szuletesiDatum, String szuletesiHely, String szuletesiOrszag, String szuletesiNev, String szuletesiNevAnyja, String adoazonositoJel, String taj, String szemelyiIgazolvanySzam, Date szemelyiIgazolvanySzamLejarat, String utlevelSzam, Date utlevelSzamLejarat, String jogositvanySzam, Date jogositvanySzamLejarat, String telefon, String mobil, String email, LakcimAktualis lakcimAktualis, LakcimDTO lakcimAllando, LakcimDTO lakcimIdeiglenes) {
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
		
		public static class LakcimDTO {
			
			public String iranyitoszam;

			public String telepules;

			public String kerulet;

			public String kozteruletNev;

			public KozteruletTipus kozteruletTipus;

			public String kozteruletSzam;

			public String epulet;

			public String lepcsohaz;

			public String emelet;

			public String ajto;
			
			public LakcimDTO(String iranyitoszam, String telepules, String kerulet, String kozteruletNev, KozteruletTipus kozteruletTipus, String kozteruletSzam, String epulet, String lepcsohaz, String emelet, String ajto) {
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
		}
	}
	
	public static class MunkakoriBesorolasDTO {

		public Integer szervezetiEgyseg;
		
		public MunkakorJellege munkakorJellege;
		
		public Integer koltseghely;
		
		public Integer foglalkozasiViszony;
		
		public Integer foglalkoztatasJellege;
		
		public Integer fEOR;
		
		public Integer munkakor;
		
		public boolean uzemanyagElszamolas;
		
		public BigDecimal munkaidoNapi;
		
		public BigDecimal munkaidoHeti;
		
		public MunkakoriBesorolasDTO(Integer szervezetiEgyseg, MunkakorJellege munkakorJellege, Integer koltseghely, Integer foglalkozasiViszony, Integer foglalkoztatasJellege, Integer fEOR, Integer munkakor, boolean uzemanyagElszamolas, BigDecimal munkaidoNapi, BigDecimal munkaidoHeti) {
			this.szervezetiEgyseg = szervezetiEgyseg;
			this.munkakorJellege = munkakorJellege;
			this.koltseghely = koltseghely;
			this.foglalkozasiViszony = foglalkozasiViszony;
			this.foglalkoztatasJellege = foglalkoztatasJellege;
			this.fEOR = fEOR;
			this.munkakor = munkakor;
			this.uzemanyagElszamolas = uzemanyagElszamolas;
			this.munkaidoNapi = munkaidoNapi;
			this.munkaidoHeti = munkaidoHeti;
		}
	}
	
	public static class JogviszonyAdatokDTO {

		public Date jogviszonyKezdete;
		
		public Date jogviszonyVege;
		
		public JogviszonyMegszunesenekModja jogviszonyMegszunesenekModja;
		
		public Date probaidoVege;
		
		public Date munkaszerzodesLejar;
		
		public Allomanymod allomanymod;
		
		public JogviszonyAdatokDTO(Date jogviszonyKezdete, Date jogviszonyVege, JogviszonyMegszunesenekModja jogviszonyMegszunesenekModja, Date probaidoVege, Date munkaszerzodesLejar, Allomanymod allomanymod) {
			this.jogviszonyKezdete = jogviszonyKezdete;
			this.jogviszonyVege = jogviszonyVege;
			this.jogviszonyMegszunesenekModja = jogviszonyMegszunesenekModja;
			this.probaidoVege = probaidoVege;
			this.munkaszerzodesLejar = munkaszerzodesLejar;
			this.allomanymod = allomanymod;
		}
	}
	
	public static class CsaladDTO {
		
		public CsaladiAllapot csaladiAllapot;
		
		public CsaladDTO(CsaladiAllapot csaladiAllapot) {
			this.csaladiAllapot = csaladiAllapot;
		}
	}
	
	public static class OrvosiVizsgalatDTO {
		
		public int gyakorisag;

		public Date utolsoOrvosiVizsgalatIdopontja;
		
		public OrvosiVizsgalatDTO(int gyakorisag, Date utolsoOrvosiVizsgalatIdopontja) {
			this.gyakorisag = gyakorisag;
			this.utolsoOrvosiVizsgalatIdopontja = utolsoOrvosiVizsgalatIdopontja;
		}
	}
	
	public static class SzabadsagnyilvantartasDTO {
		
		public int megvaltottSzabadsag;
		
		public SzabadsagnyilvantartasDTO(int megvaltottSzabadsag) {
			this.megvaltottSzabadsag = megvaltottSzabadsag;
		}
	}
}
