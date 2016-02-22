package hu.interconnect.hr.module.scheduledtasks;

import static hu.interconnect.util.DateUtils.aktualisIdo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.service.ScheduledtasksCommandService;
import hu.interconnect.hr.security.AuthorityConstants;
import hu.interconnect.scheduler.AbstractIdozitettFeladat;

@Service
public class ScheduledtasksCommandServiceImpl implements ScheduledtasksCommandService {

	@Autowired
	private List<AbstractIdozitettFeladat> feladatok;
	
	@Autowired
	private TaskScheduler taskScheduler;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void start(String azonosito) {
		Optional<AbstractIdozitettFeladat> optionalFeladat = feladatok.stream().filter(new AbstractIdozitettFeladatAzonositoAlapuPredicate(azonosito)).findFirst();
		AbstractIdozitettFeladat feladat = optionalFeladat.get();
		
		taskScheduler.schedule(new Runnable() {
			@Override
			public void run() {
				feladat.vegrehajtEsEredmenytRogzitEsLeveletKuld();
			}
		}, aktualisIdo());
	}
}
