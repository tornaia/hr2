package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatCreateDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatSaveDTO;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasDeleteDTO;

public interface VacationsCommandService {

	void create(EvesSzabadsagAdatCreateDTO dto);

	void save(EvesSzabadsagAdatSaveDTO dto);

	void createConsumption(SzabadsagFelhasznalasCreateDTO dto);

	void deleteConsumption(SzabadsagFelhasznalasDeleteDTO dto);

}