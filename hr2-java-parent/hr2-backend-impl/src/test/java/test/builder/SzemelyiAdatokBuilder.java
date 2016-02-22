package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.backend.api.enumeration.LakcimAktualis;
import hu.interconnect.hr.backend.api.enumeration.Nem;
import hu.interconnect.hr.domain.Allampolgarsag;
import hu.interconnect.hr.domain.Lakcim;
import hu.interconnect.hr.domain.SzemelyiAdatok;

public class SzemelyiAdatokBuilder extends Builder<SzemelyiAdatok> {

	private String vezeteknev;
	
	private String keresztnev;
	
	private Nem nem;
	
	private Allampolgarsag allampolgarsag;
	
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
	
	private Lakcim lakcimAllando;
	
	private Lakcim lakcimIdeiglenes;
	
	public SzemelyiAdatokBuilder vezeteknev(String vezeteknev) {
		this.vezeteknev = vezeteknev;
		return this;
	}
	
	public SzemelyiAdatokBuilder keresztnev(String keresztnev) {
		this.keresztnev = keresztnev;
		return this;
	}
	
	public SzemelyiAdatokBuilder nem(Nem nem) {
		this.nem = nem;
		return this;
	}
	
	public SzemelyiAdatokBuilder allampolgarsag(Allampolgarsag allampolgarsag) {
		this.allampolgarsag = allampolgarsag;
		return this;
	}
	
	public SzemelyiAdatokBuilder szuletesiDatum(String datum) {
		this.szuletesiDatum = parseNap(datum);
		return this;
	}
	
	public SzemelyiAdatokBuilder szuletesiHely(String szuletesiHely) {
		this.szuletesiHely = szuletesiHely;
		return this;
	}
	
	public SzemelyiAdatokBuilder szuletesiOrszag(String szuletesiOrszag) {
		this.szuletesiOrszag = szuletesiOrszag;
		return this;
	}

	public SzemelyiAdatokBuilder szuletesiNev(String szuletesiNev) {
		this.szuletesiNev = szuletesiNev;
		return this;
	}
	
	public SzemelyiAdatokBuilder szuletesiNevAnyja(String szuletesiNevAnyja) {
		this.szuletesiNevAnyja = szuletesiNevAnyja;
		return this;
	}
	
	public SzemelyiAdatokBuilder adoazonositoJel(String adoazonositoJel) {
		this.adoazonositoJel = adoazonositoJel;
		return this;
	}
	
	public SzemelyiAdatokBuilder taj(String taj) {
		this.taj = taj;
		return this;
	}
	
	public SzemelyiAdatokBuilder szemelyiIgazolvanySzam(String szemelyiIgazolvanySzam) {
		this.szemelyiIgazolvanySzam = szemelyiIgazolvanySzam;
		return this;
	}
	
	public SzemelyiAdatokBuilder szemelyiIgazolvanySzamLejarat(String datum) {
		this.szemelyiIgazolvanySzamLejarat = parseNap(datum);
		return this;
	}
	
	public SzemelyiAdatokBuilder utlevelSzam(String utlevelSzam) {
		this.utlevelSzam = utlevelSzam;
		return this;
	}
	
	public SzemelyiAdatokBuilder utlevelSzamLejarat(String datum) {
		this.utlevelSzamLejarat = parseNap(datum);
		return this;
	}
	
	public SzemelyiAdatokBuilder jogositvanySzam(String jogositvanySzam) {
		this.jogositvanySzam = jogositvanySzam;
		return this;
	}
	
	public SzemelyiAdatokBuilder jogositvanySzamLejarat(String datum) {
		this.jogositvanySzamLejarat = parseNap(datum);
		return this;
	}
	
	public SzemelyiAdatokBuilder telefon(String telefon) {
		this.telefon = telefon;
		return this;
	}
	
	public SzemelyiAdatokBuilder mobil(String mobil) {
		this.mobil = mobil;
		return this;
	}
	
	public SzemelyiAdatokBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public SzemelyiAdatokBuilder lakcimAktualis(LakcimAktualis lakcimAktualis) {
		this.lakcimAktualis = lakcimAktualis;
		return this;
	}
	
	public SzemelyiAdatokBuilder lakcimAllando(Lakcim allandoLakcim) {
		this.lakcimAllando = allandoLakcim;
		return this;
	}
	
	public SzemelyiAdatokBuilder lakcimIdeiglenes(Lakcim ideiglenesLakcim) {
		this.lakcimIdeiglenes = ideiglenesLakcim;
		return this;
	}
	
	@Override
	public SzemelyiAdatok letrehoz() {
		return new SzemelyiAdatok(vezeteknev, keresztnev, nem, allampolgarsag, szuletesiDatum, szuletesiHely, szuletesiOrszag, szuletesiNev, szuletesiNevAnyja, adoazonositoJel, taj, szemelyiIgazolvanySzam, szemelyiIgazolvanySzamLejarat, utlevelSzam, utlevelSzamLejarat, jogositvanySzam, jogositvanySzamLejarat, telefon, mobil, email, lakcimAktualis, lakcimAllando, lakcimIdeiglenes);
	}
}
