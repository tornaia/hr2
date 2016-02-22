package hu.interconnect.excel;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Coordinate {

	public int col;
	public int row;
	
	public Coordinate(int col, int row) {
		this.col = col;
		this.row = row;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(col).append(row).toString();
	}
	
}
