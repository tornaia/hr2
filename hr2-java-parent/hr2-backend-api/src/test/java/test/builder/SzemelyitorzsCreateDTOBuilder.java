package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;

import java.math.BigDecimal;
import java.util.Date;

import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.CsaladDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.JogviszonyAdatokDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.MunkakoriBesorolasDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.OrvosiVizsgalatDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.SzabadsagnyilvantartasDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.SzemelyiAdatokDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO.SzemelyiAdatokDTO.LakcimDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.backend.api.enumeration.JogviszonyMegszunesenekModja;
import hu.interconnect.hr.backend.api.enumeration.KozteruletTipus;
import hu.interconnect.hr.backend.api.enumeration.LakcimAktualis;
import hu.interconnect.hr.backend.api.enumeration.MunkakorJellege;
import hu.interconnect.hr.backend.api.enumeration.Nem;

public class SzemelyitorzsCreateDTOBuilder extends Builder<SzemelyitorzsCreateDTO> {
	
	private Integer tsz;
	
	private SzemelyiAdatokDTO szemelyiAdatok = new SzemelyiAdatokDTO();
	
	private MunkakoriBesorolasDTO munkakoriBesorolas = new MunkakoriBesorolasDTO();
	
	private JogviszonyAdatokDTO jogviszonyAdatok = new JogviszonyAdatokDTO();
	
	private CsaladDTO csalad = new CsaladDTO();
	
	private OrvosiVizsgalatDTO orvosiVizsgalat = new OrvosiVizsgalatDTO();
	
	private SzabadsagnyilvantartasDTO szabadsagnyilvantartas = new SzabadsagnyilvantartasDTO();
	
	public SzemelyitorzsCreateDTOBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public SzemelyitorzsCreateDTOBuilder szemelyiAdatok(SzemelyiAdatokDTO szemelyiAdatok) {
		this.szemelyiAdatok = szemelyiAdatok;
		return this;
	}
	
	public SzemelyitorzsCreateDTOBuilder munkakoriBesorolas(MunkakoriBesorolasDTO munkakoriBesorolas) {
		this.munkakoriBesorolas = munkakoriBesorolas;
		return this;
	}
	
	public SzemelyitorzsCreateDTOBuilder jogviszonyAdatok(JogviszonyAdatokDTO jogviszonyAdatok) {
		this.jogviszonyAdatok = jogviszonyAdatok;
		return this;
	}
	
	public SzemelyitorzsCreateDTOBuilder orvosiVizsgalat(OrvosiVizsgalatDTO orvosiVizsgalat) {
		this.orvosiVizsgalat = orvosiVizsgalat;
		return this;
	}
	
	@Override
	public SzemelyitorzsCreateDTO letrehoz() {
		SzemelyitorzsCreateDTO dto = new SzemelyitorzsCreateDTO();
		dto.setTsz(tsz);
		dto.setSzemelyiAdatok(szemelyiAdatok);
		dto.setMunkakoriBesorolas(munkakoriBesorolas);
		dto.setJogviszonyAdatok(jogviszonyAdatok);
		dto.setCsalad(csalad);
		dto.setOrvosiVizsgalat(orvosiVizsgalat);
		dto.setSzabadsagnyilvantartas(szabadsagnyilvantartas);
		return dto;
	}
	
	public static class SzemelyiAdatokDTOBuilder extends Builder<SzemelyiAdatokDTO> {

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
		
