package hu.interconnect.hr.web.rest.reports.detailedlistofpeople;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hu.interconnect.excel.Excel;
import hu.interconnect.hr.module.reports.Riport;
import hu.interconnect.hr.module.reports.detailedlistofpeople.DetailedListOfPeopleRowCreator;
import hu.interconnect.hr.web.rest.reports.AbstractReportDownloadController;

@Controller
public class DetailedListOfPeopleReportDownloadController extends AbstractReportDownloadController {

	@Autowired
	private DetailedListOfPeopleRowCreator detailedListOfPeopleRowCreator;
	
	@RequestMapping(value = "allomanyiLista", method={RequestMethod.GET})
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> sorok = detailedListOfPeopleRowCreator.eloallit();
		
		Excel excel = Riport.ALLOMANYI_LISTA_SABLON.getExcelSablon();
		excel.writeRows(sorok);
		
		String filenev = "allomanyi_lista.xls";
		setHeader(filenev, request, response);
		excel.write(response.getOutputStream());

		return null;
	}
}
