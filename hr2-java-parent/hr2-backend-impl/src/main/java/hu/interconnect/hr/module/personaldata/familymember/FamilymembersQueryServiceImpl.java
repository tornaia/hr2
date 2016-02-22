package hu.interconnect.hr.module.personaldata.familymember;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.CsaladtagokResponseDTO;
import hu.interconnect.hr.backend.api.dto.CsaladtagokResponseDTO.CsaladtagDTO;
import hu.interconnect.hr.backend.api.service.FamilymembersQueryService;
import hu.interconnect.hr.dao.CsaladtagDAO;
import hu.interconnect.hr.domain.Csaladtag;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class FamilymembersQueryServiceImpl implements FamilymembersQueryService {

	@Autowired
	private CsaladtagDAO csaladtagDAO;
	
	private static final Function<Csaladtag, CsaladtagDTO> CSALADTAG_TO_DTO_FUNCTION = (Csaladtag cs) -> new CsaladtagDTO(cs.getId(), cs.getNev(), cs.getSzuletesiDatum(), cs.getTaj(), cs.getMegjegyzes());
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public CsaladtagokResponseDTO findAllByTsz(int tsz) {
		List<Csaladtag> csaladtagok = csaladtagDAO.findAllByTsz(tsz);
		return new CsaladtagokResponseDTO(csaladtagok.stream().map(CSALADTAG_TO_DTO_FUNCTION).collect(Collectors.toList()));
	}
}
