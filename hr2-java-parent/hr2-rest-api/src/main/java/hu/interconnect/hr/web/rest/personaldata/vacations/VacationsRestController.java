package hu.interconnect.hr.web.rest.personaldata.vacations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatCreateDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatGetDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatSaveDTO;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasDeleteDTO;
import hu.interconnect.hr.backend.api.service.VacationsCommandService;
import hu.interconnect.hr.backend.api.service.VacationsQueryService;

@RestController
@RequestMapping("/api/v1/vacations")
public class VacationsRestController {

	private VacationsQueryService vacationsQueryService;
	
	private VacationsCommandService vacationsCommandService;

	@Autowired
	public VacationsRestController(VacationsQueryService vacationsQueryService, VacationsCommandService vacationsCommandService) {
		this.vacationsQueryService = vacationsQueryService;
		this.vacationsCommandService = vacationsCommandService;
	}
	
	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public EvesSzabadsagAdatResponseDTO get(EvesSzabadsagAdatGetDTO dto) {
		return vacationsQueryService.get(dto);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody EvesSzabadsagAdatCreateDTO dto) {
		vacationsCommandService.create(dto);
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public void save(@RequestBody EvesSzabadsagAdatSaveDTO dto) {
		vacationsCommandService.save(dto);
	}
	
	@RequestMapping(value = "/createconsumption", method=RequestMethod.POST)
	public void createConsumption(@RequestBody SzabadsagFelhasznalasCreateDTO dto) {
		vacationsCommandService.createConsumption(dto);
	}

	@RequestMapping(value = "/deleteconsumption", method=RequestMethod.POST)
	public void deleteConsumption(@RequestBody SzabadsagFelhasznalasDeleteDTO dto) {
		vacationsCommandService.deleteConsumption(dto);
	}
}
