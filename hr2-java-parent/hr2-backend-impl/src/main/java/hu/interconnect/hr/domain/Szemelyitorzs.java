package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO.CsaladDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO.JogviszonyAdatokDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO.MunkakoriBesorolasDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO.OrvosiVizsgalatDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO.SzabadsagnyilvantartasDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO.SzemelyiAdatokDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO.SzemelyiAdatokDTO.LakcimDTO;

@Entity
@Table(name = "SZEMELYITORZS")
public class Szemelyitorzs extends AbstractEntity {

	@Id
	@Column(name = "TSZ")
	private Integer tsz;

	@Embedded
	private SzemelyiAdatok szemelyiAdatok;

	@Embedded
	private MunkakoriBesorolas munkakoriBesorolas;

	@Embedded
	private JogviszonyAdatok jogviszonyAdatok;

	@OrderBy(value = "id")
	@OneToMany(mappedBy = "szemelyitorzs", cascade = { CascadeType.REMOVE })
	private Set<Kepzettseg> kepzettsegek;

	@OrderBy(value = "id")
	@OneToMany(mappedBy = "szemelyitorzs", cascade = { CascadeType.REMOVE })
	private Set<AtvettEszkoz> atvettEszkozok;

	@Embedded
	private Csalad csalad;

	@Embedded
	private OrvosiVizsgalat orvosiVizsgalat;

	@Embedded
	private Szabadsagnyilvantartas szabadsagnyilvantartas;

	@OrderBy(value = "ev")
	@OneToMany(mappedBy = "szemelyitorzs", cascade = { CascadeType.REMOVE })
	private Set<EvesSzabadsagAdat> evesSzabadsagAdatok;

	@OrderBy(value = "datum")
	@OneToMany(mappedBy = "szemelyitorzs", cascade = { CascadeType.REMOVE })
	private Set<Szabadsag> szabadsagok;

	@OrderBy(value = "datum")
	@OneToMany(mappedBy = "szemelyitorzs", cascade = { CascadeType.REMOVE })
	private Set<JelenletiAdat> jelenletiAdatok;

	@OneToMany(mappedBy = "szemelyitorzs", cascade = { CascadeType.REMOVE })
	private Set<Fenykep> fenykepek;

	@OneToOne(mappedBy = "szemelyitorzs", cascade = { CascadeType.REMOVE })
	private Megjegyzes megjegyzes;

	public Szemelyitorzs(int tsz, SzemelyiAdatok szemelyiAdatok, MunkakoriBesorolas munkakoriBesorolas, JogviszonyAdatok jogviszonyAdatok, Csalad csalad, OrvosiVizsgalat orvosiVizsgalat, Szabadsagnyilvantartas szabadsagnyilvantartas) {
		this.tsz = tsz;
		this.szemelyiAdatok = szemelyiAdatok;
		this.munkakoriBesorolas = munkakoriBesorolas;
		this.jogviszonyAdatok = jogviszonyAdatok;
		this.csalad = csalad;
		this.orvosiVizsgalat = orvosiVizsgalat;
		this.szabadsagnyilvantartas = szabadsagnyilvantartas;
	}

	public void merge(SzemelyitorzsEditDTO dto) {
		checkArgument(equalsAndNotNull(tsz, dto.getTsz()));
		
		szemelyiAdatok = toDomain(dto.getSzemelyiAdatok());
		munkakoriBesorolas = toDomain(dto.getMunkakoriBesorolas());
		jogviszonyAdatok = toDomain(dto.getJogviszonyAdatok());
		csalad = toDomain(dto.getCsalad());
		orvosiVizsgalat = toDomain(dto.getOrvosiVizsgalat());
		szabadsagnyilvantartas = toDomain(dto.getSzabadsagnyilvantartas());
	}
	
	private static SzemelyiAdatok toDomain(SzemelyiAdatokDTO dto) {
		Allampolgarsag allampolgarsag = toAllampolgarsagDomain(dto.getAllampolgarsag());
		Lakcim lakcimAllando = toDomain(dto.getLakcimAllando());
		Lakcim lakcimIdeiglenes = toDomain(dto.getLakcimIdeiglenes());
		return new SzemelyiAdatok(dto.getVezeteknev(), dto.getKeresztnev(), dto.getNem(), allampolgarsag, dto.getSzuletesiDatum(), dto.getSzuletesiHely(), dto.getSzuletesiOrszag(), dto.getSzuletesiNev(), dto.getSzuletesiNevAnyja(), dto.getAdoazonositoJel(), dto.getTaj(), dto.getSzemelyiIgazolvanySzam(), dto.getSzemelyiIgazolvanySzamLejarat(), dto.getUtlevelSzam(), dto.getUtlevelSzamLejarat(), dto.getJogositvanySzam(), dto.getJogositvanySzamLejarat(), dto.getTelefon(), dto.getMobil(), dto.getEmail(), dto.getLakcimAktualis(), lakcimAllando, lakcimIdeiglenes);
	}
	
