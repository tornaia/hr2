package hu.interconnect.hr.backend.api.service;

import java.util.Date;

import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvResponseDTO;

public interface TimeandattendanceQueryService {

	HaviJelenletiIvResponseDTO getHaviJelenletiIv(int tsz, Date honap);

}