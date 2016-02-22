package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemCreateDTO;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemEditDTO;

public interface ValuesetsCommandService {

	void create(ErtekkeszletElemCreateDTO dto);

	void edit(ErtekkeszletElemEditDTO dto);

	void delete(int id);

}