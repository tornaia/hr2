package hu.interconnect.hr.backend.api.service;

import java.util.List;

import hu.interconnect.hr.backend.api.dto.SzemelyitorzsResponseDTO;
import hu.interconnect.hr.backend.api.dto.TszekEsNevekDTO;

public interface PersonaldataQueryService {

	SzemelyitorzsResponseDTO getSzemelyitorzs(int tsz);

	List<Integer> getOsszesSzemelyitorzs();

	List<Integer> getOsszesAktivSzemelyitorzs();

	TszekEsNevekDTO getIdAndNameList(boolean all);

}