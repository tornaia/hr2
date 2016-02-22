package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.FenykepekResponseDTO;
import hu.interconnect.hr.backend.api.dto.FileResponseDTO;

public interface PhotosQueryService {

	byte[] get(int id);

	byte[] getMiniatur(int tsz);

	FenykepekResponseDTO getFenykepDTOk(int tsz);

	FileResponseDTO download(int id);

}