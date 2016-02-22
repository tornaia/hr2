package hu.interconnect.hr.module.personaldata.comment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.MegjegyzesSaveDTO;
import hu.interconnect.hr.backend.api.service.CommentCommandService;
import hu.interconnect.hr.dao.MegjegyzesDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Megjegyzes;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private MegjegyzesDAO megjegyzesDAO;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void save(MegjegyzesSaveDTO dto) {
		Optional<Megjegyzes> optionalMegjegyzes = megjegyzesDAO.findBySzemelyitorzs(dto.getTsz());
		if (!optionalMegjegyzes.isPresent()) {
			Megjegyzes megjegyzes = toDomain(dto);
			megjegyzesDAO.persist(megjegyzes);
		} else {
			Megjegyzes megjegyzes = optionalMegjegyzes.get();
			megjegyzes.merge(dto);
		}
	}

	private Megjegyzes toDomain(MegjegyzesSaveDTO dto) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(dto.getTsz());
		return new Megjegyzes(szemelyitorzs, dto.getSzoveg());
	}
}
