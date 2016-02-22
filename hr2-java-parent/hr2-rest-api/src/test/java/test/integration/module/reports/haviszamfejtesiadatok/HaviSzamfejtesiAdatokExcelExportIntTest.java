package test.integration.module.reports.haviszamfejtesiadatok;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus.APA_SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus.BETEGSZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus.MUNKA;
import static hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus.SZABADNAP;
import static hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus.SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus.TANULMANYI_SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus.TEMETESI_SZABADSAG;
import static hu.interconnect.util.FileUtils.getClasspathInputStream;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import hu.interconnect.excel.Excel;
import hu.interconnect.excel.ExcelOsszeshasonlito;
import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvEditDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import test.builder.HaviJelenletiIvEditDTOBuilder;
import test.builder.JelenletiAdatDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class HaviSzamfejtesiAdatokExcelExportIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();

		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
												.tsz(2)
												.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																			.vezeteknev("Papp")
																			.keresztnev("Krisztina")
																			.letrehoz())
												.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																			.allomanymod(Allomanymod.AKTIV)
																			.letrehoz())
												.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
		
		HaviJelenletiIvEditDTO haviJelenletiIv = new HaviJelenletiIvEditDTOBuilder()
												.honap("2013.02")
												.tsz(szemelyitorzs.getTsz())
												.jelenletiAdatok(newArrayList(
														new JelenletiAdatDTOBuilder().datum("2013.02.01").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("7:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.02").tipus(SZABADNAP).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.03").tipus(SZABADNAP).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.04").tipus(APA_SZABADSAG).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.05").tipus(BETEGSZABADSAG).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.06").tipus(SZABADSAG).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.07").tipus(TANULMANYI_SZABADSAG).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.08").tipus(TEMETESI_SZABADSAG).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.09").tipus(SZABADNAP).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.10").tipus(SZABADNAP).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.11").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("08:00").to50("01:00").to100("02:00").m30("03:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.12").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("08:15").to50("01:30").to100("02:45").m30("03:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.13").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.14").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:15").to50("1:30").to100("2:45").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.15").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.16").tipus(SZABADNAP).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.17").tipus(SZABADNAP).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.18").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.19").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.20").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.21").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.22").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.23").tipus(SZABADNAP).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.24").tipus(SZABADNAP).letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.25").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.26").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.27").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz(),
														new JelenletiAdatDTOBuilder().datum("2013.02.28").tipus(MUNKA).kezdet("08:15").veg("18:45").ledolgozott("8:00").to50("1:00").to100("2:00").m30("3:00").letrehoz()))
												.letrehoz();
		
		timeandattendanceRestController.edit(haviJelenletiIv);
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getHaviSzamfejtesiAdatok("2013.02");
		
		MapMatcher<String, String> elsoSor = new MapMatcher<String, String>()
									.adottKulcsErtekParral("torzsszam", "2")
									.adottKulcsErtekParral("nev", "Papp Krisztina")
									.adottKulcsErtekParral("jelenleti_iv_leadva", "")
									.adottKulcsErtekParral("km_hozzajarulas", "")
									.adottKulcsErtekParral("to50", "16:00")
									.adottKulcsErtekParral("to100", "31:30")
									.adottKulcsErtekParral("m30", "45:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.01", "07:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.02", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.03", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.04", "Apa szabadság")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.05", "Betegszabadság")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.06", "Szabadság")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.07", "Tanulmányi szabadság")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.08", "Temetési szabadság")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.09", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.10", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.11", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.12", "08:15")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.13", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.14", "08:15")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.15", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.16", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.17", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.18", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.19", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.20", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.21", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.22", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.23", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.24", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.25", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.26", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.27", "08:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.28", "08:00");
		
		MapMatcher<String, String> masodikSor = new MapMatcher<String, String>()
									.adottKulcsErtekParral("torzsszam", "9999")
									.adottKulcsErtekParral("nev", "Idősebb Nagyguci Imre")
									.adottKulcsErtekParral("jelenleti_iv_leadva", "")
									.adottKulcsErtekParral("km_hozzajarulas", "")
									.adottKulcsErtekParral("to50", "00:00")
									.adottKulcsErtekParral("to100", "00:00")
									.adottKulcsErtekParral("m30", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.01", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.02", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.03", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.04", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.05", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.06", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.07", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.08", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.09", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.10", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.11", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.12", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.13", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.14", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.15", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.16", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.17", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.18", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.19", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.20", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.21", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.22", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.23", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.24", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.25", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.26", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.27", "00:00")
									.adottKulcsErtekParral("ledolgozott_ora_2013.02.28", "00:00");
		
		assertThat(excel.getRows(), contains(asList(elsoSor, masodikSor)));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getHaviSzamfejtesiAdatok("2013.02");
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_havi_szamfejtesi_adatok.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
