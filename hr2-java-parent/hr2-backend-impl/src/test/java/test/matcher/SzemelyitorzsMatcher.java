package test.matcher;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import hu.interconnect.hr.domain.Szemelyitorzs;
import test.matcher.AbstractTypeSafeDiagnosingMatcher;

public class SzemelyitorzsMatcher extends AbstractTypeSafeDiagnosingMatcher<Szemelyitorzs> {

	private Matcher<Integer> tsz = new IsAnything<>();
	
	public SzemelyitorzsMatcher adottTsszel(Integer tsz) {
		this.tsz = CoreMatchers.is(tsz);
		return this;
	}
	
	@Override
	protected boolean matchesSafely(Szemelyitorzs item, Description mismatchDescription) {
		return matches(tsz, item.getTsz(), "tsz value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(Szemelyitorzs.class.getSimpleName())
    	.appendText(", tsz: ").appendDescriptionOf(tsz);
	}
}
