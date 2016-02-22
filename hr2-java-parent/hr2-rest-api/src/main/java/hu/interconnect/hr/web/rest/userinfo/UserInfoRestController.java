package hu.interconnect.hr.web.rest.userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.interconnect.hr.backend.api.dto.UserInfoResponseDTO;
import hu.interconnect.hr.backend.api.service.UserInfoQueryService;

@RestController
@RequestMapping("/api/v1/userinfo")
public class UserInfoRestController {

	@Autowired
	private UserInfoQueryService userInfoQueryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public UserInfoResponseDTO getUserInfo() {
		return userInfoQueryService.getUserInfo();
	}
}