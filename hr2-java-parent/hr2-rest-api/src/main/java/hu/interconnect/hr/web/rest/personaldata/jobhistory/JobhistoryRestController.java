package hu.interconnect.hr.web.rest.personaldata.jobhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatCreateDTO;
import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatEditDTO;
import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatokResponseDTO;
import hu.interconnect.hr.backend.api.service.JobhistoryCommandService;
import hu.interconnect.hr.backend.api.service.JobhistoryQueryService;

@RestController
@RequestMapping("/api/v1/jobhistory")
public class JobhistoryRestController {

	private JobhistoryQueryService jobhistoryQueryService;

	private JobhistoryCommandService jobhistoryCommandService;
	
	@Autowired
	public JobhistoryRestController(JobhistoryQueryService jobhistoryQueryService, JobhistoryCommandService jobhistoryCommandService) {
		this.jobhistoryQueryService = jobhistoryQueryService;
		this.jobhistoryCommandService = jobhistoryCommandService;
	}
	
	@RequestMapping(value = "/findall", method=RequestMethod.GET)
	public MunkakoriHistorikusAdatokResponseDTO findAll(@RequestParam int tsz) {
		return jobhistoryQueryService.findAllByTsz(tsz);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody MunkakoriHistorikusAdatCreateDTO dto) {
		jobhistoryCommandService.create(dto);
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	public void edit(@RequestBody MunkakoriHistorikusAdatEditDTO dto) {
		jobhistoryCommandService.edit(dto);
	}
	
	@RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public void delete(@PathVariable int id) {
		jobhistoryCommandService.delete(id);
	}
}
