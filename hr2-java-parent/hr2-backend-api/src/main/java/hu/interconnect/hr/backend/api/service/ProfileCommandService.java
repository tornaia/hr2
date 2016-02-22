package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.ProfileEditDTO;

public interface ProfileCommandService {

	void editProfile(ProfileEditDTO dto);
	
}
