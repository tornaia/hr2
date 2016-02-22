package hu.interconnect.util;

import static hu.interconnect.util.DateUtils.azonosHonap;
import static hu.interconnect.util.DateUtils.getCalendar;
import static org.apache.commons.lang.time.DateUtils.addMonths;

import java.util.Calendar;
import java.util.Date;

public class Month {

	private Date date;

	public Month(Date nap) {
		this.date = new Date(nap.getTime());
	}
	
	public boolean isJanuary() {
		return Calendar.JANUARY == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isFebruary() {
		return Calendar.FEBRUARY == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isMarch() {
		return Calendar.MARCH == getCalendar(date).get(Calendar.MONTH);
	} 
	
	public boolean isApril() {
		return Calendar.APRIL == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isMay() {
		return Calendar.MAY == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isJune() {
		return Calendar.JUNE == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isJuly() {
		return Calendar.JULY == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isAugust() {
		return Calendar.AUGUST == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isSeptember() {
		return Calendar.SEPTEMBER == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isOctober() {
		return Calendar.OCTOBER == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isNovember() {
		return Calendar.NOVEMBER == getCalendar(date).get(Calendar.MONTH);
	}
	
	public boolean isDecember() {
		return Calendar.DECEMBER == getCalendar(date).get(Calendar.MONTH);
	}

	public boolean beforeOrSameMonth(Month honap) {
		return date.before(honap.date) || azonosHonap(date, honap.date);
	}

	public Month getNextMonth() {
		return new Month(getCalendar(addMonths(date, 1)).getTime());
	}

	public Date getTime() {
		return new Date(date.getTime());
	}
}
