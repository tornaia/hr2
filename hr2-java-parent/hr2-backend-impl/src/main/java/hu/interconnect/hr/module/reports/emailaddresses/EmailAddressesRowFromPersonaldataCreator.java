package hu.interconnect.hr.module.reports.emailaddresses;

import java.util.Map;
import java.util.function.Function;

import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;

public class EmailAddressesRowFromPersonaldataCreator implements Function<Szemelyitorzs, Map<String, Object>> {

	@Override
	public Map<String, Object> apply(Szemelyitorzs szemelyitorzs) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		
		SzemelyiAdatok szemelyiAdatok = szemelyitorzs.getSzemelyiAdatok();
		sor.put("nev", szemelyitorzs.getTeljesNev());
		sor.put("email", szemelyiAdatok.getEmail());
		
		return sor.getMap();
	}
}
