package hu.interconnect.hr.backend.api.service;

import java.util.Date;

import hu.interconnect.hr.backend.api.dto.KivetelnapokResponseDTO;

public interface ExceptiondaysQueryService {

	KivetelnapokResponseDTO getOsszesKivetelnap();

	KivetelnapokResponseDTO getOsszesKivetelnap(Date month);

}