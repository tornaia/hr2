package test.matcher;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasokResponseDTO;
import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasokResponseDTO.IdozitettFuttatasDTO;
import test.matcher.AbstractTypeSafeDiagnosingMatcher;
import test.matcher.TimestampMatcher;

public class IdozitettFuttatasokResponseDTOMatcher extends AbstractTypeSafeDiagnosingMatcher<IdozitettFuttatasokResponseDTO> {

	private Matcher<Iterable<? extends IdozitettFuttatasDTO>> idozitettFuttatasok = new IsAnything<>();

	public IdozitettFuttatasokResponseDTOMatcher idozitettFuttatasok(Matcher<Iterable<? extends IdozitettFuttatasDTO>> idozitettFuttatasok) {
		this.idozitettFuttatasok = CoreMatchers.is(idozitettFuttatasok);
		return this;
	}
	
	@Override
	protected boolean matchesSafely(IdozitettFuttatasokResponseDTO item, Description mismatchDescription) {
		return matches(idozitettFuttatasok, item.idozitettFuttatasok, "idozitettFuttatasok value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(IdozitettFuttatasokResponseDTO.class.getSimpleName())
    	.appendText(", idozitettFuttatasok: ").appendDescriptionOf(idozitettFuttatasok);
	}
	
	public static class IdozitettFuttatasDTOMatcher extends AbstractTypeSafeDiagnosingMatcher<IdozitettFuttatasDTO> {

		private Matcher<String> azonosito = new IsAnything<>();
		
		private Matcher<Date> inditas = new IsAnything<>();
		
		private Matcher<Date> befejezes = new IsAnything<>();
		
		private Matcher<String> eredmeny = new IsAnything<>();
		
		public IdozitettFuttatasDTOMatcher azonosito(String azonosito) {
			this.azonosito = CoreMatchers.is(azonosito);
			return this;
		}
		
		public IdozitettFuttatasDTOMatcher inditas(String inditas) {
			this.inditas = new TimestampMatcher(inditas);
			return this;
		}
		
		public IdozitettFuttatasDTOMatcher befejezes(String befejezes) {
			this.befejezes = new TimestampMatcher(befejezes);
			return this;
		}
		
		public IdozitettFuttatasDTOMatcher eredmeny(String eredmeny) {
			this.eredmeny = CoreMatchers.is(eredmeny);
			return this;
		}
		
		@Override
		protected boolean matchesSafely(IdozitettFuttatasDTO item, Description mismatchDescription) {
			return matches(azonosito, item.azonosito, "azonosito value: ", mismatchDescription) &&
					matches(inditas, item.inditas, "inditas value: ", mismatchDescription) &&
					matches(befejezes, item.befejezes, "befejezes value: ", mismatchDescription) &&
					matches(eredmeny, item.eredmeny, "eredmeny value: ", mismatchDescription);
		}

		@Override
		public void describeTo(Description description) {
			description.appendText(IdozitettFuttatasokResponseDTO.class.getSimpleName())
	    	.appendText(", azonosito: ").appendDescriptionOf(azonosito)
	    	.appendText(", inditas: ").appendDescriptionOf(inditas)
	    	.appendText(", befejezes: ").appendDescriptionOf(befejezes)
	    	.appendText(", eredmeny: ").appendDescriptionOf(eredmeny);
		}
	}
}
