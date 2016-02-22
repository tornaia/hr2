package hu.interconnect.hr.web.rest.personaldata.qualification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.KepzettsegCreateDTO;
import hu.interconnect.hr.backend.api.dto.KepzettsegEditDTO;
import hu.interconnect.hr.backend.api.dto.KepzettsegekResponseDTO;
import hu.interconnect.hr.backend.api.service.QualificationsCommandService;
import hu.interconnect.hr.backend.api.service.QualificationsQueryService;

@RestController
@RequestMapping("/api/v1/qualifications")
public class QualificationsRestController {

	private QualificationsQueryService qualificationsQueryService;
	
	private QualificationsCommandService qualificationsCommandService; 
	
	@Autowired
	public QualificationsRestController(QualificationsQueryService qualificationsQueryService, QualificationsCommandService qualificationsCommandService) {
		this.qualificationsQueryService = qualificationsQueryService;
		this.qualificationsCommandService = qualificationsCommandService;
	}
	
	@RequestMapping(value = "/findall", method=RequestMethod.GET)
	public KepzettsegekResponseDTO findAll(@RequestParam int tsz) {
		return qualificationsQueryService.findAllByTsz(tsz);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody KepzettsegCreateDTO dto) {
		qualificationsCommandService.create(dto);
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	public void edit(@RequestBody KepzettsegEditDTO dto) {
		qualificationsCommandService.edit(dto);
	}
	
	@RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public void delete(@PathVariable int id) {
		qualificationsCommandService.delete(id);
	}
}
