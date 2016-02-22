package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.FenykepCreateDTO;
import hu.interconnect.hr.backend.api.dto.FenykepekSaveDTO;

public interface PhotosCommandService {

	void create(FenykepCreateDTO dto);

	void save(FenykepekSaveDTO dto);

	void delete(int id);

}