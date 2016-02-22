package hu.interconnect.hr.web.rest.personaldata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO;
import hu.interconnect.hr.backend.api.dto.TszekEsNevekDTO;
import hu.interconnect.hr.backend.api.service.PersonaldataCommandService;
import hu.interconnect.hr.backend.api.service.PersonaldataQueryService;

@RestController
@RequestMapping("/api/v1/personaldata")
public class PersonaldataRestController {

	private PersonaldataQueryService personaldataQueryService;
	
	private PersonaldataCommandService personaldataCommandService;

	@Autowired
	public PersonaldataRestController(PersonaldataQueryService personaldataQueryService, PersonaldataCommandService personaldataCommandService) {
		this.personaldataQueryService = personaldataQueryService;
		this.personaldataCommandService = personaldataCommandService;
	}
	
	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public SzemelyitorzsResponseDTO getSzemelyitorzs(@RequestParam int tsz) {
		return personaldataQueryService.getSzemelyitorzs(tsz);
	}
	
	@RequestMapping(value = "/getall", method=RequestMethod.GET)
	public List<Integer> findall() {
		return personaldataQueryService.getOsszesSzemelyitorzs();
	}
	
	@RequestMapping(value = "/getallactive", method=RequestMethod.GET)
	public List<Integer> findallactive() {
		return personaldataQueryService.getOsszesAktivSzemelyitorzs();
	}
	
	@RequestMapping(value = "/getidandnamelist", method=RequestMethod.GET)
	public TszekEsNevekDTO getIdAndNameList(@RequestParam boolean all) {
		return personaldataQueryService.getIdAndNameList(all);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody SzemelyitorzsCreateDTO dto) {
		personaldataCommandService.letrehoz(dto);
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	public void edit(@RequestBody SzemelyitorzsEditDTO dto) {
		personaldataCommandService.szerkeszt(dto);
	}
	
	@RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public void delete(@PathVariable int id) {
		personaldataCommandService.torol(id);
	}
}
