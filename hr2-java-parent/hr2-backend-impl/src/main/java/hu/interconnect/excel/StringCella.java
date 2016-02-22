package hu.interconnect.excel;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;
import hu.interconnect.exception.ProgramozasiHiba;

import java.text.NumberFormat;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.poi.ss.usermodel.Cell;

public class StringCella {
	
	private static NumberFormat nf = NumberFormat.getInstance();
	
	static {
		nf.setGroupingUsed(false);	
	}
	
	private Cell cell;
	
	public StringCella(Cell cell) {
		this.cell = cell;
	}
	
	public String getString() {
		if (cell == null) {
			return null;
		}
		
		switch (cell.getCellType()) {
		case CELL_TYPE_NUMERIC : return nf.format(cell.getNumericCellValue());
		case CELL_TYPE_STRING : return cell.getStringCellValue();
		case CELL_TYPE_BLANK : return "";
		default: throw new ProgramozasiHiba("Nem lekezelt cella típus: " + cell.getCellType());
		}
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cell).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj instanceof StringCella) {
			return new EqualsBuilder().append(getString(), ((StringCella) obj).getString()).isEquals();
		}

		return false;
	}
}