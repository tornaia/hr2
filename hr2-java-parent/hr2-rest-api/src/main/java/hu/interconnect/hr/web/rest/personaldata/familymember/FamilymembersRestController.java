package hu.interconnect.hr.web.rest.personaldata.familymember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.CsaladtagCreateDTO;
import hu.interconnect.hr.backend.api.dto.CsaladtagEditDTO;
import hu.interconnect.hr.backend.api.dto.CsaladtagokResponseDTO;
import hu.interconnect.hr.backend.api.service.FamilymembersCommandService;
import hu.interconnect.hr.backend.api.service.FamilymembersQueryService;

@RestController
@RequestMapping("/api/v1/familymembers")
public class FamilymembersRestController {

	private FamilymembersQueryService familymembersQueryService;
	
	private FamilymembersCommandService familymembersCommandService;

	@Autowired
	public FamilymembersRestController(FamilymembersQueryService familymembersQueryService, FamilymembersCommandService familymembersCommandService) {
		this.familymembersQueryService = familymembersQueryService;
		this.familymembersCommandService = familymembersCommandService;
	}
	
	@RequestMapping(value = "/findall", method=RequestMethod.GET)
	public CsaladtagokResponseDTO findAll(@RequestParam int tsz) {
		return familymembersQueryService.findAllByTsz(tsz);
	}
	
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody CsaladtagCreateDTO dto) {
		familymembersCommandService.create(dto);
	}

	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	public void edit(@RequestBody CsaladtagEditDTO dto) {
		familymembersCommandService.edit(dto);
	}
	
	@RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public void delete(@PathVariable int id) {
		familymembersCommandService.delete(id);
	}
}
