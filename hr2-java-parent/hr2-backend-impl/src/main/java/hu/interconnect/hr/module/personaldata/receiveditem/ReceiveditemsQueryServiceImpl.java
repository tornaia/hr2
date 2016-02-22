package hu.interconnect.hr.module.personaldata.receiveditem;

import static hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus.CSATOLMANYTARHELY;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.AtvettEszkozokResponseDTO;
import hu.interconnect.hr.backend.api.dto.AtvettEszkozokResponseDTO.AtvettEszkozDTO;
import hu.interconnect.hr.backend.api.dto.FileResponseDTO;
import hu.interconnect.hr.backend.api.service.ReceiveditemsQueryService;
import hu.interconnect.hr.dao.AtvettEszkozDAO;
import hu.interconnect.hr.dao.RendszerParameterDAO;
import hu.interconnect.hr.domain.AtvettEszkoz;
import hu.interconnect.hr.domain.RendszerParameter;
import hu.interconnect.hr.security.AuthorityConstants;
import hu.interconnect.util.FileUtils;

@Component
public class ReceiveditemsQueryServiceImpl implements ReceiveditemsQueryService {

	@Autowired
	private RendszerParameterDAO rendszerParameterDAO;

	@Autowired
	private AtvettEszkozDAO atvettEszkozDAO;
	
	private static final Function<AtvettEszkoz, AtvettEszkozDTO> ATVETT_ESZKOZ_TO_DTO_FUNCTION = (AtvettEszkoz a) -> new AtvettEszkozDTO(a.getId(), a.getMegnevezes(), a.getMegjegyzes(), a.getEredetiNev());

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public AtvettEszkozokResponseDTO findAllByTsz(int tsz) {
		List<AtvettEszkoz> atvettEszkozok = atvettEszkozDAO.findAllByTsz(tsz);
		return new AtvettEszkozokResponseDTO(atvettEszkozok.stream().map(ATVETT_ESZKOZ_TO_DTO_FUNCTION).collect(toList()));
	}
	

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public FileResponseDTO download(int id) {
		AtvettEszkoz atvettEszkoz = atvettEszkozDAO.findById(id);
		byte[] adat = getAdat(atvettEszkoz.getId());
		return new FileResponseDTO(atvettEszkoz.getEredetiNev(), adat);
	}
	
	private byte[] getAdat(int atvettEszkozId) {
		 return FileUtils.getByteArray(getCsatolmanytarhelyUtvonal() + atvettEszkozId);
	}

	private String getCsatolmanytarhelyUtvonal() {
		RendszerParameter csatolmanytarhelyParameter = rendszerParameterDAO.findByTipus(CSATOLMANYTARHELY);
		return csatolmanytarhelyParameter.getErtek();
	}
}
