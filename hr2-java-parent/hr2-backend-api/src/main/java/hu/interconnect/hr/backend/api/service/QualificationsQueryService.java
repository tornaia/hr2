package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.KepzettsegekResponseDTO;

public interface QualificationsQueryService {

	KepzettsegekResponseDTO findAllByTsz(int tsz);

}