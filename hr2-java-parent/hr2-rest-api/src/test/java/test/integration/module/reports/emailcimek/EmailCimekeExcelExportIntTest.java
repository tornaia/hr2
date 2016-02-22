package test.integration.module.reports.emailcimek;

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
import hu.interconnect.hr.backend.api.enumeration.RiportAllomanymod;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class EmailCimekeExcelExportIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();

		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
												.tsz(2)
												.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																			.vezeteknev("Papp")
																			.keresztnev("Krisztina")
																			.email("papp.krisztina@gmail.com")
																			.letrehoz())
												.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																			.allomanymod(Allomanymod.GYESGYED)
																			.letrehoz())
												.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getEmailCimek(RiportAllomanymod.GYESGYED);
		
		MapMatcher<String, String> elvartSor = new MapMatcher<String, String>()
									.adottKulcsErtekParral("nev", "Papp Krisztina")
									.adottKulcsErtekParral("email", "papp.krisztina@gmail.com");
		
		assertThat(excel.getRows(), contains(elvartSor));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getEmailCimek(RiportAllomanymod.GYESGYED);
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_email_cimek.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
