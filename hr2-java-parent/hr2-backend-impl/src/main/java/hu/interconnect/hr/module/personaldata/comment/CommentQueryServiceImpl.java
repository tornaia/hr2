package hu.interconnect.hr.module.personaldata.comment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.MegjegyzesResponseDTO;
import hu.interconnect.hr.backend.api.service.CommentQueryService;
import hu.interconnect.hr.dao.MegjegyzesDAO;
import hu.interconnect.hr.domain.Megjegyzes;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class CommentQueryServiceImpl implements CommentQueryService {

	@Autowired
	private MegjegyzesDAO megjegyzesDAO;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public MegjegyzesResponseDTO getMegjegyzes(int tsz) {
		Optional<Megjegyzes> optionalMegjegyzes = megjegyzesDAO.findBySzemelyitorzs(tsz);
		if (!optionalMegjegyzes.isPresent()) {
			return MegjegyzesResponseDTO.ures(tsz);
		}
		Megjegyzes megjegyzes = optionalMegjegyzes.get();
		return MegjegyzesResponseDTO.letrehoz(megjegyzes.getSzemelyitorzs().getTsz(), megjegyzes.getSzoveg());
	}
}
