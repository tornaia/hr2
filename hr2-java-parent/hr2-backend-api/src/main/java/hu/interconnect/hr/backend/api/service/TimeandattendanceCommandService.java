package hu.interconnect.hr.backend.api.service;

import java.util.Date;

import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvEditDTO;

public interface TimeandattendanceCommandService {

	void edit(HaviJelenletiIvEditDTO dto);

	void lockMonth(Date month);

	void unlockMonth(Date month);

}