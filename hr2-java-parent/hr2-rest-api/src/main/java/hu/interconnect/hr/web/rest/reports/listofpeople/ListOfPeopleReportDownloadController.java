package hu.interconnect.hr.web.rest.reports.listofpeople;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hu.interconnect.excel.Excel;
import hu.interconnect.hr.backend.api.enumeration.RiportAllomanymod;
import hu.interconnect.hr.module.reports.Riport;
import hu.interconnect.hr.module.reports.listofpeople.ListOfPeopleRowCreator;
import hu.interconnect.hr.web.rest.reports.AbstractReportDownloadController;

@Controller
public class ListOfPeopleReportDownloadController extends AbstractReportDownloadController {

	@Autowired
	private ListOfPeopleRowCreator listOfPeopleRowCreator;
	
	@RequestMapping(value = "nevsor", method={RequestMethod.GET})
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response, @RequestParam RiportAllomanymod riportAllomanymod) throws IOException {
		List<Map<String, Object>> sorok = listOfPeopleRowCreator.eloallit(riportAllomanymod);
		
		Excel excel = Riport.NEVSOR_SABLON.getExcelSablon();
		excel.writeRows(sorok);
		
		String filenev = "nevsor_" + riportAllomanymod + ".xls";
		setHeader(filenev, request, response);
		excel.write(response.getOutputStream());

		return null;
	}
}
