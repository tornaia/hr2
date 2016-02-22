package hu.interconnect.hr.web.rest.scheduledtasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasResponseDTO;
import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasokResponseDTO;
import hu.interconnect.hr.backend.api.service.ScheduledtasksCommandService;
import hu.interconnect.hr.backend.api.service.ScheduledtasksQueryService;

@RestController
@RequestMapping("/api/v1/scheduledtasks")
public class ScheduledtasksRestController {

	private ScheduledtasksQueryService scheduledtasksQueryService;
	
	private ScheduledtasksCommandService scheduledtasksCommandService;
	
	@Autowired
	public ScheduledtasksRestController(ScheduledtasksQueryService scheduledtasksQueryService, ScheduledtasksCommandService scheduledtasksCommandService) {
		this.scheduledtasksQueryService = scheduledtasksQueryService;
		this.scheduledtasksCommandService = scheduledtasksCommandService;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public IdozitettFuttatasokResponseDTO get() {
		return scheduledtasksQueryService.get();
	}
	
	@RequestMapping(value = "/details/{feladat}", method=RequestMethod.GET)
	public IdozitettFuttatasokResponseDTO details(@PathVariable String feladat) {
		return scheduledtasksQueryService.details(feladat);
	}
	
	@RequestMapping(value = "/detail/{id}", method=RequestMethod.GET)
	public IdozitettFuttatasResponseDTO details(@PathVariable int id) {
		return scheduledtasksQueryService.detail(id);
	}
	
	@RequestMapping(value = "/start/{feladat}", method=RequestMethod.POST)
	public void start(@PathVariable String feladat) {
		scheduledtasksCommandService.start(feladat);
	}
}
