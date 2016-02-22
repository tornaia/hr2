package hu.interconnect.hr.module.reports.entransandleavers;

import java.util.Map;
import java.util.function.Function;

import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;

public class EntrantsAndLeaversRowFromPersonaldataCreator implements Function<Szemelyitorzs, Map<String, Object>> {

	@Override
	public Map<String, Object> apply(Szemelyitorzs szemelyitorzs) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		
		sor.put("nev", szemelyitorzs.getTeljesNev());
		sor.putDate("jogviszony_kezdete", szemelyitorzs.getJogviszonyAdatok().getJogviszonyKezdete());
		sor.putDate("jogviszony_vege", szemelyitorzs.getJogviszonyAdatok().getJogviszonyVege());

		return sor.getMap();
	}
}
