package hu.interconnect.hr.web.rest.reports.vacation;

import static hu.interconnect.util.DateUtils.HONAP_FORMATUM;
import static hu.interconnect.util.DateUtils.honapFormaz;

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
import hu.interconnect.hr.module.reports.vacation.VacationRowCreator;
import hu.interconnect.hr.web.rest.reports.AbstractReportDownloadController;

@Controller
public class VacationReportDownloadController extends AbstractReportDownloadController {

	@Autowired
	private VacationRowCreator vacationRowCreator;
	
	@RequestMapping(value = "szabadsag", method={RequestMethod.GET})
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response, @RequestParam @DateTimeFormat(pattern=HONAP_FORMATUM) Date honap) throws IOException {
		List<Map<String, Object>> sorok = vacationRowCreator.eloallit(honap);
		
		Excel excel = Riport.SZABADSAG_SABLON.getExcelSablon();
		excel.writeRows(sorok);
		
		String filenev = "szabadsag_" + honapFormaz(honap) + ".xls";
		setHeader(filenev, request, response);
		excel.write(response.getOutputStream());

		return null;
	}
}
