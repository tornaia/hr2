package hu.interconnect.hr.module.reports.probationaryperiodend;

import java.util.Map;
import java.util.function.Function;

import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;

public class ProbationaryPeriodEndRowFromPersonaldataCreator implements Function<Szemelyitorzs, Map<String, Object>> {

	@Override
	public Map<String, Object> apply(Szemelyitorzs szemelyitorzs) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		
		sor.put("nev", szemelyitorzs.getTeljesNev());
		sor.putDate("probaido_vege", szemelyitorzs.getJogviszonyAdatok().getProbaidoVege());

		return sor.getMap();
	}
}
