package hu.interconnect.hr.web.rest.reports.monthlypayrolldata;

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
import hu.interconnect.hr.dao.KivetelnapDAO;
import hu.interconnect.hr.module.exceptiondays.KivetelnapokHelper;
import hu.interconnect.hr.module.reports.Riport;
import hu.interconnect.hr.module.reports.monthlypayrolldata.MonthlyPayrollDataRowCreator;
import hu.interconnect.hr.web.rest.reports.AbstractReportDownloadController;

@Controller
public class MonthlyPayrollDataReportDownloadController extends AbstractReportDownloadController {

	@Autowired
	private MonthlyPayrollDataRowCreator monthlyPayrollDataRowCreator;
	
	@Autowired
	private KivetelnapDAO kivetelnapDAO;
	
	@RequestMapping(value = "haviSzamfejtesiAdatok", method={RequestMethod.GET})
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response, @RequestParam @DateTimeFormat(pattern=HONAP_FORMATUM) Date honap) throws IOException {
		List<Map<String, Object>> sorok = monthlyPayrollDataRowCreator.eloallit(honap);
		KivetelnapokHelper kivetelnapok = new KivetelnapokHelper(kivetelnapDAO.findAll());
		Excel excel = Riport.HAVI_SZAMFEJTESI_ADATOK_SABLON.getExcelSablon(honap, kivetelnapok);
		excel.writeRows(sorok);
		
		String filenev = "havi_szamfejtesi_adatok_" + honapFormaz(honap) + ".xls";
		setHeader(filenev, request, response);
		excel.write(response.getOutputStream());

		return null;
	}
}
