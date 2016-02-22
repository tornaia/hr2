package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.CsaladtagCreateDTO;
import hu.interconnect.hr.backend.api.dto.CsaladtagEditDTO;

public interface FamilymembersCommandService {

	void create(CsaladtagCreateDTO dto);

	void edit(CsaladtagEditDTO dto);

	void delete(int id);

}