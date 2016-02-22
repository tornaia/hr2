package test.matcher;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import test.matcher.AbstractTypeSafeDiagnosingMatcher;

public class DateMatcher extends AbstractTypeSafeDiagnosingMatcher<Date> {

	private Matcher<Date> date;

	public DateMatcher(String date) {
		this.date = CoreMatchers.is(parseNap(date));
	}

	@Override
	protected boolean matchesSafely(Date item, Description mismatchDescription) {
		return matches(date, item, "date value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(Date.class.getSimpleName())
    	.appendText(", date: ").appendDescriptionOf(date);
	}
}
