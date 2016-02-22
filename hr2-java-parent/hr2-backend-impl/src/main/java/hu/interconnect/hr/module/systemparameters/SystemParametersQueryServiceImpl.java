package hu.interconnect.hr.module.systemparameters;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.RendszerParameterekResponseDTO;
import hu.interconnect.hr.backend.api.dto.RendszerParameterekResponseDTO.RendszerParameterDTO;
import hu.interconnect.hr.backend.api.service.SystemParametersQueryService;
import hu.interconnect.hr.dao.RendszerParameterDAO;
import hu.interconnect.hr.domain.RendszerParameter;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class SystemParametersQueryServiceImpl implements SystemParametersQueryService {

	@Autowired
	private RendszerParameterDAO rendszerParameterDAO;
	
	private static final Function<RendszerParameter, RendszerParameterDTO> RENDSZER_PARAMETER_TO_DTO_FUNCTION = (RendszerParameter r) -> new RendszerParameterDTO(r.getTipus(), r.getErtek());
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public RendszerParameterekResponseDTO getAll() {
		List<RendszerParameter> rendszerParameterek = rendszerParameterDAO.findOsszesRendszerParameter();
		return new RendszerParameterekResponseDTO(rendszerParameterek.stream().map(RENDSZER_PARAMETER_TO_DTO_FUNCTION).collect(Collectors.toList()));
	}
}
