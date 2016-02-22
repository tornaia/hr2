package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.FelhasznaloCreateDTO;
import hu.interconnect.hr.backend.api.dto.FelhasznaloEditDTO;

public interface UsersCommandService {

	void create(FelhasznaloCreateDTO dto);

	void edit(FelhasznaloEditDTO dto);

	void delete(int id);

}