package hu.interconnect.excel;

public class ExcelFejlecMezo {

	public static final int DEFAULT_OSZLOP_SZELESSEG = 40;
	public static final int DEFAULT_KESKENY_OSZLOP_SZELESSEG = 14;
	private static final int SZELESSEGI_EGYSEG_EXCEL_ES_POI_HANYADOS = 261;
	
	private String text;
	
	private String comment;
	
	private int width;
	
	private Short backgroundColor;
	
	public ExcelFejlecMezo(String text, String comment) {
		this(text, comment, DEFAULT_OSZLOP_SZELESSEG);
	}

	public ExcelFejlecMezo(String text, String comment, int width) {
		this(text, comment, width, null);
	}
	
	public ExcelFejlecMezo(String text, String comment, int width, Short backgroundColor) {
		this.text = text;
		this.comment = comment;
		this.width = SZELESSEGI_EGYSEG_EXCEL_ES_POI_HANYADOS*width;
		this.backgroundColor = backgroundColor;
	}

	public String getText() {
		return text;
	}

	public String getComment() {
		return comment;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Short getBackgroundColor() {
		return backgroundColor;
	}
}
