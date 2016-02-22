package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasResponseDTO;
import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasokResponseDTO;

public interface ScheduledtasksQueryService {

	IdozitettFuttatasokResponseDTO get();

	IdozitettFuttatasokResponseDTO details(String feladat);

	IdozitettFuttatasResponseDTO detail(int id);

}