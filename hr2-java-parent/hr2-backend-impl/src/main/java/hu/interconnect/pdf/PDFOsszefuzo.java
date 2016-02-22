package hu.interconnect.pdf;

import hu.interconnect.exception.ProgramozasiHiba;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public final class PDFOsszefuzo {

	public static byte[] appendAll(List<ByteArrayOutputStream> pdfek) {
		try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, result);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			
			for (ByteArrayOutputStream baos : pdfek) {
			    PdfReader reader = new PdfReader(baos.toByteArray());
			    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			        document.newPage();
			        //import the page from source pdf
			        PdfImportedPage page = writer.getImportedPage(reader, i);
			        //add the page to the destination pdf
			        cb.addTemplate(page, 0, 0);
			    }
			}
			
			if (pdfek.isEmpty()) {
			    document.add(new Paragraph(" "));
			    document.setPageSize(PageSize.A4);
			    document.newPage();
			}
			
			document.close();
			return result.toByteArray();
		} catch (Exception e) {
			throw new ProgramozasiHiba("Nem sikerult osszefuzni a pdfeket!", e);
		}
	}
	
	private PDFOsszefuzo() {
	}
}
