package hu.interconnect.hr.backend.api.service;


import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsEditDTO;

public interface PersonaldataCommandService {

	void letrehoz(SzemelyitorzsCreateDTO dto);

	void szerkeszt(SzemelyitorzsEditDTO dto);

	void torol(int tsz);

}