package hu.interconnect.hr.module.valuesets;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemResponseDTO;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemekResponseDTO;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemekResponseDTO.ErtekkeszletElemDTO;
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;
import hu.interconnect.hr.backend.api.service.ValuesetsQueryService;
import hu.interconnect.hr.dao.ErtekkeszletElemDAO;
import hu.interconnect.hr.domain.ErtekkeszletElem;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class ValuesetsQueryServiceImpl implements ValuesetsQueryService {

	@Autowired
	private ErtekkeszletElemDAO ertekkeszletElemDAO;
	
	private static final Function<ErtekkeszletElem, ErtekkeszletElemResponseDTO> ERTEKKESZLET_ELEM_TO_ERTEKKESZLET_ELEM_DTO_FUNCTION = (ErtekkeszletElem e) -> new ErtekkeszletElemResponseDTO(e.getId(), e.getTipus(), e.getMegnevezesMagyar(), e.getMegnevezesAngol());

	private static final Function<ErtekkeszletElem, ErtekkeszletElemDTO> ERTEKKESZLET_ELEM_TO_ERTEKKESZLET_ELEMEK_RESPONSE_DTO_FUNCTION = (ErtekkeszletElem e) -> new ErtekkeszletElemDTO(e.getId(), e.getTipus(), e.getMegnevezesMagyar(), e.getMegnevezesAngol());

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO + AuthorityConstants.OR + AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public ErtekkeszletElemResponseDTO getById(int id) {
		ErtekkeszletElem ertekkeszletElem = ertekkeszletElemDAO.findById(id);
		return ERTEKKESZLET_ELEM_TO_ERTEKKESZLET_ELEM_DTO_FUNCTION.apply(ertekkeszletElem);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.PERMIT_ALL)
	public ErtekkeszletElemekResponseDTO getOsszesAdottTipusu(ErtekkeszletElemTipus tipus) {
		List<ErtekkeszletElem> osszesAdottTipusu = ertekkeszletElemDAO.getOsszesAdottTipusu(tipus);
		return new ErtekkeszletElemekResponseDTO(osszesAdottTipusu.stream().map(ERTEKKESZLET_ELEM_TO_ERTEKKESZLET_ELEMEK_RESPONSE_DTO_FUNCTION).collect(Collectors.toList()));
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.PERMIT_ALL)
	public ErtekkeszletElemekResponseDTO getOsszes() {
		List<ErtekkeszletElem> osszes = ertekkeszletElemDAO.findAll();
		return new ErtekkeszletElemekResponseDTO(osszes.stream().map(ERTEKKESZLET_ELEM_TO_ERTEKKESZLET_ELEMEK_RESPONSE_DTO_FUNCTION).collect(Collectors.toList()));
	}
}
