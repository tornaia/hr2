package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.FelhasznaloResponseDTO;
import hu.interconnect.hr.backend.api.dto.FelhasznalokResponseDTO;

public interface UsersQueryService {

	FelhasznalokResponseDTO list();

	FelhasznaloResponseDTO get(int id);

}