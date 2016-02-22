package test.unit.hu.interconnect.excel;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.util.FileUtils.getClasspathByteArray;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.junit.Test;

import hu.interconnect.excel.Excel;
import hu.interconnect.excel.ExcelFejlecMezo;
import hu.interconnect.excel.ExcelOsszeshasonlito;
import test.unit.AbstractBackendUnitTest;

public class ExcelTest extends AbstractBackendUnitTest {

	@Test
	public void excelSablonFejlecMezokbolTest() {
		List<ExcelFejlecMezo> sablonFejlecMezok = newArrayList();
		sablonFejlecMezok.add(new ExcelFejlecMezo("Törzsszám", "torzsszam"));
		sablonFejlecMezok.add(new ExcelFejlecMezo("Név", "nev"));
		
		Excel excel = new Excel(sablonFejlecMezok);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		excel.write(baos);
		byte[] generaltExcelBinaris = baos.toByteArray();
		
		byte[] elvartExcelBinaris = getClasspathByteArray("/excel/excel_test_dinamikus_sablon.xls");
		
		ExcelOsszeshasonlito.compareExcelFiles(new Excel(elvartExcelBinaris), new Excel(generaltExcelBinaris));
	}
	
}
