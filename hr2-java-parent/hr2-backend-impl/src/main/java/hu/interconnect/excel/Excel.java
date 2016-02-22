package hu.interconnect.excel;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.util.FileUtils;

public class Excel {
	
	public static final short DEFAULT_FEJLEC_HATTERSZIN = IndexedColors.PALE_BLUE.getIndex();
	
	private Workbook munkafuzet;
	
	private static final int ELSO_MUNKALAP = 0;
	private static final int FEJLEC_SOR = 0;
	private static final int ELSO_ADAT_OSZLOP = 0;
	private static final int ELSO_ADAT_SOR = 1;

	private static final short DINAMIKUSAN_GENERALT_EXCEL_TEMPLATE_KOMMENT_POPUP_SZELESSEGE_EGY_CELLA = 1;
	private static final int DINAMIKUSAN_GENERALT_EXCEL_TEMPLATE_KOMMENT_POPUP_MAGASSAGA_EGY_CELLA1 = 1;
	
	private List<ExcelFejlecMezo> sablonFejlecMezok;
	
	public Excel(InputStream is) {
		munkafuzet = getExcel(is);
	}
	
	public Excel(byte[] b) {
		this(new ByteArrayInputStream(b));
	}
	
	public Excel(List<ExcelFejlecMezo> sablonFejlecMezok) {
		this.sablonFejlecMezok = sablonFejlecMezok;
		munkafuzet = new HSSFWorkbook();
		Sheet sheet = munkafuzet.createSheet();
		Row row = sheet.createRow(0);
		HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
		CreationHelper factory = munkafuzet.getCreationHelper();
		for (int i=0;i<sablonFejlecMezok.size();++i) {
			ExcelFejlecMezo mezo = sablonFejlecMezok.get(i);
			Cell createCell = row.createCell(i, Cell.CELL_TYPE_STRING);
			createCell.setCellValue(mezo.getText());
			
			// comment
			HSSFComment comment = patriarch.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short)0, 0, DINAMIKUSAN_GENERALT_EXCEL_TEMPLATE_KOMMENT_POPUP_SZELESSEGE_EGY_CELLA, DINAMIKUSAN_GENERALT_EXCEL_TEMPLATE_KOMMENT_POPUP_MAGASSAGA_EGY_CELLA1));
			comment.setColumn(i);
			comment.setRow(FEJLEC_SOR);
			RichTextString str = factory.createRichTextString(mezo.getComment());
			comment.setString(str);
			
			// formazasok
			sheet.setColumnWidth(i, mezo.getWidth());
			
			CellStyle createCellStyle = munkafuzet.createCellStyle();
			createCellStyle.setFillForegroundColor(DEFAULT_FEJLEC_HATTERSZIN);
			createCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			createCellStyle.setWrapText(true);
			createCell.setCellStyle(createCellStyle);
		}
	}
	
	private static Workbook getExcel(InputStream is) {
		try {
			return WorkbookFactory.create(is);
		} catch (InvalidFormatException e) {
			throw new ProgramozasiHiba("Nem sikerült az excelt betölteni! Érvénytelen formátum!", e);
		} catch (IOException e) {
			throw new ProgramozasiHiba("Nem sikerült az excelt betölteni! IO hiba!", e);
		}
	}
	
	public List<Map<String, String>> getRows() {
		List<Map<String, String>> sorok = newArrayList();
		Sheet munkalap = munkafuzet.getSheetAt(ELSO_MUNKALAP);
		List<String> oszlopok = getOszlopNevek();
		
		Iterator<Row> sorIterator = munkalap.rowIterator();
		if (sorIterator.hasNext()) {
			sorIterator.next();
		}
		while (sorIterator.hasNext()) {
			Row sor = sorIterator.next();
			Map<String, String> entitas = newHashMap();
			for (int i=ELSO_ADAT_OSZLOP;i<oszlopok.size();++i) {
				String oszlop = oszlopok.get(i);
				StringCella cella = new StringCella(sor.getCell(i));
				entitas.put(oszlop, cella.getString());
			}
			sorok.add(entitas);
		}
		return sorok;
	}
	
	public void writeRows(Iterable<Map<String, Object>> entities) {
		Sheet munkalap = munkafuzet.getSheetAt(ELSO_MUNKALAP);
		List<String> oszlopok = getOszlopNevek();

		int sorMutato = ELSO_ADAT_SOR;
		for (Map<String, Object> entity : entities) {
			Row sor = munkalap.createRow(sorMutato);
			int oszlopMutato = ELSO_ADAT_OSZLOP;
			for (String oszlop : oszlopok) {
				Cell cella = sor.createCell(oszlopMutato);
				Object ertek = entity.get(oszlop);
				if (ertek == null) {
					throw new ProgramozasiHiba("NULL erteket kaptam a kulcshoz: " + oszlop);
				}
				Object cellValue = getCellValue(ertek);
				if (cellValue instanceof String) {
					cella.setCellValue(new HSSFRichTextString((String) cellValue));
				} else if (cellValue instanceof Long) {
					cella.setCellValue((Long) cellValue);					
				} else if (cellValue instanceof Integer) {
					cella.setCellValue((Integer) cellValue);					
				} else {
					throw new ProgramozasiHiba("Nem lekezelet cellValue: " + cellValue.getClass().getCanonicalName());
				}
				
				// backgroundcolor if any
				ExcelFejlecMezo fejlecMezo = getFejlecMezo(oszlop);
				if (fejlecMezo != null && fejlecMezo.getBackgroundColor() != null) {
					CellStyle createCellStyle = munkafuzet.createCellStyle();
					createCellStyle.setFillForegroundColor(fejlecMezo.getBackgroundColor());
					createCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					cella.setCellStyle(createCellStyle);
				}
				
				++oszlopMutato;
			}
			++sorMutato;
		}
	}
	
	private static Object getCellValue(Object o) {
		if (o instanceof String) {
			return o;
		} else if (o instanceof Long) {
			return o;
		} else if (o instanceof Integer) {
			return o;
		} else {
			throw new ProgramozasiHiba("Nem lekezelet cellValue: " + o.getClass().getCanonicalName());
		}
	}
	
	public void write(OutputStream out) {
		try {
			munkafuzet.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			closeQuietly(out);
			throw new ProgramozasiHiba("Nem tudtam kiirni az excelt!", e);
		}
	}

	public void write(String path) {
		try {
			FileUtils.mkdirs(new File(path).getParentFile().getAbsolutePath());
			try (OutputStream out = new FileOutputStream(path)) {
				munkafuzet.write(new FileOutputStream(path));
			}
		} catch (FileNotFoundException e) {
			throw new ProgramozasiHiba("Nem tudtam kiirni az excelt!", e);
		} catch (IOException e) {
			throw new ProgramozasiHiba("Nem tudtam kiirni az excelt!", e);
		}
	}
	
	private List<String> getOszlopNevek() {
		Sheet munkalap = munkafuzet.getSheetAt(ELSO_MUNKALAP);
		Row fejlec = munkalap.getRow(FEJLEC_SOR);
		List<String> oszlopok = newArrayList();
		for (int i=ELSO_ADAT_OSZLOP;;i++) {
			Cell fejlecCella = fejlec.getCell(i);
			if (fejlecCella == null) {
				break;
			}
			Comment cellComment = fejlecCella.getCellComment();
			String azonosito;
			if (cellComment != null && cellComment.getString() != null && cellComment.getString().getString() != null && !cellComment.getString().getString().isEmpty()) {
				azonosito = cellComment.getString().getString();
			} else {
				azonosito = fejlecCella.getStringCellValue();
			}
			oszlopok.add(azonosito);
		}
		return oszlopok;
	}
	
	public Cell getCell(Coordinate cellCoordinate) {
		Sheet munkalap = munkafuzet.getSheetAt(ELSO_MUNKALAP);
		Row row = munkalap.getRow(cellCoordinate.row);
		if (row == null) {
			return null;
		}
		return row.getCell(cellCoordinate.col);
	}
	
	private ExcelFejlecMezo getFejlecMezo(String oszlop) {
		if (sablonFejlecMezok == null) {
			return null;
		}
		for (ExcelFejlecMezo mezo : sablonFejlecMezok) {
			if (equalsAndNotNull(mezo.getComment(), oszlop)) {
				return mezo;
			}
		}
		return null;
	}

}
