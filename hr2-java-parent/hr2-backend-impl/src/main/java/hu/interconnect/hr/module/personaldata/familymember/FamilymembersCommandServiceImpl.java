package hu.interconnect.hr.module.personaldata.familymember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.CsaladtagCreateDTO;
import hu.interconnect.hr.backend.api.dto.CsaladtagEditDTO;
import hu.interconnect.hr.backend.api.service.FamilymembersCommandService;
import hu.interconnect.hr.dao.CsaladtagDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Csaladtag;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class FamilymembersCommandServiceImpl implements FamilymembersCommandService {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private CsaladtagDAO csaladtagDAO;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void create(CsaladtagCreateDTO dto) {
		Csaladtag csaladtag = toDomain(dto);
		csaladtagDAO.persist(csaladtag);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void edit(CsaladtagEditDTO dto) {
		Csaladtag csaladtag = csaladtagDAO.findById(dto.getId());
		csaladtag.merge(dto);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void delete(int id) {
		csaladtagDAO.delete(id);
	}
	
	private Csaladtag toDomain(CsaladtagCreateDTO dto) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(dto.getTsz());
		return new Csaladtag(szemelyitorzs, dto.getNev(), dto.getSzuletesiDatum(), dto.getTaj(), dto.getMegjegyzes());
	}
}
