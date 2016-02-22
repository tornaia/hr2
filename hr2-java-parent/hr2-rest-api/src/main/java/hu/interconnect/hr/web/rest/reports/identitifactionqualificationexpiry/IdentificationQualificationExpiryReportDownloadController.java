package hu.interconnect.hr.web.rest.reports.identitifactionqualificationexpiry;

import static hu.interconnect.util.DateUtils.NAP_FORMATUM;
import static hu.interconnect.util.DateUtils.napFormaz;

import java.io.IOException;
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

import hu.interconnect.excel.Excel;
import hu.interconnect.hr.module.reports.Riport;
import hu.interconnect.hr.module.reports.identitifactionqualificationexpiry.IdentificationQualificationExpiryRowCreator;
import hu.interconnect.hr.web.rest.reports.AbstractReportDownloadController;

@Controller
public class IdentificationQualificationExpiryReportDownloadController extends AbstractReportDownloadController {

	@Autowired
	private IdentificationQualificationExpiryRowCreator identificationQualificationExpiryRowCreator;
	
	@RequestMapping(value = "igazolvanyokEsKepzettsegekLejarat", method={RequestMethod.GET})
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response, @RequestParam @DateTimeFormat(pattern=NAP_FORMATUM) Date kezdet, @RequestParam @DateTimeFormat(pattern=NAP_FORMATUM) Date veg) throws IOException {
		List<Map<String, Object>> sorok = identificationQualificationExpiryRowCreator.eloallit(kezdet, veg);
		
		Excel excel = Riport.IGAZOLVANYOK_ES_KEPZETTSEGEK_LEJARAT_SABLON.getExcelSablon();
		excel.writeRows(sorok);
		
		String filenev = "igazolvanyok_es_kepzettsegek_lejarat_" + napFormaz(kezdet) + "-" + napFormaz(veg) + ".xls";
		setHeader(filenev, request, response);
		excel.write(response.getOutputStream());

		return null;
	}
}
