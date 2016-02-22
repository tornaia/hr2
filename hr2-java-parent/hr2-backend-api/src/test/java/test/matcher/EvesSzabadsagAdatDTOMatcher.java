package test.matcher;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO.ConsumptionTableDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO.ConsumptionTableDTO.ConsumptionPerTypePerMonthDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO.ConsumptionTableDTO.ConsumptionPerTypePerMonthDTO.AdottHaviEsAdottJelleguSzabadsagokDTO;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasResponseDTO;
import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;
import test.matcher.AbstractTypeSafeDiagnosingMatcher;

public class EvesSzabadsagAdatDTOMatcher extends AbstractTypeSafeDiagnosingMatcher<EvesSzabadsagAdatResponseDTO> {

	private Matcher<Integer> tsz = new IsAnything<>();
	
	private Matcher<Integer> ev = new IsAnything<>();
	
	private Matcher<Integer> alapszabadsag = new IsAnything<>();
	
	private Matcher<Integer> gyermekekUtan = new IsAnything<>();
	
	private Matcher<Integer> fiatalkoru = new IsAnything<>();
	
	private Matcher<Integer> vakSzemely = new IsAnything<>();
	
	private Matcher<Integer> egyebMunkakor = new IsAnything<>();
	
	private Matcher<Integer> ktKaPotszabadsag = new IsAnything<>();
	
	private Matcher<Integer> ktKaVezetoi = new IsAnything<>();
	
	private Matcher<Integer> egyebCsokkento = new IsAnything<>();
	
	private Matcher<Integer> tanulmanyi = new IsAnything<>();
	
	private Matcher<Integer> multEviSzabadsag = new IsAnything<>();
	
	private Matcher<Integer> bszJarandosagAdottEvi = new IsAnything<>();
	
	private Matcher<ConsumptionTableDTO> consumptionTableDTO = new IsAnything<>();
	