	private static MunkakoriBesorolas toDomain(MunkakoriBesorolasDTO dto) {
		SzervezetiEgyseg szervezetiEgyseg = toSzervezetiEgysegDomain(dto.getSzervezetiEgyseg());
		Koltseghely koltseghely = toKoltseghelyDomain(dto.getKoltseghely());
		FoglalkozasiViszony foglalkozasiViszony = toFoglalkozasiViszonyDomain(dto.getFoglalkozasiViszony());
		FoglalkoztatasJellege foglalkoztatasJellege = toFoglalkoztatasJellegeDomain(dto.getFoglalkoztatasJellege());
		FEOR fEOR = toFEORDomain(dto.getfEOR());
		Munkakor munkakor = toMunkakorDomain(dto.getMunkakor());
		return new MunkakoriBesorolas(szervezetiEgyseg, dto.getMunkakorJellege(), koltseghely, foglalkozasiViszony, foglalkoztatasJellege, fEOR, munkakor, dto.isUzemanyagElszamolas(), dto.getMunkaidoNapi(), dto.getMunkaidoHeti());
	}
	
	private static JogviszonyAdatok toDomain(JogviszonyAdatokDTO dto) {
		return new JogviszonyAdatok(dto.getJogviszonyKezdete(), dto.getJogviszonyVege(), dto.getJogviszonyMegszunesenekModja(), dto.getProbaidoVege(), dto.getMunkaszerzodesLejar(), dto.getAllomanymod());
	}
	
	private static Csalad toDomain(CsaladDTO csalad) {
		return new Csalad(csalad.getCsaladiAllapot());
	}
	
	private static OrvosiVizsgalat toDomain(OrvosiVizsgalatDTO orvosiVizsgalat) {
		return new OrvosiVizsgalat(orvosiVizsgalat.getGyakorisag(), orvosiVizsgalat.getUtolsoOrvosiVizsgalatIdopontja());
	}
	
	private static Szabadsagnyilvantartas toDomain(SzabadsagnyilvantartasDTO szabadsagnyilvantartas) {
		return new Szabadsagnyilvantartas(szabadsagnyilvantartas.getMegvaltottSzabadsag());
	}
	
	private static Allampolgarsag toAllampolgarsagDomain(Integer id) {
		return id != null ? new Allampolgarsag(id) : null;
	}
	
	private static SzervezetiEgyseg toSzervezetiEgysegDomain(Integer id) {
		return id != null ? new SzervezetiEgyseg(id) : null;
	}

	private static Koltseghely toKoltseghelyDomain(Integer id) {
		return id != null ? new Koltseghely(id) : null;
	}

	private static FoglalkozasiViszony toFoglalkozasiViszonyDomain(Integer id) {
		return id != null ? new FoglalkozasiViszony(id) : null;
	}

	private static FoglalkoztatasJellege toFoglalkoztatasJellegeDomain(Integer id) {
		return id != null ? new FoglalkoztatasJellege(id) : null;
	}

	private static FEOR toFEORDomain(Integer id) {
		return id != null ? new FEOR(id) : null;
	}

	private static Munkakor toMunkakorDomain(Integer id) {
		return id != null ? new Munkakor(id) : null;
	}
	
	private static Lakcim toDomain(LakcimDTO lakcim) {
		return lakcim != null ? new Lakcim(lakcim.getIranyitoszam(), lakcim.getTelepules(), lakcim.getKerulet(), lakcim.getKozteruletNev(), lakcim.getKozteruletTipus(), lakcim.getKozteruletSzam(), lakcim.getEpulet(), lakcim.getLepcsohaz(), lakcim.getEmelet(), lakcim.getAjto()) : null;
	}
	
	@Override
	public Integer getId() {
		return tsz;
	}
	
	public Integer getTsz() {
		return tsz;
	}
	
	public String getTeljesNev() {
		return szemelyiAdatok == null ? null : szemelyiAdatok.getTeljesNev();
	}

	public SzemelyiAdatok getSzemelyiAdatok() {
		return szemelyiAdatok;
	}

	public MunkakoriBesorolas getMunkakoriBesorolas() {
		return munkakoriBesorolas;
	}

	public JogviszonyAdatok getJogviszonyAdatok() {
		return jogviszonyAdatok;
	}

	public Set<Kepzettseg> getKepzettsegek() {
		return kepzettsegek;
	}

	public Set<AtvettEszkoz> getAtvettEszkozok() {
		return atvettEszkozok;
	}

	public Csalad getCsalad() {
		return csalad;
	}

	public OrvosiVizsgalat getOrvosiVizsgalat() {
		return orvosiVizsgalat;
	}

	public Szabadsagnyilvantartas getSzabadsagnyilvantartas() {
		return szabadsagnyilvantartas;
	}

	@SuppressWarnings("unused")
	private Szemelyitorzs() {
	}
}