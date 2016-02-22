package hu.interconnect.util;

import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static org.apache.commons.lang3.time.DateUtils.addDays;
import static org.apache.commons.lang3.time.DateUtils.truncate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;

import hu.interconnect.exception.ProgramozasiHiba;

public final class DateUtils {

	private static Date tesztIdo;
	
	public static final String EV_FORMATUM = "yyyy";
	public static final String HONAP_FORMATUM = "yyyy.MM";
	public static final String NAP_FORMATUM = "yyyy.MM.dd";
	public static final String NAP_ORAPERCMASODPERC_FORMATUM = "yyyy.MM.dd HH:mm:ss";
	public static final String ORAPERC_FORMATUM = "HH:mm";

	private static final DateFormat EV_FORMATTER = new SimpleDateFormat(EV_FORMATUM);
	private static final DateFormat HONAP_FORMATTER = new SimpleDateFormat(HONAP_FORMATUM);
	private static final DateFormat NAP_FORMATTER = new SimpleDateFormat(NAP_FORMATUM);
	private static final DateFormat NAP_ORAPERCMASODPERC_FORMATTER = new SimpleDateFormat(NAP_ORAPERCMASODPERC_FORMATUM);
	private static final DateFormat ORAPERC_FORMATTER = new SimpleDateFormat(ORAPERC_FORMATUM);

	private static final Date NULLA_IDOPILLANAT = parseNapOraPercMasodperc("1970.01.01 00:00:00");

	private static final int NUMBER_OF_DAYS_IN_DECEMBER = 31;
	
	public static Date getNullaIdopillanat() {
		return parseNapOraPercMasodperc("1970.01.01 00:00:00");
	}
	
	public static boolean isBetween(Date what, Date start, Date end) {
		if (what == null) {
			return false;
		}
		return equalsAndNotNull(start, what) || start.before(what) && end.after(what);
	}

	public static Date aktualisIdo() {
		return tesztIdo == null ? new Date() : new Date(tesztIdo.getTime());
	}

	public static void tesztDatumotBeallit(String napStr) {
		tesztIdo = parseNap(napStr);
	}

	public static void tesztIdopontotBeallit(String idopontStr) {
		tesztIdo = parseNapOraPercMasodperc(idopontStr);
	}

	public static void alaphelyzet() {
		tesztIdo = null;
	}

	public static Date parseNapOraPercMasodperc(String idoStr) {
		synchronized (NAP_ORAPERCMASODPERC_FORMATTER) {
			try {
				return NAP_ORAPERCMASODPERC_FORMATTER.parse(idoStr);
			} catch (ParseException e) {
				throw new ProgramozasiHiba("Nem sikerult parseolni a naporapercmasodpercet!", e);
			}
		}
	}

	public static Date parseNap(String napStr) {
		synchronized (NAP_FORMATTER) {
			try {
				return NAP_FORMATTER.parse(napStr);
			} catch (ParseException e) {
				throw new ProgramozasiHiba("Nem sikerult parseolni a napot!", e);
			}
		}
	}

	public static Date parseHonap(String honapStr) {
		synchronized (HONAP_FORMATTER) {
			try {
				return HONAP_FORMATTER.parse(honapStr);
			} catch (ParseException e) {
				throw new ProgramozasiHiba("Nem sikerult parseolni a honapot!", e);
			}
		}
	}
	
	public static Date parseEv(String ev) {
		synchronized (HONAP_FORMATTER) {
			try {
				return HONAP_FORMATTER.parse(ev + ".01");
			} catch (ParseException e) {
				throw new ProgramozasiHiba("Nem sikerult parseolni az evet!", e);
			}
		}
	}

	public static Date parseOraPerc(String orapercStr) {
		synchronized (ORAPERC_FORMATTER) {
			try {
				return ORAPERC_FORMATTER.parse(orapercStr);
			} catch (ParseException e) {
				throw new ProgramozasiHiba("Nem sikerult parseolni az orapercet!", e);
			}
		}
	}

