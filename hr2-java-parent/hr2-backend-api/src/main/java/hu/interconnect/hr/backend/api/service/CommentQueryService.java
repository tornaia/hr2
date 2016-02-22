package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.MegjegyzesResponseDTO;

public interface CommentQueryService {

	MegjegyzesResponseDTO getMegjegyzes(int tsz);

}