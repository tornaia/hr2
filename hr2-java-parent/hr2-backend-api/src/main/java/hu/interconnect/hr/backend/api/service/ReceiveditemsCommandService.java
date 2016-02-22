package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.AtvettEszkozCreateDTO;
import hu.interconnect.hr.backend.api.dto.AtvettEszkozEditDTO;

public interface ReceiveditemsCommandService {

	void create(AtvettEszkozCreateDTO dto);

	void edit(AtvettEszkozEditDTO dto);

	void delete(int id);

}