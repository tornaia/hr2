package hu.interconnect.hr.web.rest.valuesets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemCreateDTO;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemEditDTO;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemResponseDTO;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemekResponseDTO;
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;
import hu.interconnect.hr.backend.api.service.ValuesetsCommandService;
import hu.interconnect.hr.backend.api.service.ValuesetsQueryService;

@RestController
@RequestMapping("/api/v1/valuesets")
public class ValuesetsRestController {

	private ValuesetsQueryService valuesetsQueryService;
	
	private ValuesetsCommandService valuesetsCommandService;
	
	@Autowired
	public ValuesetsRestController(ValuesetsQueryService valuesetsQueryService, ValuesetsCommandService valuesetsCommandService) {
		this.valuesetsQueryService = valuesetsQueryService;
		this.valuesetsCommandService = valuesetsCommandService;
	}
	
	@RequestMapping(value = "/getbyid", method=RequestMethod.GET)
	public ErtekkeszletElemResponseDTO getById(@RequestParam int id) {
		return valuesetsQueryService.getById(id);
	}
	
	@RequestMapping(value = "/getbytype", method=RequestMethod.GET)
	public ErtekkeszletElemekResponseDTO getOsszesAdottTipusu(@RequestParam ErtekkeszletElemTipus type) {
		return valuesetsQueryService.getOsszesAdottTipusu(type);
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public ErtekkeszletElemekResponseDTO getOsszes() {
		return valuesetsQueryService.getOsszes();
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody ErtekkeszletElemCreateDTO dto) {
		valuesetsCommandService.create(dto);
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	public void edit(@RequestBody ErtekkeszletElemEditDTO dto) {
		valuesetsCommandService.edit(dto);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void delete(@PathVariable int id) {
		valuesetsCommandService.delete(id);
	}
}
