package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.AtvettEszkozokResponseDTO;
import hu.interconnect.hr.backend.api.dto.FileResponseDTO;

public interface ReceiveditemsQueryService {

	AtvettEszkozokResponseDTO findAllByTsz(int tsz);

	FileResponseDTO download(int id);

}