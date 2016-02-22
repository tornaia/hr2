package hu.interconnect.hr.web.rest.reports.probationaryperiodend;

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
import hu.interconnect.hr.module.reports.probationaryperiodend.ProbationaryPeriodEndRowCreator;
import hu.interconnect.hr.web.rest.reports.AbstractReportDownloadController;

@Controller
public class ProbationaryPeriodEndReportDownloadController extends AbstractReportDownloadController {

	@Autowired
	private ProbationaryPeriodEndRowCreator probationaryPeriodEndRowCreator;
	
	@RequestMapping(value = "probaidoVege", method={RequestMethod.GET})
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response, @RequestParam @DateTimeFormat(pattern=NAP_FORMATUM) Date kezdet, @RequestParam @DateTimeFormat(pattern=NAP_FORMATUM) Date veg) throws IOException {
		List<Map<String, Object>> sorok = probationaryPeriodEndRowCreator.eloallit(kezdet, veg);
		
		Excel excel = Riport.PROBAIDO_VEGE_SABLON.getExcelSablon();
		excel.writeRows(sorok);
		
		String filenev = "probaido_vege_" + napFormaz(kezdet) + "-" + napFormaz(veg) + ".xls";
		setHeader(filenev, request, response);
		excel.write(response.getOutputStream());

		return null;
	}
}
