package test.integration.module.reports.probaidovege;

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
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class ProbaidoVegeExcelExportIntTest extends AbstractRestAPIIntTest {

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
																			.probaidoVege("2012.10.15")
																			.letrehoz())
												.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getProbaidoVege("2012.09.15", "2012.11.30");
		
		MapMatcher<String, String> elvartSor = new MapMatcher<String, String>()
										.adottKulcsErtekParral("nev", "Papp Krisztina")
										.adottKulcsErtekParral("probaido_vege", "2012.10.15");
		
		assertThat(excel.getRows(), contains(elvartSor));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getProbaidoVege("2012.09.15", "2012.11.30");
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_probaido_vege.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