	public EvesSzabadsagAdatDTOMatcher tsz(Integer tsz) {
		this.tsz = CoreMatchers.is(tsz);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher ev(int ev) {
		this.ev = CoreMatchers.is(ev);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher alapszabadsag(int alapszabadsag) {
		this.alapszabadsag = CoreMatchers.is(alapszabadsag);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher gyermekekUtan(int gyermekekUtan) {
		this.gyermekekUtan = CoreMatchers.is(gyermekekUtan);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher fiatalkoru(int fiatalkoru) {
		this.fiatalkoru = CoreMatchers.is(fiatalkoru);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher vakSzemely(int vakSzemely) {
		this.vakSzemely = CoreMatchers.is(vakSzemely);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher egyebMunkakor(int egyebMunkakor) {
		this.egyebMunkakor = CoreMatchers.is(egyebMunkakor);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher ktKaPotszabadsag(int ktKaPotszabadsag) {
		this.ktKaPotszabadsag = CoreMatchers.is(ktKaPotszabadsag);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher ktKaVezetoi(int ktKaVezetoi) {
		this.ktKaVezetoi = CoreMatchers.is(ktKaVezetoi);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher egyebCsokkento(int egyebCsokkento) {
		this.egyebCsokkento = CoreMatchers.is(egyebCsokkento);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher tanulmanyi(int tanulmanyi) {
		this.tanulmanyi = CoreMatchers.is(tanulmanyi);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher multEviSzabadsag(int multEviSzabadsag) {
		this.multEviSzabadsag = CoreMatchers.is(multEviSzabadsag);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher bszJarandosagAdottEvi(int bszJarandosagAdottEvi) {
		this.bszJarandosagAdottEvi = CoreMatchers.is(bszJarandosagAdottEvi);
		return this;
	}
	
	public EvesSzabadsagAdatDTOMatcher consumptionTableDTO(Matcher<ConsumptionTableDTO> consumptionTableDTO) {
		this.consumptionTableDTO = consumptionTableDTO;
		return this;
	}
	
	@Override
	protected boolean matchesSafely(EvesSzabadsagAdatResponseDTO item, Description mismatchDescription) {
		return matches(tsz, item.tsz, "tsz value: ", mismatchDescription) &&
				matches(ev, item.ev, "ev value: ", mismatchDescription) &&
				matches(alapszabadsag, item.alapszabadsag, "alapszabadsag value: ", mismatchDescription) &&
				matches(gyermekekUtan, item.gyermekekUtan, "gyermekekUtan value: ", mismatchDescription) &&
				matches(fiatalkoru, item.fiatalkoru, "fiatalkoru value: ", mismatchDescription) &&
				matches(vakSzemely, item.vakSzemely, "vakSzemely value: ", mismatchDescription) &&
				matches(egyebMunkakor, item.egyebMunkakor, "egyebMunkakor value: ", mismatchDescription) &&
				matches(ktKaPotszabadsag, item.ktKaPotszabadsag, "ktKaPotszabadsag value: ", mismatchDescription) &&
				matches(ktKaVezetoi, item.ktKaVezetoi, "ktKaVezetoi value: ", mismatchDescription) &&
				matches(egyebCsokkento, item.egyebCsokkento, "egyebCsokkento value: ", mismatchDescription) &&
				matches(tanulmanyi, item.tanulmanyi, "tanulmanyi value: ", mismatchDescription) &&
				matches(multEviSzabadsag, item.multEviSzabadsag, "multEviSzabadsag value: ", mismatchDescription) &&
				matches(bszJarandosagAdottEvi, item.bszJarandosagAdottEvi, "bszJarandosagAdottEvi value: ", mismatchDescription) &&
				matches(consumptionTableDTO, item.consumptionTableDTO, "consumptionTableDTO value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(EvesSzabadsagAdatResponseDTO.class.getSimpleName())
        	.appendText(", tsz: ").appendDescriptionOf(tsz)
        	.appendText(", ev: ").appendDescriptionOf(ev)
        	.appendText(", alapszabadsag: ").appendDescriptionOf(alapszabadsag)
        	.appendText(", gyermekekUtan: ").appendDescriptionOf(gyermekekUtan)
        	.appendText(", fiatalkoru: ").appendDescriptionOf(fiatalkoru)
        	.appendText(", vakSzemely: ").appendDescriptionOf(vakSzemely)
        	.appendText(", egyebMunkakor: ").appendDescriptionOf(egyebMunkakor)
        	.appendText(", ktKaPotszabadsag: ").appendDescriptionOf(ktKaPotszabadsag)
        	.appendText(", ktKaVezetoi: ").appendDescriptionOf(ktKaVezetoi)
        	.appendText(", egyeb	Csokkento: ").appendDescriptionOf(egyebCsokkento)
        	.appendText(", tanulmanyi: ").appendDescriptionOf(tanulmanyi)
        	.appendText(", multEviSzabadsag: ").appendDescriptionOf(multEviSzabadsag)
        	.appendText(", bszJarandosagAdottEvi: ").appendDescriptionOf(bszJarandosagAdottEvi)
        	.appendText(", consumptionTableDTO: ").appendDescriptionOf(consumptionTableDTO);
	}
	
	public static class ConsumptionTableDTOMatcher extends AbstractTypeSafeDiagnosingMatcher<ConsumptionTableDTO> {

		private Matcher<Iterable<? extends ConsumptionPerTypePerMonthDTO>> rows = new IsAnything<>();
		
		public ConsumptionTableDTOMatcher rows(Matcher<Iterable<? extends ConsumptionPerTypePerMonthDTO>> rows) {
			this.rows = CoreMatchers.is(rows);
			return this;
		}
		
		@Override
		protected boolean matchesSafely(ConsumptionTableDTO item, Description mismatchDescription) {
			return matches(rows, item.rows, "rows value: ", mismatchDescription);
		}

		@Override
		public void describeTo(Description description) {
			description.appendText(ConsumptionTableDTO.class.getSimpleName())
	    	.appendText(", rows: ").appendDescriptionOf(rows);
		}
		
		public static class ConsumptionPerTypePerMonthDTOMatcher extends AbstractTypeSafeDiagnosingMatcher<ConsumptionPerTypePerMonthDTO> {

			private Matcher<FelhasznaltSzabadnapJelleg> jelleg = new IsAnything<>();
			
			private Matcher<Integer> january = new IsAnything<>();
			private Matcher<Integer> february = new IsAnything<>();
			private Matcher<Integer> march = new IsAnything<>();
			private Matcher<Integer> april = new IsAnything<>();
			private Matcher<Integer> may = new IsAnything<>();
			private Matcher<Integer> june = new IsAnything<>();
			private Matcher<Integer> july = new IsAnything<>();
			private Matcher<Integer> august = new IsAnything<>();
			private Matcher<Integer> september = new IsAnything<>();
			private Matcher<Integer> october = new IsAnything<>();
			private Matcher<Integer> november = new IsAnything<>();
			private Matcher<Integer> december = new IsAnything<>();
			
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> januaryDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> februaryDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> marchDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> aprilDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> mayDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> juneDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> julyDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> augustDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> septemberDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> octoberDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> novemberDetails = new IsAnything<>();
			private Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> decemberDetails = new IsAnything<>();
			
			public ConsumptionPerTypePerMonthDTOMatcher jelleg(FelhasznaltSzabadnapJelleg jelleg) {
				this.jelleg = CoreMatchers.is(jelleg);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher january(Integer january) {
				this.january = CoreMatchers.is(january);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher february(Integer february) {
				this.february = CoreMatchers.is(february);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher march(Integer march) {
				this.march = CoreMatchers.is(march);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher april(Integer april) {
				this.april = CoreMatchers.is(april);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher may(Integer may) {
				this.may = CoreMatchers.is(may);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher june(Integer june) {
				this.june = CoreMatchers.is(june);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher july(Integer july) {
				this.july = CoreMatchers.is(july);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher august(Integer august) {
				this.august = CoreMatchers.is(august);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher september(Integer september) {
				this.september = CoreMatchers.is(september);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher october(Integer october) {
				this.october = CoreMatchers.is(october);
				return this;
			}

			public ConsumptionPerTypePerMonthDTOMatcher november(Integer november) {
				this.november = CoreMatchers.is(november);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher december(Integer december) {
				this.december = CoreMatchers.is(december);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher januaryDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> januaryDetails) {
				this.januaryDetails = CoreMatchers.is(januaryDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher februaryDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> februaryDetails) {
				this.februaryDetails = CoreMatchers.is(februaryDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher marchDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> marchDetails) {
				this.marchDetails = CoreMatchers.is(marchDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher aprilDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> aprilDetails) {
				this.aprilDetails = CoreMatchers.is(aprilDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher mayDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> mayDetails) {
				this.mayDetails = CoreMatchers.is(mayDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher juneDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> juneDetails) {
				this.juneDetails = CoreMatchers.is(juneDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher julyDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> julyDetails) {
				this.julyDetails = CoreMatchers.is(julyDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher augustDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> augustDetails) {
				this.augustDetails = CoreMatchers.is(augustDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher septemberDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> septemberDetails) {
				this.septemberDetails = CoreMatchers.is(septemberDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher octoberDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> octoberDetails) {
				this.octoberDetails = CoreMatchers.is(octoberDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher novemberDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> novemberDetails) {
				this.novemberDetails = CoreMatchers.is(novemberDetails);
				return this;
			}
			
			public ConsumptionPerTypePerMonthDTOMatcher decemberDetails(Matcher<AdottHaviEsAdottJelleguSzabadsagokDTO> decemberDetails) {
				this.decemberDetails = CoreMatchers.is(decemberDetails);
				return this;
			}
			
			@Override
			protected boolean matchesSafely(ConsumptionPerTypePerMonthDTO item, Description mismatchDescription) {
				return matches(jelleg, item.jelleg, "jelleg value: ", mismatchDescription) &&
						matches(january, item.january, "january value: ", mismatchDescription) &&
						matches(february, item.february, "february value: ", mismatchDescription) &&
						matches(march, item.march, "march value: ", mismatchDescription) &&
						matches(april, item.april, "april value: ", mismatchDescription) &&
						matches(may, item.may, "may value: ", mismatchDescription) &&
						matches(june, item.june, "june value: ", mismatchDescription) &&
						matches(july, item.july, "july value: ", mismatchDescription) &&
						matches(august, item.august, "august value: ", mismatchDescription) &&
						matches(september, item.september, "september value: ", mismatchDescription) &&
						matches(october, item.october, "october value: ", mismatchDescription) &&
						matches(november, item.november, "november value: ", mismatchDescription) &&
						matches(december, item.december, "december value: ", mismatchDescription) &&
						matches(januaryDetails, item.januaryDetails, "januaryDetails value: ", mismatchDescription) &&
						matches(februaryDetails, item.februaryDetails, "februaryDetails value: ", mismatchDescription) &&
						matches(marchDetails, item.marchDetails, "marchDetails value: ", mismatchDescription) &&
						matches(aprilDetails, item.aprilDetails, "aprilDetails value: ", mismatchDescription) &&
						matches(mayDetails, item.mayDetails, "mayDetails value: ", mismatchDescription) &&
						matches(juneDetails, item.juneDetails, "juneDetails value: ", mismatchDescription) &&
						matches(julyDetails, item.julyDetails, "julyDetails value: ", mismatchDescription) &&
						matches(augustDetails, item.augustDetails, "augustDetails value: ", mismatchDescription) &&
						matches(septemberDetails, item.septemberDetails, "septemberDetails value: ", mismatchDescription) &&
						matches(octoberDetails, item.octoberDetails, "octoberDetails value: ", mismatchDescription) &&
						matches(novemberDetails, item.novemberDetails, "novemberDetails value: ", mismatchDescription) &&
						matches(decemberDetails, item.decemberDetails, "decemberDetails value: ", mismatchDescription);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText(ConsumptionPerTypePerMonthDTO.class.getSimpleName())
		    	.appendText(", jelleg: ").appendDescriptionOf(jelleg)
		    	.appendText(", january: ").appendDescriptionOf(january)
		    	.appendText(", february: ").appendDescriptionOf(february)
		    	.appendText(", march: ").appendDescriptionOf(march)
		    	.appendText(", april: ").appendDescriptionOf(april)
		    	.appendText(", may: ").appendDescriptionOf(may)
		    	.appendText(", june: ").appendDescriptionOf(june)
		    	.appendText(", july: ").appendDescriptionOf(july)
		    	.appendText(", august: ").appendDescriptionOf(august)
		    	.appendText(", september: ").appendDescriptionOf(september)
		    	.appendText(", october: ").appendDescriptionOf(october)
		    	.appendText(", november: ").appendDescriptionOf(november)
		    	.appendText(", december: ").appendDescriptionOf(december)
		    	.appendText(", januaryDetails: ").appendDescriptionOf(januaryDetails)
		    	.appendText(", februaryDetails: ").appendDescriptionOf(februaryDetails)
		    	.appendText(", marchDetails: ").appendDescriptionOf(marchDetails)
		    	.appendText(", aprilDetails: ").appendDescriptionOf(aprilDetails)
		    	.appendText(", mayDetails: ").appendDescriptionOf(mayDetails)
		    	.appendText(", juneDetails: ").appendDescriptionOf(juneDetails)
		    	.appendText(", julyDetails: ").appendDescriptionOf(julyDetails)
		    	.appendText(", augustDetails: ").appendDescriptionOf(augustDetails)
		    	.appendText(", septemberDetails: ").appendDescriptionOf(septemberDetails)
		    	.appendText(", octoberDetails: ").appendDescriptionOf(octoberDetails)
		    	.appendText(", novemberDetails: ").appendDescriptionOf(novemberDetails)
		    	.appendText(", decemberDetails: ").appendDescriptionOf(decemberDetails);
			}
			
			public static class AdottHaviEsAdottJelleguSzabadsagokDTOMatcher extends AbstractTypeSafeDiagnosingMatcher<AdottHaviEsAdottJelleguSzabadsagokDTO> {

				private Matcher<Iterable<? extends SzabadsagFelhasznalasResponseDTO>> reszletek = new IsAnything<>();
				
				public AdottHaviEsAdottJelleguSzabadsagokDTOMatcher reszletek(Matcher<Iterable<? extends SzabadsagFelhasznalasResponseDTO>> reszletek) {
					this.reszletek = CoreMatchers.is(reszletek);
					return this;
				}
				
				@Override
				protected boolean matchesSafely(AdottHaviEsAdottJelleguSzabadsagokDTO item, Description mismatchDescription) {
					return matches(reszletek, item.reszletek, "reszletek value: ", mismatchDescription);
				}

				@Override
				public void describeTo(Description description) {
					description.appendText(AdottHaviEsAdottJelleguSzabadsagokDTO.class.getSimpleName())
			    	.appendText(", reszletek: ").appendDescriptionOf(reszletek);
				}

			}
		}
	}
}