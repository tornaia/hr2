package test.matcher;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import hu.interconnect.hr.domain.IdozitettFuttatas;
import test.matcher.AbstractTypeSafeDiagnosingMatcher;
import test.matcher.TimestampMatcher;

public class IdozitettFuttatasMatcher extends AbstractTypeSafeDiagnosingMatcher<IdozitettFuttatas> {

	private Matcher<String> azonosito = new IsAnything<>();

	private Matcher<Date> inditas = new IsAnything<>();

	private Matcher<Date> befejezes = new IsAnything<>();

	private Matcher<String> eredmeny = new IsAnything<>();

	public IdozitettFuttatasMatcher azonosito(String azonosito) {
		this.azonosito = CoreMatchers.is(azonosito);
		return this;
	}

	public IdozitettFuttatasMatcher inditas(String inditasStr) {
		this.inditas = new TimestampMatcher(inditasStr);
		return this;
	}

	public IdozitettFuttatasMatcher befejezes(String befejezesStr) {
		this.befejezes = new TimestampMatcher(befejezesStr);
		return this;
	}

	public IdozitettFuttatasMatcher eredmeny(String eredmeny) {
		this.eredmeny = CoreMatchers.is(eredmeny);
		return this;
	}

	@Override
	protected boolean matchesSafely(IdozitettFuttatas item, Description mismatchDescription) {
		return matches(azonosito, item.getAzonosito(), "azonosito value: ", mismatchDescription) &&
				matches(inditas, item.getInditas(), "inditas value: ", mismatchDescription) &&
				matches(befejezes, item.getBefejezes(), "befejezes value: ", mismatchDescription) &&
				matches(eredmeny, item.getEredmeny(), "eredmeny value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(IdozitettFuttatas.class.getSimpleName())
    	.appendText(", azonosito: ").appendDescriptionOf(azonosito)
    	.appendText(", inditas: ").appendDescriptionOf(inditas)
    	.appendText(", befejezes: ").appendDescriptionOf(befejezes)
    	.appendText(", eredmeny: ").appendDescriptionOf(eredmeny);
	}
}
