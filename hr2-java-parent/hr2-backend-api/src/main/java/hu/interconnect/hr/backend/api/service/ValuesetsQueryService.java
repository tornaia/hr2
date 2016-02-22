package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemResponseDTO;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemekResponseDTO;
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;

public interface ValuesetsQueryService {

	ErtekkeszletElemResponseDTO getById(int id);

	ErtekkeszletElemekResponseDTO getOsszesAdottTipusu(ErtekkeszletElemTipus tipus);

	ErtekkeszletElemekResponseDTO getOsszes();

}