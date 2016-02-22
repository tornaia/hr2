package hu.interconnect.hr.web.rest.reports.attendance;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.util.DateUtils.HONAP_FORMATUM;
import static hu.interconnect.util.DateUtils.honapFormaz;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hu.interconnect.hr.backend.api.dto.FileResponseDTO;
import hu.interconnect.hr.backend.api.enumeration.PDFSablonTipus;
import hu.interconnect.hr.module.reports.attendance.AttendanceRowCreator;
import hu.interconnect.hr.web.rest.reports.AbstractReportDownloadController;
import hu.interconnect.pdf.HtmlTemplateAlapuPdfGenerator;
import hu.interconnect.pdf.PDFOsszefuzo;

@Controller
public class AttendanceReportDownloadController extends AbstractReportDownloadController {

	@Autowired
	private AttendanceRowCreator attendanceRowCreator;
	
	@Autowired
	private HtmlTemplateAlapuPdfGenerator htmlTemplateAlapuPdfGenerator;

	@RequestMapping(value = "jelenletiIv", method={RequestMethod.GET})
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response, @RequestParam @DateTimeFormat(pattern=HONAP_FORMATUM) Date honap) {
		List<Map<String, Object>> laponkentAdatok = attendanceRowCreator.eloallit(honap);
		
		List<ByteArrayOutputStream> pdfLapokKulonBinarisPdfekben = newArrayList();
		for (Map<String, Object> lapAdat : laponkentAdatok) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			htmlTemplateAlapuPdfGenerator.general(PDFSablonTipus.JELENLETI_IV, lapAdat, baos);
			pdfLapokKulonBinarisPdfekben.add(baos);
		}
		
		String filenev = "jelenleti_iv_" + honapFormaz(honap) + ".pdf";
		byte[] pdfBytes = PDFOsszefuzo.appendAll(pdfLapokKulonBinarisPdfekben);
		downloadFile(request, response, new FileResponseDTO(filenev, pdfBytes));

		return null;
	}
}
