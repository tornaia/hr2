package hu.interconnect.util;

import static hu.interconnect.util.DateUtils.getEvElsoNapja;
import static hu.interconnect.util.DateUtils.getEvUtolsoNapja;

import java.util.Date;
import java.util.Iterator;

public class MonthIterator implements Iterable<Month>, Iterator<Month> {

	private Month end;
	private Month cursor;
	
	public MonthIterator(Date start, Date end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("Start or end cannot be null!");
		}
		
		if (start.after(end)) {
			throw new IllegalArgumentException("Start must not be after end!");
		}
		
		this.end = new Month(end);
		this.cursor = new Month(start);
	}
	
	public static MonthIterator fromYear(Date ev) {
		return new MonthIterator(getEvElsoNapja(ev), getEvUtolsoNapja(ev));
	}
	
	public static MonthIterator fromYear(int ev) {
		return new MonthIterator(getEvElsoNapja(ev), getEvUtolsoNapja(ev));
	}

	@Override
	public boolean hasNext() {
		return cursor.beforeOrSameMonth(end); 
	}

	@Override
	public Month next() {
		Month ret = new Month(cursor.getTime());
		this.cursor = cursor.getNextMonth();
		return ret;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("MonthIterator not supports remove!");
	}

	@Override
	public Iterator<Month> iterator() {
		return this;
	}
}
