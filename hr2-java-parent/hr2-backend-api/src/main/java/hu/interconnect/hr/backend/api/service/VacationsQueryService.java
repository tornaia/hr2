package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatGetDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatResponseDTO;

public interface VacationsQueryService {

	EvesSzabadsagAdatResponseDTO get(EvesSzabadsagAdatGetDTO dto);

}