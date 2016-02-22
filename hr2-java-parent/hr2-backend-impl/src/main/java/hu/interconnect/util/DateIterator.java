package hu.interconnect.util;

import static hu.interconnect.util.DateUtils.getHoElsoNapja;
import static hu.interconnect.util.DateUtils.getHoUtolsoNapja;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static org.apache.commons.lang.time.DateUtils.addDays;
import static org.apache.commons.lang.time.DateUtils.truncate;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class DateIterator implements Iterable<Date>, Iterator<Date> {

	private Date cursor;
	private Date end;
	
	public DateIterator(Date start, Date end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("Start or end cannot be null!");
		}
		
		if (start.after(end)) {
			throw new IllegalArgumentException("Start must not be after end!");
		}
		
		this.end = truncate(end, Calendar.DAY_OF_MONTH);
		this.cursor = truncate(start, Calendar.DAY_OF_MONTH);
	}
	
	public static DateIterator daysOfMonthIterator(Date dayOfMonth) {
		return new DateIterator(getHoElsoNapja(dayOfMonth), getHoUtolsoNapja(dayOfMonth));
	}
	
	@Override
	public boolean hasNext() {
		return cursor.before(end) || equalsAndNotNull(cursor, end);
	}

	@Override
	public Date next() {
		Date next = new Date(cursor.getTime());
		this.cursor = addDays(cursor, 1);
		return next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("DayIterator not supports remove!");
	}

	@Override
	public Iterator<Date> iterator() {
		return this;
	}
}
