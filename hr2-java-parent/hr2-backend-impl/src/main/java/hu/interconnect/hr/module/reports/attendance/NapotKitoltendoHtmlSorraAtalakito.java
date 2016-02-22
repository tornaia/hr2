package hu.interconnect.hr.module.reports.attendance;

import static hu.interconnect.util.DateUtils.getCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

import hu.interconnect.hr.module.exceptiondays.KivetelnapokHelper;

public class NapotKitoltendoHtmlSorraAtalakito implements Function<Date, String> {

	private KivetelnapokHelper kivetelnapok;

	NapotKitoltendoHtmlSorraAtalakito(KivetelnapokHelper kivetelnapok) {
		this.kivetelnapok = kivetelnapok;
	}
	
	private static final String TR_OPEN = "<tr>";
	private static final String TR_CLOSE = "</tr>";
	private static final String TD_OPEN = "<td>";
	private static final String TD_OPEN_GREY = "<td bgcolor=\"#CCCCCC\">";
	private static final String TD_CLOSE = "</td>";
	
	/*
	 *	<tr>
	 *		<td>2.</td>
	 *		<td></td>
	 *		<td></td>
	 *		<td></td>
	 *		<td></td>
	 *		<td></td>
	 *		<td></td>
	 *		<td></td>
	 *	</tr>
	 */
	@Override
	public String apply(Date date) {
		StringBuilder sb = new StringBuilder();
		
		String tdOpen = kivetelnapok.isPihenonap(date) ? TD_OPEN_GREY : TD_OPEN;
		
		sb.append(TR_OPEN);
		
		sb.append(tdOpen);
		sb.append(getCalendar(date).get(Calendar.DAY_OF_MONTH));
		sb.append(".");
		sb.append(TD_CLOSE);
		
		sb.append(tdOpen);
		sb.append(TD_CLOSE);
		
		sb.append(tdOpen);
		sb.append(TD_CLOSE);
		
		sb.append(tdOpen);
		sb.append(TD_CLOSE);
		
		sb.append(tdOpen);
		sb.append(TD_CLOSE);
		
		sb.append(tdOpen);
		sb.append(TD_CLOSE);
		
		sb.append(tdOpen);
		sb.append(TD_CLOSE);
		
		sb.append(tdOpen);
		sb.append(TD_CLOSE);
		
		sb.append(TR_CLOSE);
		
		return sb.toString();
	}

}
