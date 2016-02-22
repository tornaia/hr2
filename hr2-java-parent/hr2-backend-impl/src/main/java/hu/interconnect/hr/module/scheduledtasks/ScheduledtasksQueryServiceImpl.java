package hu.interconnect.hr.module.scheduledtasks;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasResponseDTO;
import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasokResponseDTO;
import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasokResponseDTO.IdozitettFuttatasDTO;
import hu.interconnect.hr.backend.api.service.ScheduledtasksQueryService;
import hu.interconnect.hr.dao.IdozitettFuttatasDAO;
import hu.interconnect.hr.domain.IdozitettFuttatas;
import hu.interconnect.hr.security.AuthorityConstants;
import hu.interconnect.scheduler.AbstractIdozitettFeladat;

@Component
public class ScheduledtasksQueryServiceImpl implements ScheduledtasksQueryService {

	private static final int EREDMENY_MAX_LENGTH_ON_DETAILS = 512;

	@Autowired
	private List<AbstractIdozitettFeladat> feladatok;
	
	@Autowired
	private IdozitettFuttatasDAO idozitettFuttatasDAO;
	
	private static final Function<IdozitettFuttatas, IdozitettFuttatasDTO> IDOZITETT_FUTTATAS_TO_DTO_FUNCTION = (IdozitettFuttatas i) -> new IdozitettFuttatasDTO(i.getId(), i.getAzonosito(), i.getInditas(), i.getBefejezes(), StringUtils.substring(i.getEredmeny(), 0, EREDMENY_MAX_LENGTH_ON_DETAILS));
	
	private static final Function<IdozitettFuttatas, IdozitettFuttatasResponseDTO> IDOZITETT_FUTTATAS_TO_RESPONSE_DTO_FUNCTION = (IdozitettFuttatas i) -> new IdozitettFuttatasResponseDTO(i.getAzonosito(), i.getInditas(), i.getBefejezes(), i.getEredmeny());

	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public IdozitettFuttatasokResponseDTO get() {
		return new IdozitettFuttatasokResponseDTO(feladatok
													.stream()
													.map(feladat -> idozitettFuttatasDAO.findUtolsoByAzonosito(feladat.getClass().getSimpleName()))
													.map(IDOZITETT_FUTTATAS_TO_DTO_FUNCTION)
													.collect(Collectors.toList()));
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public IdozitettFuttatasokResponseDTO details(String feladat) {
		List<IdozitettFuttatas> idozitettFuttatasReszletek = idozitettFuttatasDAO.findByAzonosito(feladat);
		return new IdozitettFuttatasokResponseDTO(idozitettFuttatasReszletek.stream().map(IDOZITETT_FUTTATAS_TO_DTO_FUNCTION).collect(Collectors.toList()));
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public IdozitettFuttatasResponseDTO detail(int id) {
		IdozitettFuttatas idozitettFuttatas = idozitettFuttatasDAO.findById(id);
		return IDOZITETT_FUTTATAS_TO_RESPONSE_DTO_FUNCTION.apply(idozitettFuttatas);
	}
}
