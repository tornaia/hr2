package hu.interconnect.hr.module.personaldata.qualification;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.KepzettsegekResponseDTO;
import hu.interconnect.hr.backend.api.dto.KepzettsegekResponseDTO.KepzettsegDTO;
import hu.interconnect.hr.backend.api.service.QualificationsQueryService;
import hu.interconnect.hr.dao.KepzettsegDAO;
import hu.interconnect.hr.domain.Kepzettseg;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class QualificationsQueryServiceImpl implements QualificationsQueryService {

	@Autowired
	private KepzettsegDAO kepzettsegDAO;
	
	private static final Function<Kepzettseg, KepzettsegDTO> KEPZETTSEG_TO_DTO_FUNCTION = (Kepzettseg k) -> new KepzettsegDTO(k.getId(), k.getTipus(), k.getMegnevezes(), k.getModFok(), k.getEv(), k.getErvenyessegVege(), k.getMegjegyzes());
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public KepzettsegekResponseDTO findAllByTsz(int tsz) {
		List<Kepzettseg> kepzettsegek = kepzettsegDAO.findAllByTsz(tsz);
		return new KepzettsegekResponseDTO(kepzettsegek.stream().map(KEPZETTSEG_TO_DTO_FUNCTION).collect(toList()));
	}
}