	public static String evFormaz(Date datum) {
		synchronized (EV_FORMATTER) {
			return EV_FORMATTER.format(datum);
		}
	}

	public static String honapFormaz(Date datum) {
		synchronized (HONAP_FORMATTER) {
			return HONAP_FORMATTER.format(datum);
		}
	}

	public static String napFormaz(Date datum) {
		synchronized (NAP_FORMATTER) {
			return NAP_FORMATTER.format(datum);
		}
	}

	public static String oraPercFormaz(Date ido) {
		long nullaPont = NULLA_IDOPILLANAT.getTime();
		long idoPont = ido.getTime();
		long kulonbseg = idoPont-nullaPont;
		long orakSzama = kulonbseg / (DateTimeConstants.MILLIS_PER_HOUR);
		String orak = String.format("%02d", orakSzama);
		long percekSzama = (kulonbseg - orakSzama*DateTimeConstants.MILLIS_PER_HOUR)/(DateTimeConstants.MILLIS_PER_MINUTE);
		String percek = String.format("%02d", percekSzama);
		return orak + ":" + percek;
	}

	public static boolean azonosNap(Date date1, Date date2) {
		 if (date1 == null || date2 == null) {
			return false;
        }

        return Days.daysBetween(new DateTime(truncate(getCalendar(date1), Calendar.DAY_OF_MONTH)), new DateTime(truncate(getCalendar(date2), Calendar.DAY_OF_MONTH))).getDays() == 0;
    }

	public static boolean azonosHonap(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
        }

        Calendar cal1 = truncate(getCalendar(date1), Calendar.MONTH);
        Calendar cal2 = truncate(getCalendar(date2), Calendar.MONTH);
        
        return cal1.equals(cal2);
	}

	public static Date hozzaadNapot(Date datum, int mennyit) {
		return addDays(datum, mennyit);
	}

	public static Date getEvElsoNapja(Date nap) {
		return getEvElsoNapja(getCalendar(nap).get(Calendar.YEAR));
	}

	public static Date getEvElsoNapja(int ev) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ev);
		return truncate(cal, Calendar.YEAR).getTime();
	}

	public static Date getEvUtolsoNapja(Date nap) {
		Calendar cal = getCalendar(nap);
		return getEvUtolsoNapja(cal.get(Calendar.YEAR));
	}

	public static Date getEvUtolsoNapja(int ev) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, ev);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, NUMBER_OF_DAYS_IN_DECEMBER);
		return truncate(cal, Calendar.DAY_OF_MONTH).getTime();
	}

	public static Date getHoElsoNapja(Date nap) {
		Calendar cal = getCalendar(nap);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return truncate(cal, Calendar.DAY_OF_MONTH).getTime();
	}

	public static Date getHoUtolsoNapja(Date nap) {
		Calendar cal = getCalendar(nap);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return truncate(cal, Calendar.DAY_OF_MONTH).getTime();
	}
	
	public static int getNapokSzamaEvbenEsHonapban(Date honap) {
		return getCalendar(getHoUtolsoNapja(honap)).get(Calendar.DAY_OF_MONTH);
	}

	public static int getNapokSzama(Date kezdet, Date veg) {
		if (kezdet == null || veg == null) {
			throw new IllegalArgumentException("A kezdet vagy a veg NULL: " + kezdet + ", " + veg);
		} else if (kezdet.after(veg)) {
			throw new IllegalArgumentException("A kezdet később van mint a vég: " + kezdet.toString() + ", " + veg.toString());
		}
		return Days.daysBetween(new DateTime(truncate(getCalendar(kezdet), Calendar.DAY_OF_MONTH)), new DateTime(truncate(getCalendar(veg), Calendar.DAY_OF_MONTH))).getDays()+1;
	}

	public static boolean isHetvege(Date nap) {
		Calendar cal = getCalendar(nap);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
	}

	public static boolean isMunkanap(Date nap) {
		return !isHetvege(nap);
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	public static boolean isInInclusive(Date target, Date start, Date end) {
	    return !target.before(start) && !target.after(end);
	}

	private DateUtils() {
	}
}
