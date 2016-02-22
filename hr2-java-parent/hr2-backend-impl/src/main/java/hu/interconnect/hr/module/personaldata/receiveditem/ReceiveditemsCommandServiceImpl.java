package hu.interconnect.hr.module.personaldata.receiveditem;

import static hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus.CSATOLMANYTARHELY;
import static hu.interconnect.util.FileUtils.writeTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.AtvettEszkozCreateDTO;
import hu.interconnect.hr.backend.api.dto.AtvettEszkozEditDTO;
import hu.interconnect.hr.backend.api.service.ReceiveditemsCommandService;
import hu.interconnect.hr.dao.AtvettEszkozDAO;
import hu.interconnect.hr.dao.RendszerParameterDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.AtvettEszkoz;
import hu.interconnect.hr.domain.RendszerParameter;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class ReceiveditemsCommandServiceImpl implements ReceiveditemsCommandService {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private RendszerParameterDAO rendszerParameterDAO;

	@Autowired
	private AtvettEszkozDAO atvettEszkozDAO;

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void create(AtvettEszkozCreateDTO dto) {
		AtvettEszkoz atvettEszkoz = toDomain(dto);
		atvettEszkozDAO.persist(atvettEszkoz);

		byte[] adat = dto.getAdat();
		if (adat != null) {
			RendszerParameter csatolmanytarhelyParameter = rendszerParameterDAO.findByTipus(CSATOLMANYTARHELY);
			String konyvtarUtvonal = csatolmanytarhelyParameter.getErtek();
			String kepFileName = "" + atvettEszkoz.getId();
			writeTo(konyvtarUtvonal + kepFileName, adat);
		}
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void edit(AtvettEszkozEditDTO dto) {
		AtvettEszkoz perzisztaltAtvettEszkoz = atvettEszkozDAO.findById(dto.getId());
		perzisztaltAtvettEszkoz.merge(dto);

		byte[] adat = dto.getAdat();
		if (adat != null) {
			RendszerParameter csatolmanytarhelyParameter = rendszerParameterDAO.findByTipus(CSATOLMANYTARHELY);
			String konyvtarUtvonal = csatolmanytarhelyParameter.getErtek();
			String kepFileName = "" + perzisztaltAtvettEszkoz.getId();
			writeTo(konyvtarUtvonal + kepFileName, adat);
		}
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void delete(int id) {
		atvettEszkozDAO.delete(id);
	}
	
	private AtvettEszkoz toDomain(AtvettEszkozCreateDTO dto) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(dto.getTsz());
		return new AtvettEszkoz(szemelyitorzs, dto.getMegnevezes(), dto.getMegjegyzes(), dto.getEredetiNev(), dto.getAdat());
	}
}
