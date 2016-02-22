package hu.interconnect.hr.module.personaldata.qualification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.KepzettsegCreateDTO;
import hu.interconnect.hr.backend.api.dto.KepzettsegEditDTO;
import hu.interconnect.hr.backend.api.service.QualificationsCommandService;
import hu.interconnect.hr.dao.KepzettsegDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Kepzettseg;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class QualificationsCommandServiceImpl implements QualificationsCommandService {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private KepzettsegDAO kepzettsegDAO;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void create(KepzettsegCreateDTO dto) {
		Kepzettseg kepzettseg = toDomain(dto);
		kepzettsegDAO.persist(kepzettseg);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void edit(KepzettsegEditDTO dto) {
		Kepzettseg k = kepzettsegDAO.findById(dto.getId());
		k.merge(dto);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void delete(int id) {
		kepzettsegDAO.delete(id);
	}
	
	private Kepzettseg toDomain(KepzettsegCreateDTO dto) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(dto.getTsz());
		return new Kepzettseg(szemelyitorzs, dto.getTipus(), dto.getMegnevezes(), dto.getModFok(), dto.getEv(), dto.getErvenyessegVege(), dto.getMegjegyzes());
	}
}
