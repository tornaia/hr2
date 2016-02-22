package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.ProfileResponseDTO;

public interface ProfileQueryService {

	ProfileResponseDTO getByName(String name);

}
