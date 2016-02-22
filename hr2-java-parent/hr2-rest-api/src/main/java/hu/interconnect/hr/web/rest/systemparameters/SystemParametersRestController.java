package hu.interconnect.hr.web.rest.systemparameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.RendszerParameterekResponseDTO;
import hu.interconnect.hr.backend.api.dto.RendszerParameterekEditDTO;
import hu.interconnect.hr.backend.api.service.SystemParametersCommandService;
import hu.interconnect.hr.backend.api.service.SystemParametersQueryService;

@RestController
@RequestMapping("/api/v1/systemparameters")
public class SystemParametersRestController {

	private SystemParametersQueryService systemParametersQueryService;
	
	private SystemParametersCommandService systemParametersCommandService;
	
	@Autowired
	public SystemParametersRestController(SystemParametersQueryService systemParametersQueryService, SystemParametersCommandService systemParametersCommandService) {
		this.systemParametersQueryService = systemParametersQueryService;
		this.systemParametersCommandService = systemParametersCommandService;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public RendszerParameterekResponseDTO list() {
		return systemParametersQueryService.getAll();
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	public void edit(@RequestBody RendszerParameterekEditDTO dto) {
		systemParametersCommandService.edit(dto);
	}
}
