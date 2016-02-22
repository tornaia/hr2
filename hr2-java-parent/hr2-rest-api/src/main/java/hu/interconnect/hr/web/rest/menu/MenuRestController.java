package hu.interconnect.hr.web.rest.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.MenuResponseDTO;
import hu.interconnect.hr.backend.api.service.MenuQueryService;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuRestController {

	@Autowired
	private MenuQueryService menuQueryService;

	@RequestMapping
	public MenuResponseDTO getMenu() {
		return menuQueryService.getMenu();
	}
}