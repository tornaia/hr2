package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatokResponseDTO;

public interface JobhistoryQueryService {

	MunkakoriHistorikusAdatokResponseDTO findAllByTsz(int tsz);

}