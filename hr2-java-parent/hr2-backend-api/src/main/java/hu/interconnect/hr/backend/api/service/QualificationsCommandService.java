package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.KepzettsegCreateDTO;
import hu.interconnect.hr.backend.api.dto.KepzettsegEditDTO;

public interface QualificationsCommandService {

	void create(KepzettsegCreateDTO dto);

	void edit(KepzettsegEditDTO dto);

	void delete(int id);

}