package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatCreateDTO;
import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatEditDTO;

public interface JobhistoryCommandService {

	void create(MunkakoriHistorikusAdatCreateDTO dto);

	void edit(MunkakoriHistorikusAdatEditDTO dto);

	void delete(int id);

}