		public SzemelyiAdatokDTOBuilder vezeteknev(String vezeteknev) {
			this.vezeteknev = vezeteknev;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder keresztnev(String keresztnev) {
			this.keresztnev = keresztnev;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder nem(Nem nem) {
			this.nem = nem;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder allampolgarsag(Integer allampolgarsag) {
			this.allampolgarsag = allampolgarsag;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder szuletesiDatum(String datum) {
			this.szuletesiDatum = parseNap(datum);
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder szuletesiHely(String szuletesiHely) {
			this.szuletesiHely = szuletesiHely;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder szuletesiOrszag(String szuletesiOrszag) {
			this.szuletesiOrszag = szuletesiOrszag;
			return this;
		}

		public SzemelyiAdatokDTOBuilder szuletesiNev(String szuletesiNev) {
			this.szuletesiNev = szuletesiNev;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder szuletesiNevAnyja(String szuletesiNevAnyja) {
			this.szuletesiNevAnyja = szuletesiNevAnyja;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder adoazonositoJel(String adoazonositoJel) {
			this.adoazonositoJel = adoazonositoJel;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder taj(String taj) {
			this.taj = taj;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder szemelyiIgazolvanySzam(String szemelyiIgazolvanySzam) {
			this.szemelyiIgazolvanySzam = szemelyiIgazolvanySzam;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder szemelyiIgazolvanySzamLejarat(String datum) {
			this.szemelyiIgazolvanySzamLejarat = parseNap(datum);
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder utlevelSzam(String utlevelSzam) {
			this.utlevelSzam = utlevelSzam;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder utlevelSzamLejarat(String datum) {
			this.utlevelSzamLejarat = parseNap(datum);
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder jogositvanySzam(String jogositvanySzam) {
			this.jogositvanySzam = jogositvanySzam;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder jogositvanySzamLejarat(String datum) {
			this.jogositvanySzamLejarat = parseNap(datum);
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder telefon(String telefon) {
			this.telefon = telefon;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder mobil(String mobil) {
			this.mobil = mobil;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder lakcimAktualis(LakcimAktualis lakcimAktualis) {
			this.lakcimAktualis = lakcimAktualis;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder lakcimAllando(LakcimDTO allandoLakcim) {
			this.lakcimAllando = allandoLakcim;
			return this;
		}
		
		public SzemelyiAdatokDTOBuilder lakcimIdeiglenes(LakcimDTO ideiglenesLakcim) {
			this.lakcimIdeiglenes = ideiglenesLakcim;
			return this;
		}
		
		@Override
		public SzemelyiAdatokDTO letrehoz() {
			SzemelyiAdatokDTO szemelyiAdatokDTO = new SzemelyiAdatokDTO();
			szemelyiAdatokDTO.setVezeteknev(vezeteknev);
			szemelyiAdatokDTO.setKeresztnev(keresztnev);
			szemelyiAdatokDTO.setNem(nem);
			szemelyiAdatokDTO.setAllampolgarsag(allampolgarsag);
			szemelyiAdatokDTO.setSzuletesiDatum(szuletesiDatum);
			szemelyiAdatokDTO.setSzuletesiHely(szuletesiHely);
			szemelyiAdatokDTO.setSzuletesiOrszag(szuletesiOrszag);
			szemelyiAdatokDTO.setSzuletesiNev(szuletesiNev);
			szemelyiAdatokDTO.setSzuletesiNevAnyja(szuletesiNevAnyja);
			szemelyiAdatokDTO.setAdoazonositoJel(adoazonositoJel);
			szemelyiAdatokDTO.setTaj(taj);
			szemelyiAdatokDTO.setSzemelyiIgazolvanySzam(szemelyiIgazolvanySzam);
			szemelyiAdatokDTO.setSzemelyiIgazolvanySzamLejarat(szemelyiIgazolvanySzamLejarat);
			szemelyiAdatokDTO.setUtlevelSzam(utlevelSzam);
			szemelyiAdatokDTO.setUtlevelSzamLejarat(utlevelSzamLejarat);
			szemelyiAdatokDTO.setJogositvanySzam(jogositvanySzam);
			szemelyiAdatokDTO.setJogositvanySzamLejarat(jogositvanySzamLejarat);
			szemelyiAdatokDTO.setTelefon(telefon);
			szemelyiAdatokDTO.setMobil(mobil);
			szemelyiAdatokDTO.setEmail(email);
			szemelyiAdatokDTO.setLakcimAktualis(lakcimAktualis);
			szemelyiAdatokDTO.setLakcimAllando(lakcimAllando);
			szemelyiAdatokDTO.setLakcimIdeiglenes(lakcimIdeiglenes);
			return szemelyiAdatokDTO;
		}
		
		public static class LakcimDTOBuilder extends Builder<LakcimDTO>  {

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
			
			public LakcimDTOBuilder iranyitoszam(String iranyitoszam) {
				this.iranyitoszam = iranyitoszam;
				return this;
			}
			
			public LakcimDTOBuilder telepules(String telepules) {
				this.telepules = telepules;
				return this;
			}
			
			public LakcimDTOBuilder kerulet(String kerulet) {
				this.kerulet = kerulet;
				return this;
			}
			
			public LakcimDTOBuilder kozteruletNev(String kozteruletNev) {
				this.kozteruletNev = kozteruletNev;
				return this;
			}
			
			public LakcimDTOBuilder kozteruletTipus(KozteruletTipus kozteruletTipus) {
				this.kozteruletTipus = kozteruletTipus;
				return this;
			}
			
			public LakcimDTOBuilder kozteruletSzam(String kozteruletSzam) {
				this.kozteruletSzam = kozteruletSzam;
				return this;
			}
			
			public LakcimDTOBuilder epulet(String epulet) {
				this.epulet = epulet;
				return this;
			}
			
			public LakcimDTOBuilder lepcsohaz(String lepcsohaz) {
				this.lepcsohaz = lepcsohaz;
				return this;
			}
			
			public LakcimDTOBuilder emelet(String emelet) {
				this.emelet = emelet;
				return this;
			}
			
			public LakcimDTOBuilder ajto(String ajto) {
				this.ajto = ajto;
				return this;
			}
			
			@Override
			public LakcimDTO letrehoz() {
				LakcimDTO lakcimDTO = new LakcimDTO();
				lakcimDTO.setIranyitoszam(iranyitoszam);
				lakcimDTO.setTelepules(telepules);
				lakcimDTO.setKerulet(kerulet);
				lakcimDTO.setKozteruletNev(kozteruletNev);
				lakcimDTO.setKozteruletTipus(kozteruletTipus);
				lakcimDTO.setKozteruletSzam(kozteruletSzam);
				lakcimDTO.setEpulet(epulet);
				lakcimDTO.setLepcsohaz(lepcsohaz);
				lakcimDTO.setEmelet(emelet);
				lakcimDTO.setAjto(ajto);
				return lakcimDTO;
			}
		}
	}
	

	public static class JogviszonyAdatokDTOBuilder extends Builder<JogviszonyAdatokDTO>  {
	
		private Date jogviszonyKezdete;
		
		private Date jogviszonyVege;
		
		private JogviszonyMegszunesenekModja jogviszonyMegszunesenekModja;
		
		private Date probaidoVege;
		
		private Date munkaszerzodesLejar;
		
		private Allomanymod allomanymod;
		
		public JogviszonyAdatokDTOBuilder jogviszonyKezdete(String datum) {
			this.jogviszonyKezdete = parseNap(datum);
			return this;
		}
		
		public JogviszonyAdatokDTOBuilder jogviszonyVege(String datum) {
			this.jogviszonyVege = parseNap(datum);
			return this;
		}
		
		public JogviszonyAdatokDTOBuilder probaidoVege(String datum) {
			this.probaidoVege = parseNap(datum);
			return this;
		}
		
		public JogviszonyAdatokDTOBuilder allomanymod(Allomanymod allomanymod) {
			this.allomanymod = allomanymod;
			return this;
		}
		
		@Override
		public JogviszonyAdatokDTO letrehoz() {
			JogviszonyAdatokDTO jogviszonyAdatokDTO = new JogviszonyAdatokDTO();
			jogviszonyAdatokDTO.setJogviszonyKezdete(jogviszonyKezdete);
			jogviszonyAdatokDTO.setJogviszonyVege(jogviszonyVege);
			jogviszonyAdatokDTO.setJogviszonyMegszunesenekModja(jogviszonyMegszunesenekModja);
			jogviszonyAdatokDTO.setProbaidoVege(probaidoVege);
			jogviszonyAdatokDTO.setMunkaszerzodesLejar(munkaszerzodesLejar);
			jogviszonyAdatokDTO.setAllomanymod(allomanymod);
			return jogviszonyAdatokDTO;
		}
	}
	
	public static class MunkakoriBesorolasDTOBuilder extends Builder<MunkakoriBesorolasDTO>  {

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
		
		public MunkakoriBesorolasDTOBuilder koltseghely(Integer koltseghely) {
			this.koltseghely = koltseghely;
			return this;
		}

		public MunkakoriBesorolasDTOBuilder munkakor(Integer munkakor) {
			this.munkakor = munkakor;
			return this;
		}
		
		@Override
		public MunkakoriBesorolasDTO letrehoz() {
			MunkakoriBesorolasDTO munkakoriBesorolasDTO = new MunkakoriBesorolasDTO();
			munkakoriBesorolasDTO.setSzervezetiEgyseg(szervezetiEgyseg);
			munkakoriBesorolasDTO.setMunkakorJellege(munkakorJellege);
			munkakoriBesorolasDTO.setKoltseghely(koltseghely);
			munkakoriBesorolasDTO.setFoglalkozasiViszony(foglalkozasiViszony);
			munkakoriBesorolasDTO.setFoglalkoztatasJellege(foglalkoztatasJellege);
			munkakoriBesorolasDTO.setfEOR(fEOR);
			munkakoriBesorolasDTO.setMunkakor(munkakor);
			munkakoriBesorolasDTO.setUzemanyagElszamolas(uzemanyagElszamolas);
			munkakoriBesorolasDTO.setMunkaidoNapi(munkaidoNapi);
			munkakoriBesorolasDTO.setMunkaidoHeti(munkaidoHeti);
			return munkakoriBesorolasDTO;
		}
	}
	
	public static class OrvosiVizsgalatDTOBuilder extends Builder<OrvosiVizsgalatDTO> {

		private Integer gyakorisag;
		
		private Date utolsoOrvosiVizsgalatIdopontja;
		
		public OrvosiVizsgalatDTOBuilder gyakorisag(Integer gyakorisag) {
			this.gyakorisag = gyakorisag;
			return this;
		}
		
		public OrvosiVizsgalatDTOBuilder utolsoOrvosiVizsgalatIdopontja(String datum) {
			this.utolsoOrvosiVizsgalatIdopontja = parseNap(datum);
			return this;
		}
		
		@Override
		public OrvosiVizsgalatDTO letrehoz() {
			OrvosiVizsgalatDTO orvosiVizsgalatDTO = new OrvosiVizsgalatDTO();
			orvosiVizsgalatDTO.setGyakorisag(gyakorisag);
			orvosiVizsgalatDTO.setUtolsoOrvosiVizsgalatIdopontja(utolsoOrvosiVizsgalatIdopontja);
			return orvosiVizsgalatDTO;
		}
	}
}
