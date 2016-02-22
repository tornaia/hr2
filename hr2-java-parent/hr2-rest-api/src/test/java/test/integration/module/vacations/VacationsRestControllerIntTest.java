package test.integration.module.vacations;

import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.BETEGSZABADSAG;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyIterable;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;
import test.builder.EvesSzabadsagAdatGetDTOBuilder;
import test.builder.SzabadsagFelhasznalasCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.EvesSzabadsagAdatDTOMatcher;
import test.matcher.EvesSzabadsagAdatDTOMatcher.ConsumptionTableDTOMatcher;
import test.matcher.EvesSzabadsagAdatDTOMatcher.ConsumptionTableDTOMatcher.ConsumptionPerTypePerMonthDTOMatcher;
import test.matcher.EvesSzabadsagAdatDTOMatcher.ConsumptionTableDTOMatcher.ConsumptionPerTypePerMonthDTOMatcher.AdottHaviEsAdottJelleguSzabadsagokDTOMatcher;
import test.matcher.SzabadsagFelhasznalasResponseDTOMatcher;

public class VacationsRestControllerIntTest extends AbstractRestAPIIntTest {

	@Test
	public void evesSzabadsagAdatokMegjelennek() {
		adminBejelentkezik();
		
		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
												.tsz(1)
												.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
														.vezeteknev("vezeteknev")
														.keresztnev("keresztnev")
														.letrehoz())
												.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
														.allomanymod(Allomanymod.AKTIV)
														.letrehoz())
												.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
		
		SzabadsagFelhasznalasCreateDTO felhasznalas = new SzabadsagFelhasznalasCreateDTOBuilder()
													.tsz(szemelyitorzs.getTsz())
													.kezdet("2009.01.01")
													.veg("2015.12.31")
													.jelleg(BETEGSZABADSAG)
													.letrehoz();
		
		vacationsRestController.createConsumption(felhasznalas);
		
		EvesSzabadsagAdatResponseDTO evesSzabadsagAdatDTO = vacationsRestController.get(new EvesSzabadsagAdatGetDTOBuilder()
													.tsz(szemelyitorzs.getTsz())
													.ev(2012)
													.letrehoz());
		
		assertThat(evesSzabadsagAdatDTO, new EvesSzabadsagAdatDTOMatcher()
				.tsz(szemelyitorzs.getTsz())
				.ev(2012)
				.alapszabadsag(0)
				.gyermekekUtan(0)
				.fiatalkoru(0)
				.vakSzemely(0)
				.egyebMunkakor(0)
				.ktKaPotszabadsag(0)
				.ktKaVezetoi(0)
				.egyebCsokkento(0)
				.tanulmanyi(0)
				.multEviSzabadsag(0)
				.bszJarandosagAdottEvi(0)
				.consumptionTableDTO(new ConsumptionTableDTOMatcher().rows(contains(asList(
								new ConsumptionPerTypePerMonthDTOMatcher()
												.jelleg(FelhasznaltSzabadnapJelleg.SZABADSAG)
												.january(0)
												.januaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.february(0)
												.februaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.march(0)
												.marchDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.april(0)
												.aprilDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.may(0)
												.mayDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.june(0)
												.juneDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.july(0)
												.julyDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.august(0)
												.augustDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.september(0)
												.septemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.october(0)
												.octoberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.november(0)
												.novemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.december(0)
												.decemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable())),
								new ConsumptionPerTypePerMonthDTOMatcher()
												.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
												.january(22)
												.januaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																															.tsz(szemelyitorzs.getTsz())
																															.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																															.kezdet("2012.01.02")
																															.veg("2012.01.31")
																															.munkanapokSzama(22)))))
												.february(21)
												.februaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.02.01")
																														.veg("2012.02.29")
																														.munkanapokSzama(21)))))
												.march(22)
												.marchDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.03.01")
																														.veg("2012.03.30")
																														.munkanapokSzama(22)))))
												.april(21)
												.aprilDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.04.02")
																														.veg("2012.04.27")
																														.munkanapokSzama(21)))))
												.may(23)
												.mayDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.05.01")
																														.veg("2012.05.31")
																														.munkanapokSzama(23)))))
												.june(21)
												.juneDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.06.01")
																														.veg("2012.06.29")
																														.munkanapokSzama(21)))))
												.july(22)
												.julyDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.07.02")
																														.veg("2012.07.31")
																														.munkanapokSzama(22)))))
												.august(23)
												.augustDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.08.01")
																														.veg("2012.08.31")
																														.munkanapokSzama(23)))))
												.september(20)
												.septemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.09.03")
																														.veg("2012.09.28")
																														.munkanapokSzama(20)))))
												.october(23)
												.octoberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.10.01")
																														.veg("2012.10.31")
																														.munkanapokSzama(23)))))
												.november(22)
												.novemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.11.01")
																														.veg("2012.11.30")
																														.munkanapokSzama(22)))))
												.december(21)
												.decemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(contains(asList(
																														new SzabadsagFelhasznalasResponseDTOMatcher()
																														.tsz(szemelyitorzs.getTsz())
																														.jelleg(FelhasznaltSzabadnapJelleg.BETEGSZABADSAG)
																														.kezdet("2012.12.01")
																														.veg("2012.12.28")
																														.munkanapokSzama(21))))),
								new ConsumptionPerTypePerMonthDTOMatcher()
												.jelleg(FelhasznaltSzabadnapJelleg.TEMETESI_SZABADSAG)
												.january(0)
												.januaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.february(0)
												.februaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.march(0)
												.marchDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.april(0)
												.aprilDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.may(0)
												.mayDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.june(0)
												.juneDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.july(0)
												.julyDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.august(0)
												.augustDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.september(0)
												.septemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.october(0)
												.octoberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.november(0)
												.novemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.december(0)
												.decemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable())),
								new ConsumptionPerTypePerMonthDTOMatcher()
												.jelleg(FelhasznaltSzabadnapJelleg.APA_SZABADSAG)
												.january(0)
												.januaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.february(0)
												.februaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.march(0)
												.marchDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.april(0)
												.aprilDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.may(0)
												.mayDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.june(0)
												.juneDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.july(0)
												.julyDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.august(0)
												.augustDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.september(0)
												.septemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.october(0)
												.octoberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.november(0)
												.novemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.december(0)
												.decemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable())),
								new ConsumptionPerTypePerMonthDTOMatcher()
												.jelleg(FelhasznaltSzabadnapJelleg.TANULMANYI_SZABADSAG)
												.january(0)
												.januaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.february(0)
												.februaryDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.march(0)
												.marchDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.april(0)
												.aprilDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.may(0)
												.mayDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.june(0)
												.juneDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.july(0)
												.julyDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.august(0)
												.augustDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.september(0)
												.septemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.october(0)
												.octoberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.november(0)
												.novemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
												.december(0)
												.decemberDetails(new AdottHaviEsAdottJelleguSzabadsagokDTOMatcher().reszletek(emptyIterable()))
								)))));
	}
}
