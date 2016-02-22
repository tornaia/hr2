package test.integration.module.reports.uzemorvosivizsgalatlejarat;

import static hu.interconnect.util.FileUtils.getClasspathInputStream;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import hu.interconnect.excel.Excel;
import hu.interconnect.excel.ExcelOsszeshasonlito;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.OrvosiVizsgalatDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class UzemorvosiVizsgalatLejaratExcelExportIntTest extends AbstractRestAPIIntTest {

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
												.orvosiVizsgalat(new OrvosiVizsgalatDTOBuilder()
																			.gyakorisag(12)
																			.utolsoOrvosiVizsgalatIdopontja("2012.01.04")
																			.letrehoz())
												.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getUzemorvosiVizsgalatLejarat("2013.01.02", "2013.01.30");
		
		MapMatcher<String, String> elvartSor = new MapMatcher<String, String>()
										.adottKulcsErtekParral("nev", "Papp Krisztina")
										.adottKulcsErtekParral("kovetkezo_orvosi_vizsgalat_idopontja", "2013.01.04");
		
		assertThat(excel.getRows(), contains(elvartSor));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getUzemorvosiVizsgalatLejarat("2013.01.02", "2013.01.30");
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_orvosi_vizsgalat_lejarat.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
