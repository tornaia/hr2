package test.integration.module.reports.atvetteszkozok;

import static hu.interconnect.util.FileUtils.getClasspathInputStream;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import hu.interconnect.excel.Excel;
import hu.interconnect.excel.ExcelOsszeshasonlito;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import test.builder.AtvettEszkozCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class AtvettEszkozokExcelExportIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();

		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
											.tsz(2)
											.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
													.vezeteknev("vezeteknev")
													.keresztnev("keresztnev")
													.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
													.allomanymod(Allomanymod.AKTIV)
													.letrehoz())
											.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);

		receiveditemsRestController.create(new AtvettEszkozCreateDTOBuilder()
											.tsz(szemelyitorzs.getTsz())
											.megnevezes("Megnevezés1")
											.megjegyzes("Megjegyzés1")
											.eredetiNev("eredeti.pdf")
											.adat(new byte[2])
											.letrehoz());
		
		receiveditemsRestController.create(new AtvettEszkozCreateDTOBuilder()
											.tsz(szemelyitorzs.getTsz())
											.megnevezes("Megnevezés2")
											.megjegyzes("Megjegyzés2")
											.eredetiNev("eredeti2.pdf")
											.adat(new byte[2])
											.letrehoz());
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getAtvettEszkozok(2);

		MapMatcher<String, String> elsoSor = new MapMatcher<String, String>()
										.adottKulcsErtekParral("megnevezes", "Megnevezés1")
										.adottKulcsErtekParral("megjegyzes", "Megjegyzés1");
		
		MapMatcher<String, String> masodikSor = new MapMatcher<String, String>()
										.adottKulcsErtekParral("megnevezes", "Megnevezés2")
										.adottKulcsErtekParral("megjegyzes", "Megjegyzés2");
		
		assertThat(excel.getRows(), contains(asList(elsoSor, masodikSor)));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getAtvettEszkozok(2);
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_atvett_eszkozok.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
