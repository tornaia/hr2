package hu.interconnect.hr.module.personaldata.jobhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatCreateDTO;
import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatEditDTO;
import hu.interconnect.hr.backend.api.service.JobhistoryCommandService;
import hu.interconnect.hr.dao.MunkakoriHistorikusAdatDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.FEOR;
import hu.interconnect.hr.domain.MunkakoriHistorikusAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class JobhistoryCommandServiceImpl implements JobhistoryCommandService {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private MunkakoriHistorikusAdatDAO munkakoriHistorikusAdatDAO;

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void create(MunkakoriHistorikusAdatCreateDTO dto) {
		MunkakoriHistorikusAdat munkakoriHistorikusAdat = toDomain(dto);
		munkakoriHistorikusAdatDAO.persist(munkakoriHistorikusAdat);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void edit(MunkakoriHistorikusAdatEditDTO dto) {
		MunkakoriHistorikusAdat m = munkakoriHistorikusAdatDAO.findById(dto.getId());
		m.merge(dto);
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void delete(int id) {
		munkakoriHistorikusAdatDAO.delete(id);
	}
	
	private MunkakoriHistorikusAdat toDomain(MunkakoriHistorikusAdatCreateDTO dto) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(dto.getTsz());
		FEOR fEOR = new FEOR(dto.getfEOR());
		return new MunkakoriHistorikusAdat(szemelyitorzs, dto.getDatum(), fEOR, dto.getFizetes());
	}
}
