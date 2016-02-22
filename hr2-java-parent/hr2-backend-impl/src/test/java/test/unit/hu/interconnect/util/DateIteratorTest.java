package test.unit.hu.interconnect.util;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.util.DateUtils.parseNap;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import hu.interconnect.util.DateIterator;
import test.matcher.DateMatcher;
import test.unit.AbstractBackendUnitTest;

public class DateIteratorTest extends AbstractBackendUnitTest {

	@Test
	public void monthIterator() {
		DateIterator februarIterator = DateIterator.daysOfMonthIterator(parseNap("2013.02.16"));
		
		List<Date> days = newArrayList(februarIterator.iterator());
		assertThat(days, hasSize(28));
		
		Date febr1 = days.get(0);
		assertThat(febr1, new DateMatcher("2013.02.01"));
		
		Date febr28 = days.get(27);
		assertThat(febr28, new DateMatcher("2013.02.28"));
	}
	
	@Test
	public void monthIterator2() {
		DateIterator februarIterator = DateIterator.daysOfMonthIterator(parseNap("2013.02.01"));
		
		List<Date> days = newArrayList(februarIterator.iterator());
		assertThat(days, hasSize(28));
		
		Date febr1 = days.get(0);
		assertThat(febr1, new DateMatcher("2013.02.01"));
		
		Date febr28 = days.get(27);
		assertThat(febr28, new DateMatcher("2013.02.28"));
	}
	
	@Test
	public void monthIterator3() {
		DateIterator februarIterator = DateIterator.daysOfMonthIterator(parseNap("2013.02.28"));
		
		List<Date> days = newArrayList(februarIterator.iterator());
		assertThat(days, hasSize(28));
		
		Date febr1 = days.get(0);
		assertThat(febr1, new DateMatcher("2013.02.01"));
		
		Date febr28 = days.get(27);
		assertThat(febr28, new DateMatcher("2013.02.28"));
	}
}
