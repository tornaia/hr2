package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.CsaladtagokResponseDTO;

public interface FamilymembersQueryService {

	CsaladtagokResponseDTO findAllByTsz(int tsz);

}