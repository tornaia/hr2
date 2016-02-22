package hu.interconnect.pdf;

import java.io.OutputStream;
import java.io.StringReader;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;

import com.lowagie.text.pdf.BaseFont;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.enumeration.PDFSablonTipus;
import hu.interconnect.hr.dao.PDFSablonDAO;
import hu.interconnect.hr.domain.PDFSablon;
import hu.interconnect.util.VelocityUtils;

/**
 * Legeneral egy pdf-et egy xhtml template alapjan.
 */
@Component
public class HtmlTemplateAlapuPdfGenerator {

	private static final Log LOGGER = LogFactory.getLog(HtmlTemplateAlapuPdfGenerator.class);

	@Autowired
	private PDFSablonDAO pdfSablonDAO;

	/**
	 * A tipusnak megfelelo sablont legeneralja a kapott adatokkal.
	 */
	public void general(PDFSablonTipus pdfSablonTipus, Map<String, Object> adatok, OutputStream out) {
		PDFSablon sablon = pdfSablonDAO.findByTipus(pdfSablonTipus);
		generalas(sablon.getTartalom(), adatok, out);
	}

	/**
	 * A Stringkent megadott HTML-bol eloallitja a PDF sablont
	 */
	private static void generalas(String sablon, Map<String, Object> adatok, OutputStream out) {
		try {
			// 1. az xhtml template-be beletesszuk az adatokat - velocity
			String velocityOutput = VelocityUtils.generate(sablon, adatok);

			// 2. ebbol lesz egy xhtml -> xml doc
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(velocityOutput)));

			// 3. xhtml -> pdf
			ITextRenderer renderer = new ITextRenderer();

			// a html-ben a font family csak olyan lehet, ami itt meg van adva.
			ITextFontResolver resolver = renderer.getFontResolver();
			resolver.addFont("fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			resolver.addFont("fonts/arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			resolver.addFont("fonts/arialbi.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			resolver.addFont("fonts/ariali.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

			renderer.setDocument(doc, null);

			renderer.layout();
			renderer.createPDF(out);
		} catch (Exception e) {
			LOGGER.error("Nem sikerult a pdf generalas!", e);
			throw new ProgramozasiHiba("Nem sikerult a pdf generalas", e);
		}
	}
}