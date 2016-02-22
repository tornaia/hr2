package hu.interconnect.hr.backend.api.service;

import hu.interconnect.hr.backend.api.dto.MegjegyzesSaveDTO;

public interface CommentCommandService {

	void save(MegjegyzesSaveDTO dto);

}