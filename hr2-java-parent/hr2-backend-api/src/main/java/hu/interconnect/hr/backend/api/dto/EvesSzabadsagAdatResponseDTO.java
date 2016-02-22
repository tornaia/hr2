package hu.interconnect.hr.backend.api.dto;

import java.util.ArrayList;
import java.util.List;

import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;

public class EvesSzabadsagAdatResponseDTO {

	public Integer id;
	
	public int tsz;
	
	public int ev;
	
	public int alapszabadsag;
	
	public int gyermekekUtan;
	
	public int fiatalkoru;
	
	public int vakSzemely;
	
	public int egyebMunkakor;
	
	public int ktKaPotszabadsag;
	
	public int ktKaVezetoi;
	
	public int egyebCsokkento;
	
	public int tanulmanyi;
	
	public int multEviSzabadsag;
	
	public int bszJarandosagAdottEvi;
	
	public ConsumptionTableDTO consumptionTableDTO;
	
	public EvesSzabadsagAdatResponseDTO(Integer id, int tsz, int ev, int alapszabadsag, int gyermekekUtan, int fiatalkoru, int vakSzemely, int egyebMunkakor, int ktKaPotszabadsag, int ktKaVezetoi, int egyebCsokkento, int tanulmanyi, int multEviSzabadsag, int bszJarandosagAdottEvi, ConsumptionTableDTO consumptionTable) {
		this.id = id;
		this.tsz = tsz;
		this.ev = ev;
		this.alapszabadsag = alapszabadsag;
		this.gyermekekUtan = gyermekekUtan;
		this.fiatalkoru = fiatalkoru;
		this.vakSzemely = vakSzemely;
		this.egyebMunkakor = egyebMunkakor;
		this.ktKaPotszabadsag = ktKaPotszabadsag;
		this.ktKaVezetoi = ktKaVezetoi;
		this.egyebCsokkento = egyebCsokkento;
		this.tanulmanyi = tanulmanyi;
		this.multEviSzabadsag = multEviSzabadsag;
		this.bszJarandosagAdottEvi = bszJarandosagAdottEvi;
		this.consumptionTableDTO = consumptionTable;
	}
	
	public static class ConsumptionTableDTO {
		
		public List<ConsumptionPerTypePerMonthDTO> rows;
		
		public ConsumptionTableDTO(List<ConsumptionPerTypePerMonthDTO> rows) {
			this.rows = rows;
		}
		
		public static class ConsumptionPerTypePerMonthDTO {

			public FelhasznaltSzabadnapJelleg jelleg;
			public int january;
			public int february;
			public int march;
			public int april;
			public int may;
			public int june;
			public int july;
			public int august;
			public int september;
			public int october;
			public int november;
			public int december;

			public AdottHaviEsAdottJelleguSzabadsagokDTO januaryDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO februaryDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO marchDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO aprilDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO mayDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO juneDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO julyDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO augustDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO septemberDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO octoberDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO novemberDetails;
			public AdottHaviEsAdottJelleguSzabadsagokDTO decemberDetails;
			
			public ConsumptionPerTypePerMonthDTO(FelhasznaltSzabadnapJelleg jelleg, int january, int february, int march, int april, int may, int june, int july, int august, int september, int october, int november, int december) {
				this.jelleg = jelleg;
				this.january = january;
				this.february = february;
				this.march = march;
				this.april = april;
				this.may = may;
				this.june = june;
				this.july = july;
				this.august = august;
				this.september = september;
				this.october = october;
				this.november = november;
				this.december = december;
				this.januaryDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.februaryDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.marchDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.aprilDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.mayDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.juneDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.julyDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.augustDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.septemberDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.octoberDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.novemberDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
				this.decemberDetails = AdottHaviEsAdottJelleguSzabadsagokDTO.ures();
			}
			
			public static ConsumptionPerTypePerMonthDTO createEmpty(FelhasznaltSzabadnapJelleg jelleg) {
				return new ConsumptionPerTypePerMonthDTO(jelleg, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			}
			
			public static class AdottHaviEsAdottJelleguSzabadsagokDTO {

				public List<SzabadsagFelhasznalasResponseDTO> reszletek;
				
				public AdottHaviEsAdottJelleguSzabadsagokDTO(List<SzabadsagFelhasznalasResponseDTO> reszletek) {
					this.reszletek = reszletek;
				}
				
				private static AdottHaviEsAdottJelleguSzabadsagokDTO ures() {
					return new AdottHaviEsAdottJelleguSzabadsagokDTO(new ArrayList<>());
				}
			}
		}
	}
}
