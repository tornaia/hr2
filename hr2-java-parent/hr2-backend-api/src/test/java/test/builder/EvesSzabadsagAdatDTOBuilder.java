package test.builder;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO.ConsumptionTableDTO;

public class EvesSzabadsagAdatDTOBuilder extends Builder<EvesSzabadsagAdatResponseDTO>  {

	private Integer id;
	
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
	
	private ConsumptionTableDTO consumptionTableDTO;
	
	public EvesSzabadsagAdatDTOBuilder adottIdval(Integer id) {
		this.id = id;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottTsszel(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottEvvel(int ev) {
		this.ev = ev;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottAlapszabadsaggal(int alapszabadsag) {
		this.alapszabadsag = alapszabadsag;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottGyermekekUtan(int gyermekekUtan) {
		this.gyermekekUtan = gyermekekUtan;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottFiatalkoru(int fiatalkoru) {
		this.fiatalkoru = fiatalkoru;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottVakSzemely(int vakSzemely) {
		this.vakSzemely = vakSzemely;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottEgyebMunkakor(int egyebMunkakor) {
		this.egyebMunkakor = egyebMunkakor;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottKtKaPotszabadsag(int ktKaPotszabadsag) {
		this.ktKaPotszabadsag = ktKaPotszabadsag;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottKtKaVezetoi(int ktKaVezetoi) {
		this.ktKaVezetoi = ktKaVezetoi;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottEgyebCsokkento(int egyebCsokkento) {
		this.egyebCsokkento = egyebCsokkento;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottTanulmanyi(int tanulmanyi) {
		this.tanulmanyi = tanulmanyi;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottMultEviSzabadsag(int multEviSzabadsag) {
		this.multEviSzabadsag = multEviSzabadsag;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottBszJarandosagAdottEvi(int bszJarandosagAdottEvi) {
		this.bszJarandosagAdottEvi = bszJarandosagAdottEvi;
		return this;
	}
	
	public EvesSzabadsagAdatDTOBuilder adottConsumptionTableDTOval(ConsumptionTableDTO consumptionTableDTO) {
		this.consumptionTableDTO = consumptionTableDTO;
		return this;
	}
	
	@Override
	public EvesSzabadsagAdatResponseDTO letrehoz() {
		return new EvesSzabadsagAdatResponseDTO(id, tsz, ev, alapszabadsag, gyermekekUtan, fiatalkoru, vakSzemely, egyebMunkakor, ktKaPotszabadsag, ktKaVezetoi, egyebCsokkento, tanulmanyi, multEviSzabadsag, bszJarandosagAdottEvi, consumptionTableDTO);
	}
}
