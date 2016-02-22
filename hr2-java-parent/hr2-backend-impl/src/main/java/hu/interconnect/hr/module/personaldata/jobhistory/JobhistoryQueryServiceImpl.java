package hu.interconnect.hr.module.personaldata.jobhistory;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatokResponseDTO;
import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatokResponseDTO.MunkakoriHistorikusAdatDTO;
import hu.interconnect.hr.backend.api.service.JobhistoryQueryService;
import hu.interconnect.hr.dao.MunkakoriHistorikusAdatDAO;
import hu.interconnect.hr.domain.MunkakoriHistorikusAdat;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class JobhistoryQueryServiceImpl implements JobhistoryQueryService {

	@Autowired
	private MunkakoriHistorikusAdatDAO munkakoriHistorikusAdatDAO;
	
	private static final Function<MunkakoriHistorikusAdat, MunkakoriHistorikusAdatDTO> MUNKAKORI_HISTORIKUS_ADAT_TO_DTO_FUNCTION = (MunkakoriHistorikusAdat m) -> new MunkakoriHistorikusAdatDTO(m.getId(), m.getDatum(), m.getfEOR() != null ? m.getfEOR().getId() : null, m.getFizetes());
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public MunkakoriHistorikusAdatokResponseDTO findAllByTsz(int tsz) {
		List<MunkakoriHistorikusAdat> munkakoriHistorikusAdatok = munkakoriHistorikusAdatDAO.findAllByTsz(tsz);
		return new MunkakoriHistorikusAdatokResponseDTO(munkakoriHistorikusAdatok.stream().map(MUNKAKORI_HISTORIKUS_ADAT_TO_DTO_FUNCTION).collect(Collectors.toList()));
	}
}
