package test.matcher;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsAnything;

import hu.interconnect.common.KuldendoLevel;
import test.matcher.AbstractTypeSafeDiagnosingMatcher;

public class KuldendoLevelMatcher extends AbstractTypeSafeDiagnosingMatcher<KuldendoLevel> {

	private Matcher<String[]> cimzettek = new IsAnything<>();
	
	private Matcher<String> targy = new IsAnything<>();
	
	private Matcher<String> tartalom = new IsAnything<>();
	
	public KuldendoLevelMatcher cimzettek(String... cimzettek) {
		if (cimzettek.length == 0) {
			this.cimzettek = Matchers.arrayWithSize(0);
		} else {
			this.cimzettek = Matchers.arrayContaining(cimzettek);
		}
		return this;
	}
	
	public KuldendoLevelMatcher targy(String targy) {
		this.targy = CoreMatchers.is(targy);
		return this;
	}
	
	public KuldendoLevelMatcher tartalom(String tartalom) {
		this.tartalom = CoreMatchers.is(tartalom);
		return this;
	}
	
	@Override
	protected boolean matchesSafely(KuldendoLevel item, Description mismatchDescription) {
		return matches(cimzettek, item.getCimzettek(), "cimzettek value: ", mismatchDescription) &&
				matches(targy, item.getTargy(), "targy value: ", mismatchDescription) &&
				matches(tartalom, item.getTartalom(), "tartalom value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(KuldendoLevel.class.getSimpleName())
    	.appendText(", cimzettek: ").appendDescriptionOf(cimzettek)
    	.appendText(", targy: ").appendDescriptionOf(targy)
    	.appendText(", tartalom: ").appendDescriptionOf(tartalom);
	}
}
