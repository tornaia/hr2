package hu.interconnect.hr.module.scheduledtasks.igazolvanykepzettseglejarat;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static hu.interconnect.util.DateUtils.isBetween;
import static hu.interconnect.util.DateUtils.napFormaz;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import hu.interconnect.common.KuldendoLevel;
import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.util.VelocityUtils;

public class SzemelyitorzsbolIgazolvanyKepzettsegLejarLeveletEloallito implements Function<Szemelyitorzs, KuldendoLevel> {

	private String template;
	
	private Date kezdet;
	
	private Date veg;

	public SzemelyitorzsbolIgazolvanyKepzettsegLejarLeveletEloallito(String template, Date kezdet, Date veg) {
		this.template = template;
		this.kezdet = kezdet;
		this.veg = veg;
	}

	@Override
	public KuldendoLevel apply(Szemelyitorzs szemelyitorzs) {
		SzemelyiAdatok szemelyiAdatok = szemelyitorzs.getSzemelyiAdatok();

		Map<String, Object> adatok = newHashMap();
		
		Date szemelyiIgazolvanySzamLejarat = szemelyiAdatok.getSzemelyiIgazolvanySzamLejarat();
		adatok.put("isSzemelyiIgazolvanyLejart", isBetween(szemelyiIgazolvanySzamLejarat, kezdet, veg));
		adatok.put("szemelyiIgazolvanyLejarat", szemelyiIgazolvanySzamLejarat != null ? napFormaz(szemelyiIgazolvanySzamLejarat) : null);
		
		Date utlevelSzamLejarat = szemelyiAdatok.getUtlevelSzamLejarat();
		adatok.put("isUtlevelSzamLejart", isBetween(utlevelSzamLejarat, kezdet, veg));
		adatok.put("utlevelSzamLejarat", utlevelSzamLejarat != null ? napFormaz(utlevelSzamLejarat) : null);
		
		Date jogositvanySzamLejarat = szemelyiAdatok.getJogositvanySzamLejarat();
		adatok.put("isJogositvanySzamLejart", isBetween(jogositvanySzamLejarat, kezdet, veg));
		adatok.put("jogositvanySzamLejarat", jogositvanySzamLejarat != null ? napFormaz(jogositvanySzamLejarat) : null);
		
		List<Map<String, String>> kepzettsegek = szemelyitorzs.getKepzettsegek()
													.stream()
													.filter(kepzettseg -> isBetween(kepzettseg.getErvenyessegVege(), kezdet, veg))
													.map(kepzettseg -> ImmutableMap.of("megnevezes", kepzettseg.getMegnevezes(), "ervenyessegVege", napFormaz(kepzettseg.getErvenyessegVege())))
													.collect(Collectors.toList());
		adatok.put("kepzettsegek", kepzettsegek);
		
		String szemelyitorzsEmail = szemelyitorzs.getSzemelyiAdatok().getEmail();
		boolean isSzemelyitorzsEmailFilledOut = isNotBlank(szemelyitorzsEmail);
		adatok.put("isSzemelyitorzsEmailFilledOut", isSzemelyitorzsEmailFilledOut);
		
		String tartalom = VelocityUtils.generate(template, adatok);

		List<String> cimzettek = isSzemelyitorzsEmailFilledOut ? newArrayList(szemelyitorzsEmail) : newArrayList();
		return new KuldendoLevel(cimzettek, "Igazolvány, képzettség lejár 3 hónap múlva - " + szemelyitorzs.getTeljesNev(), tartalom);
	}
}
