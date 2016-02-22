package hu.interconnect.excel;

import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static java.util.stream.StreamSupport.stream;

import org.apache.poi.ss.usermodel.Cell;

import hu.interconnect.exception.ProgramozasiHiba;

public final class ExcelOsszeshasonlito {

	private static final Area ELSO_SZAZ_SOR_ES_OSZLOP = new Area(new Coordinate(0, 0), new Coordinate(100, 100));

	public static void compareExcelFiles(Excel expected, Excel actual) {
		compareExcelFiles(expected, actual, ELSO_SZAZ_SOR_ES_OSZLOP);
	}
	
	private static void compareExcelFiles(Excel expected, Excel actual, Area area) {
		stream(area.spliterator(), false)
		.filter(cellCoordinate -> !compareCells(expected.getCell(cellCoordinate), actual.getCell(cellCoordinate)))
		.forEach(cellCoordinate -> { throw new ProgramozasiHiba("Cell mismatch: " + cellCoordinate + ", expected: " + expected.getCell(cellCoordinate) + ", actual: " + actual.getCell(cellCoordinate)); });
	}
	
	private static boolean compareCells(Cell expected, Cell actual) {
		if (expected == null && actual == null) {
			return true;
		}
		StringCella expectedAsStringCella = new StringCella(expected);
		StringCella actualAsStringCella = new StringCella(actual);
		return equalsAndNotNull(expectedAsStringCella, actualAsStringCella);
	}
	
	private ExcelOsszeshasonlito() {
	}
}
