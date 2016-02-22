package test.matcher;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;
import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.hr.domain.Szemelyitorzs;
import test.matcher.AbstractTypeSafeDiagnosingMatcher;

public class SzabadsagMatcher extends AbstractTypeSafeDiagnosingMatcher<Szabadsag> {

	private Matcher<Szemelyitorzs> szemelyitorzs = new IsAnything<>();

	private Matcher<Date> datum = new IsAnything<>();

	private Matcher<FelhasznaltSzabadnapJelleg> jelleg = new IsAnything<>();

	public SzabadsagMatcher szemelyitorzs(Matcher<Szemelyitorzs> szemelyitorzs) {
		this.szemelyitorzs = szemelyitorzs;
		return this;
	}

	public SzabadsagMatcher datum(String napStr) {
		this.datum = CoreMatchers.is(parseNap(napStr));
		return this;
	}

	public SzabadsagMatcher jelleg(FelhasznaltSzabadnapJelleg jelleg) {
		this.jelleg = CoreMatchers.is(jelleg);
		return this;
	}

	@Override
	protected boolean matchesSafely(Szabadsag item, Description mismatchDescription) {
		return matches(szemelyitorzs, item.getSzemelyitorzs(), "szemelyitorzs erteke: ", mismatchDescription) &&
		matches(datum, item.getDatum(), "datum erteke: ", mismatchDescription) &&
		matches(jelleg, item.getJelleg(), "jelleg erteke: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(Szabadsag.class.getSimpleName())
		.appendText(", szemelyitorzs: ").appendDescriptionOf(szemelyitorzs)
		.appendText(", datum: ").appendDescriptionOf(datum)
		.appendText(", jelleg: ").appendDescriptionOf(jelleg);
	}
}
