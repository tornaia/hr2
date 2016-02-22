package hu.interconnect.hr.web.rest.reports.receiveditems;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hu.interconnect.excel.Excel;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.Riport;
import hu.interconnect.hr.module.reports.receiveditems.ReceivedItemsRowCreator;
import hu.interconnect.hr.web.rest.reports.AbstractReportDownloadController;

@Controller
public class ReceivedItemsReportDownloadController extends AbstractReportDownloadController {

	@Autowired
	private ReceivedItemsRowCreator receivedItemsRowCreator;
	
	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@RequestMapping(value = "atvettEszkozok", method={RequestMethod.GET})
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response, @RequestParam int tsz) throws IOException {
		List<Map<String, Object>> sorok = receivedItemsRowCreator.eloallit(tsz);
		
		Excel excel = Riport.ATVETT_ESZKOZOK_SABLON.getExcelSablon();
		excel.writeRows(sorok);
		
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findByIdFetchelve(tsz);
		String teljesNev = szemelyitorzs.getSzemelyiAdatok() == null ? StringUtils.EMPTY : szemelyitorzs.getTeljesNev();
		
		String filenev = "atvett_eszkozok_" + teljesNev + ".xls";
		setHeader(filenev, request, response);
		excel.write(response.getOutputStream());

		return null;
	}
}
