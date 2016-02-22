package hu.interconnect.hr.module.reports;

import static hu.interconnect.util.DateUtils.evFormaz;
import static hu.interconnect.util.DateUtils.honapFormaz;
import static hu.interconnect.util.DateUtils.napFormaz;
import static hu.interconnect.util.DateUtils.oraPercFormaz;

import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;

import hu.interconnect.hr.domain.ErtekkeszletElem;
import hu.interconnect.util.Localizer;

public class NullValueToEmptyStringMap {
	
	private Map<String, Object> map = Maps.newHashMap();
	
	public void put(String key, Object value) {
		map.put(key, value == null ? "" : value);
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void putErtekkeszletElem(String key, ErtekkeszletElem ertekkeszletElem) {
		map.put(key, ertekkeszletElem == null ? "" : ertekkeszletElem.getMegnevezesMagyar());
	}

	public void putDate(String key, Date date) {
		map.put(key, date == null ? "" : napFormaz(date));
	}
	
	public void putTime(String key, Date time) {
		map.put(key, time == null ? "" : oraPercFormaz(time));
	}
	
	public void putDateWithYearMonth(String key, Date date) {
		map.put(key, date == null ? "" : honapFormaz(date));
	}
	
	public void putDateWithYear(String key, Date date) {
		map.put(key, date == null ? "" : evFormaz(date));
	}
	
	public void putEnum(String key, Enum<?> enumValue) {
		if (enumValue == null) {
			map.put(key, "");
		} else {
			String enumLokalizalva = Localizer.getMessage(enumValue);
			map.put(key, enumLokalizalva);
		}
	}
	
	public void putEmpty(String key) {
		map.put(key, "");
	}
}
