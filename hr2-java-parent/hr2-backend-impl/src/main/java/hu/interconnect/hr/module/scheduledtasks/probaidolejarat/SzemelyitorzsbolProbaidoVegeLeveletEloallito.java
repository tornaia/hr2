package hu.interconnect.hr.module.scheduledtasks.probaidolejarat;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static hu.interconnect.util.DateUtils.napFormaz;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import hu.interconnect.common.KuldendoLevel;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.util.VelocityUtils;

public class SzemelyitorzsbolProbaidoVegeLeveletEloallito implements Function<List<Szemelyitorzs>, KuldendoLevel> {

	private String template;

	public SzemelyitorzsbolProbaidoVegeLeveletEloallito(String template) {
		this.template = template;
	}

	@Override
	public KuldendoLevel apply(List<Szemelyitorzs> szemelyitorzsek) {
		Map<String, Object> adatok = newHashMap();
		adatok.put("szemelyitorzsek", szemelyitorzsek
										.stream()
										.map(szemelyitorzs -> ImmutableMap.of("teljesNev", szemelyitorzs.getTeljesNev(), "probaidoVege", napFormaz(szemelyitorzs.getJogviszonyAdatok().getProbaidoVege())))
										.collect(Collectors.toList()));
		
		String tartalom = VelocityUtils.generate(template, adatok);
		
		List<String> cimzettek = newArrayList();
		return new KuldendoLevel(cimzettek, "Próbaidő vége", tartalom);
	}
}
