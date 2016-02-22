package test.matcher;

import java.sql.Timestamp;
import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import hu.interconnect.util.DateUtils;

public class TimestampMatcher extends AbstractTypeSafeDiagnosingMatcher<Date> {

	private Matcher<Timestamp> timestamp;

	public TimestampMatcher(String napOraPercMasodperc) {
		timestamp = CoreMatchers.is(new Timestamp(DateUtils.parseNapOraPercMasodperc(napOraPercMasodperc).getTime()));
	}

	@Override
	protected boolean matchesSafely(Date item, Description mismatchDescription) {
		return matches(CoreMatchers.is(timestamp), item, "timestamp value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(Timestamp.class.getSimpleName())
    	.appendText(", timestamp: ").appendDescriptionOf(timestamp);
	}
}
