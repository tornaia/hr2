package hu.interconnect.hr.module.reports.receiveditems;

import java.util.Map;
import java.util.function.Function;

import hu.interconnect.hr.domain.AtvettEszkoz;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;

public class ReceivedItemsRowFromReceivedItemCreator implements Function<AtvettEszkoz, Map<String, Object>> {

	@Override
	public Map<String, Object> apply(AtvettEszkoz atvettEszkozDTO) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		sor.put("megnevezes", atvettEszkozDTO.getMegnevezes());
		sor.put("megjegyzes", atvettEszkozDTO.getMegjegyzes());
		return sor.getMap();
	}
}
