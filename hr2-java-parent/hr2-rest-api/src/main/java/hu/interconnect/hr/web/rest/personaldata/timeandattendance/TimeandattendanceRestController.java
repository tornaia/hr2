package hu.interconnect.hr.web.rest.personaldata.timeandattendance;

import static hu.interconnect.util.DateUtils.HONAP_FORMATUM;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvEditDTO;
import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvResponseDTO;
import hu.interconnect.hr.backend.api.service.TimeandattendanceCommandService;
import hu.interconnect.hr.backend.api.service.TimeandattendanceQueryService;

@RestController
@RequestMapping("/api/v1/timeandattendance")
public class TimeandattendanceRestController {
	
	private TimeandattendanceQueryService timeandattendanceQueryService;
	
	private TimeandattendanceCommandService timeandattendanceCommandService;
	
	@Autowired
	public TimeandattendanceRestController(TimeandattendanceQueryService timeandattendanceQueryService, TimeandattendanceCommandService timeandattendanceCommandService) {
		this.timeandattendanceQueryService = timeandattendanceQueryService;
		this.timeandattendanceCommandService = timeandattendanceCommandService;
	}

	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public HaviJelenletiIvResponseDTO getHaviJelenletiIv(@RequestParam int tsz, @RequestParam @DateTimeFormat(pattern=HONAP_FORMATUM) Date honap) {
		return timeandattendanceQueryService.getHaviJelenletiIv(tsz, honap);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void edit(@RequestBody HaviJelenletiIvEditDTO dto) {
		timeandattendanceCommandService.edit(dto);
	}
	
	@RequestMapping(value = "/lockmonth", method = RequestMethod.POST)
	public void lockMonth(@RequestParam @DateTimeFormat(pattern=HONAP_FORMATUM) Date month) {
		timeandattendanceCommandService.lockMonth(month);
	}
	
	@RequestMapping(value = "/unlockmonth", method = RequestMethod.POST)
	public void unlockMonth(@RequestParam @DateTimeFormat(pattern=HONAP_FORMATUM) Date month) {
		timeandattendanceCommandService.unlockMonth(month);
	}
}