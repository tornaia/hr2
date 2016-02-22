package hu.interconnect.hr.module.reports.listofpeople;

import java.util.Map;
import java.util.function.Function;

import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;

public class ListOfPeopleRowFromPersonaldataCreator implements Function<Szemelyitorzs, Map<String, Object>> {

	@Override
	public Map<String, Object> apply(Szemelyitorzs szemelyitorzs) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		
		sor.put("nev", szemelyitorzs.getTeljesNev());
		
		return sor.getMap();
	}
}
