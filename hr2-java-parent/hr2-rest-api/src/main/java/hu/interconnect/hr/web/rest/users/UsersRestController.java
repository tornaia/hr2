package hu.interconnect.hr.web.rest.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.FelhasznaloCreateDTO;
import hu.interconnect.hr.backend.api.dto.FelhasznaloEditDTO;
import hu.interconnect.hr.backend.api.dto.FelhasznaloResponseDTO;
import hu.interconnect.hr.backend.api.dto.FelhasznalokResponseDTO;
import hu.interconnect.hr.backend.api.service.UsersCommandService;
import hu.interconnect.hr.backend.api.service.UsersQueryService;

@RestController
@RequestMapping("/api/v1/users")
public class UsersRestController {
	
	private UsersQueryService usersQueryService;
	
	private UsersCommandService usersCommandService;
	
	@Autowired
	public UsersRestController(UsersQueryService usersQueryService, UsersCommandService usersCommandService) {
		this.usersQueryService = usersQueryService;
		this.usersCommandService = usersCommandService;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public FelhasznalokResponseDTO list() {
		return usersQueryService.list();
	}
	
	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public FelhasznaloResponseDTO get(@RequestParam int id) {
		return usersQueryService.get(id);
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public void create(@RequestBody FelhasznaloCreateDTO dto) {
		usersCommandService.create(dto);
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	public void edit(@RequestBody FelhasznaloEditDTO felhasznaloEditDTO) {
		usersCommandService.edit(felhasznaloEditDTO);
	}
	
	@RequestMapping(value = "/delete/{id}", method=RequestMethod.POST)
	public void delete(@PathVariable int id) {
		usersCommandService.delete(id);
	}
}