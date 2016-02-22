package test.builder;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatCreateDTO;

public class EvesSzabadsagAdatCreateDTOBuilder extends Builder<EvesSzabadsagAdatCreateDTO>  {

	private int tsz;
	
	private int ev;
	
	private int alapszabadsag;
	
	private int gyermekekUtan;
	
	private int fiatalkoru;
	
	private int vakSzemely;
	
	private int egyebMunkakor;
	
	private int ktKaPotszabadsag;
	
	private int ktKaVezetoi;
	
	private int egyebCsokkento;
	
	private int tanulmanyi;
	
	private int multEviSzabadsag;
	
	private int bszJarandosagAdottEvi;
	
	public EvesSzabadsagAdatCreateDTOBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder ev(int ev) {
		this.ev = ev;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder alapszabadsag(int alapszabadsag) {
		this.alapszabadsag = alapszabadsag;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder gyermekekUtan(int gyermekekUtan) {
		this.gyermekekUtan = gyermekekUtan;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder fiatalkoru(int fiatalkoru) {
		this.fiatalkoru = fiatalkoru;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder vakSzemely(int vakSzemely) {
		this.vakSzemely = vakSzemely;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder egyebMunkakor(int egyebMunkakor) {
		this.egyebMunkakor = egyebMunkakor;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder ktKaPotszabadsag(int ktKaPotszabadsag) {
		this.ktKaPotszabadsag = ktKaPotszabadsag;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder ktKaVezetoi(int ktKaVezetoi) {
		this.ktKaVezetoi = ktKaVezetoi;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder egyebCsokkento(int egyebCsokkento) {
		this.egyebCsokkento = egyebCsokkento;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder tanulmanyi(int tanulmanyi) {
		this.tanulmanyi = tanulmanyi;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder multEviSzabadsag(int multEviSzabadsag) {
		this.multEviSzabadsag = multEviSzabadsag;
		return this;
	}
	
	public EvesSzabadsagAdatCreateDTOBuilder bszJarandosagAdottEvi(int bszJarandosagAdottEvi) {
		this.bszJarandosagAdottEvi = bszJarandosagAdottEvi;
		return this;
	}
	
	@Override
	public EvesSzabadsagAdatCreateDTO letrehoz() {
		EvesSzabadsagAdatCreateDTO evesSzabadsagAdatCreateDTO = new EvesSzabadsagAdatCreateDTO();
		evesSzabadsagAdatCreateDTO.setTsz(tsz);
		evesSzabadsagAdatCreateDTO.setEv(ev);
		evesSzabadsagAdatCreateDTO.setAlapszabadsag(alapszabadsag);
		evesSzabadsagAdatCreateDTO.setGyermekekUtan(gyermekekUtan);
		evesSzabadsagAdatCreateDTO.setFiatalkoru(fiatalkoru);
		evesSzabadsagAdatCreateDTO.setVakSzemely(vakSzemely);
		evesSzabadsagAdatCreateDTO.setEgyebMunkakor(egyebMunkakor);
		evesSzabadsagAdatCreateDTO.setKtKaPotszabadsag(ktKaPotszabadsag);
		evesSzabadsagAdatCreateDTO.setKtKaVezetoi(ktKaVezetoi);
		evesSzabadsagAdatCreateDTO.setEgyebCsokkento(egyebCsokkento);
		evesSzabadsagAdatCreateDTO.setTanulmanyi(tanulmanyi);
		evesSzabadsagAdatCreateDTO.setMultEviSzabadsag(multEviSzabadsag);
		evesSzabadsagAdatCreateDTO.setBszJarandosagAdottEvi(bszJarandosagAdottEvi);
		return evesSzabadsagAdatCreateDTO;
	}
}
