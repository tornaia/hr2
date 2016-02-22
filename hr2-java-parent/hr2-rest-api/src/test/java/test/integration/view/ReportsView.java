package test.integration.view;

import static hu.interconnect.util.DateUtils.parseHonap;
import static hu.interconnect.util.DateUtils.parseNap;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;

import hu.interconnect.excel.Excel;
import hu.interconnect.hr.backend.api.enumeration.RiportAllomanymod;
import hu.interconnect.hr.web.rest.reports.attendance.AttendanceReportDownloadController;
import hu.interconnect.hr.web.rest.reports.detailedlistofpeople.DetailedListOfPeopleReportDownloadController;
import hu.interconnect.hr.web.rest.reports.detailedlistofpeopleplussalaryandqualification.DetailedListOfPeoplePlusSalaryAndQualificationReportDownloadController;
import hu.interconnect.hr.web.rest.reports.emailaddresses.EmailAddressesReportDownloadController;
import hu.interconnect.hr.web.rest.reports.entransandleavers.EntrantsAndLeaversReportDownloadController;
import hu.interconnect.hr.web.rest.reports.identitifactionqualificationexpiry.IdentificationQualificationExpiryReportDownloadController;
import hu.interconnect.hr.web.rest.reports.listofpeople.ListOfPeopleReportDownloadController;
import hu.interconnect.hr.web.rest.reports.medicalexaminationexpiry.MedicalExaminationExpiryReportDownloadController;
import hu.interconnect.hr.web.rest.reports.monthlypayrolldata.MonthlyPayrollDataReportDownloadController;
import hu.interconnect.hr.web.rest.reports.personaldata.PersonalDataReportDownloadController;
import hu.interconnect.hr.web.rest.reports.probationaryperiodend.ProbationaryPeriodEndReportDownloadController;
import hu.interconnect.hr.web.rest.reports.receiveditems.ReceivedItemsReportDownloadController;
import hu.interconnect.hr.web.rest.reports.vacation.VacationReportDownloadController;

@Component
public class ReportsView {

	@Autowired
	private MedicalExaminationExpiryReportDownloadController medicalExaminationExpiryReportDownloadController;
	
	@Autowired
	private IdentificationQualificationExpiryReportDownloadController identificationQualificationExpiryReportDownloadController;
	
	@Autowired
	private ProbationaryPeriodEndReportDownloadController probationaryPeriodEndReportDownloadController;
	
	@Autowired
	private ListOfPeopleReportDownloadController listOfPeopleReportDownloadController;
	
	@Autowired
	private DetailedListOfPeopleReportDownloadController detailedListOfPeopleReportDownloadController;

	@Autowired
	private EmailAddressesReportDownloadController emailAddressesReportDownloadController;
	
	@Autowired
	private PersonalDataReportDownloadController personalDataReportDownloadController;
	
	@Autowired
	private VacationReportDownloadController vacationReportDownloadController;
	
	@Autowired
	private AttendanceReportDownloadController attendanceReportDownloadController;
	
	@Autowired
	private EntrantsAndLeaversReportDownloadController entrantsAndLeaversReportDownloadController;
	
	@Autowired
	private ReceivedItemsReportDownloadController receivedItemsReportDownloadController;

	@Autowired
	private DetailedListOfPeoplePlusSalaryAndQualificationReportDownloadController detailedListOfPeoplePlusSalaryAndQualificationReportDownloadController;
	
	@Autowired
	private MonthlyPayrollDataReportDownloadController monthlyPayrollDataReportDownloadController;

	public Excel getUzemorvosiVizsgalatLejarat(String kezdetStr, String vegStr) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		medicalExaminationExpiryReportDownloadController.export(request, response, parseNap(kezdetStr), parseNap(vegStr));
		return new Excel(response.getContentAsByteArray());
	}
	
	public Excel getIgazolvanyokEsKepzettsegekLejarat(String kezdetStr, String vegStr) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		identificationQualificationExpiryReportDownloadController.export(request, response, parseNap(kezdetStr), parseNap(vegStr));
		return new Excel(response.getContentAsByteArray());
	}

	public Excel getProbaidoVege(String kezdetStr, String vegStr) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		probationaryPeriodEndReportDownloadController.export(request, response, parseNap(kezdetStr), parseNap(vegStr));
		return new Excel(response.getContentAsByteArray());
	}
	
	public Excel getNevsor(RiportAllomanymod riportAllomanymod) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		listOfPeopleReportDownloadController.export(request, response, riportAllomanymod);
		return new Excel(response.getContentAsByteArray());
	}
	
	public Excel getAllomanyiLista() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		detailedListOfPeopleReportDownloadController.export(request, response);
		return new Excel(response.getContentAsByteArray());
	}

	public Excel getEmailCimek(RiportAllomanymod riportAllomanymod) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		emailAddressesReportDownloadController.export(request, response, riportAllomanymod);
		return new Excel(response.getContentAsByteArray());
	}

	public Excel getAlapadatok(RiportAllomanymod riportAllomanymod) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		personalDataReportDownloadController.export(request, response, riportAllomanymod);
		return new Excel(response.getContentAsByteArray());
	}

	public Excel getSzabadsag(String honapStr) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		vacationReportDownloadController.export(request, response, parseHonap(honapStr));
		return new Excel(response.getContentAsByteArray());
	}
	
	public byte[] getJelenletiIv(String honapStr) {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		attendanceReportDownloadController.export(request, response, parseHonap(honapStr));
		return response.getContentAsByteArray();
	}
	
	public Excel getBeEsKilepok(String kezdetStr, String vegStr) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		entrantsAndLeaversReportDownloadController.export(request, response, parseNap(kezdetStr), parseNap(vegStr));
		return new Excel(response.getContentAsByteArray());
	}
	
	public Excel getKigyujtesiLista(RiportAllomanymod riportAllomanymod) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		detailedListOfPeoplePlusSalaryAndQualificationReportDownloadController.export(request, response, riportAllomanymod);
		return new Excel(response.getContentAsByteArray());
	}
	
	public Excel getAtvettEszkozok(int tsz) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		receivedItemsReportDownloadController.export(request, response, tsz);
		return new Excel(response.getContentAsByteArray());
	}

	public Excel getHaviSzamfejtesiAdatok(String honapStr) throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		monthlyPayrollDataReportDownloadController.export(request, response, parseHonap(honapStr));
		return new Excel(response.getContentAsByteArray());
	}
}