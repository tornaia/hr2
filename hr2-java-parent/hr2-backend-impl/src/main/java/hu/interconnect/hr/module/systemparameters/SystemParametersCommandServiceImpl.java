package hu.interconnect.hr.module.systemparameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.RendszerParameterekEditDTO;
import hu.interconnect.hr.backend.api.dto.RendszerParameterekEditDTO.RendszerParameterSaveDTO;
import hu.interconnect.hr.backend.api.service.SystemParametersCommandService;
import hu.interconnect.hr.dao.RendszerParameterDAO;
import hu.interconnect.hr.domain.RendszerParameter;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class SystemParametersCommandServiceImpl implements SystemParametersCommandService {

	@Autowired
	private RendszerParameterDAO rendszerParameterDAO;

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void edit(RendszerParameterekEditDTO dtok) {
		for (RendszerParameterSaveDTO dto : dtok.getRendszerParameterek()) {
			RendszerParameter perzisztaltRendszerParameter = rendszerParameterDAO.findByTipus(dto.getTipus());
			perzisztaltRendszerParameter.merge(dto);
		}
	}
}
