package hu.interconnect.hr.web.rest.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.ProfileEditDTO;
import hu.interconnect.hr.backend.api.dto.ProfileResponseDTO;
import hu.interconnect.hr.backend.api.service.ProfileCommandService;
import hu.interconnect.hr.backend.api.service.ProfileQueryService;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileRestController {
	
	private ProfileQueryService profileQueryService;
	
	private ProfileCommandService profileCommandService;
	
	@Autowired
	public ProfileRestController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
		this.profileQueryService = profileQueryService;
		this.profileCommandService = profileCommandService;
	}
	
	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public ProfileResponseDTO getProfileByName(@RequestParam String name) {
		return profileQueryService.getByName(name);
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST)
	public void editProfile(@RequestBody ProfileEditDTO dto) {
		profileCommandService.editProfile(dto);
	}
}