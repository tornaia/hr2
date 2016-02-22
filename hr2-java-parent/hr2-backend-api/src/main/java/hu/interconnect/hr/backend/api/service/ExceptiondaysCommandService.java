package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.KivetelnapCreateDTO;

public interface ExceptiondaysCommandService {

	void create(KivetelnapCreateDTO dto);

	void delete(int id);

}