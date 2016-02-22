package test.unit.hu.interconnect.hr.module.reports;

import static org.junit.Assert.assertNotNull;
import hu.interconnect.excel.Excel;
import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.module.reports.Riport;
import test.unit.AbstractBackendUnitTest;

import org.junit.Test;

public class RiportTest extends AbstractBackendUnitTest {

	@Test
	public void nullParamOk() {
		Excel excelSablon = Riport.ORVOSI_VIZSGALAT_LEJARAT_SABLON.getExcelSablon();
		assertNotNull(excelSablon);
	}
	
	@Test(expected=ProgramozasiHiba.class)
	public void unnecessaryParamCausesException() {
		Riport.ORVOSI_VIZSGALAT_LEJARAT_SABLON.getExcelSablon("");
	}
}
