package test.integration;

import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static java.util.Objects.isNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemekResponseDTO.ErtekkeszletElemDTO;
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;
import hu.interconnect.hr.web.rest.personaldata.PersonaldataRestController;
import hu.interconnect.hr.web.rest.personaldata.jobhistory.JobhistoryRestController;
import hu.interconnect.hr.web.rest.personaldata.qualification.QualificationsRestController;
import hu.interconnect.hr.web.rest.personaldata.receiveditem.ReceiveditemsRestController;
import hu.interconnect.hr.web.rest.personaldata.timeandattendance.TimeandattendanceRestController;
import hu.interconnect.hr.web.rest.personaldata.vacations.VacationsRestController;
import hu.interconnect.hr.web.rest.scheduledtasks.ScheduledtasksRestController;
import hu.interconnect.hr.web.rest.userinfo.UserInfoRestController;
import hu.interconnect.hr.web.rest.users.UsersRestController;
import hu.interconnect.hr.web.rest.valuesets.ValuesetsRestController;
import test.integration.view.ReportsView;

public abstract class AbstractRestAPIIntTest extends AbstractBackendIntTest {

	@Autowired
	protected UserInfoRestController userInfoRestController;
	
	@Autowired
	protected UsersRestController usersRestController;
	
	@Autowired
	protected PersonaldataRestController personaldataRestController;
	
	@Autowired
	protected ValuesetsRestController valuesetsRestController;
	
	@Autowired
	protected QualificationsRestController qualificationsRestController;
	
	@Autowired
	protected JobhistoryRestController jobhistoryRestController;
	
	@Autowired
	protected ReceiveditemsRestController receiveditemsRestController;
	
	@Autowired
	protected TimeandattendanceRestController timeandattendanceRestController;
	
	@Autowired
	protected VacationsRestController vacationsRestController;
	
	@Autowired
	protected ScheduledtasksRestController scheduledtasksRestController;
	
	@Autowired
	protected FakeMailSender fakeMailSender;

	@Autowired
	protected ReportsView reportsView;
	
	protected int getErtekkeszletElemId(ErtekkeszletElemTipus tipus, final String megnevezesMagyar) {
		Optional<ErtekkeszletElemDTO> optionalErtekkeszletElemDTO = valuesetsRestController.getOsszesAdottTipusu(tipus).ertekkeszletElemek.stream().filter(e -> equalsAndNotNull(megnevezesMagyar, e.megnevezesMagyar)).findFirst();
		assertTrue("ValueSet with type: " + tipus + " and with Hungarian label: " + megnevezesMagyar + " was not found", optionalErtekkeszletElemDTO.isPresent());
		return optionalErtekkeszletElemDTO.get().id;
	}
	
	protected void waitUntilJobsAreRunning() {
		try {
			Thread.sleep(1000L);
			while(scheduledtasksRestController.get().idozitettFuttatasok.stream().anyMatch(p -> isNull(p.befejezes))) {
				Thread.sleep(1000L);
			}
		} catch (InterruptedException e) {
			throw new ProgramozasiHiba("Unexpected exception", e);
		}
	}
}
