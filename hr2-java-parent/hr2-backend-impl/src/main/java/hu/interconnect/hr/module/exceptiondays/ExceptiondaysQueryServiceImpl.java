package hu.interconnect.hr.module.exceptiondays;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.KivetelnapokResponseDTO;
import hu.interconnect.hr.backend.api.dto.KivetelnapokResponseDTO.KivetelnapDTO;
import hu.interconnect.hr.backend.api.service.ExceptiondaysQueryService;
import hu.interconnect.hr.dao.KivetelnapDAO;
import hu.interconnect.hr.domain.Kivetelnap;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class ExceptiondaysQueryServiceImpl implements ExceptiondaysQueryService {

	@Autowired
	private KivetelnapDAO kivetelnapDAO;
	
	private static final Function<Kivetelnap, KivetelnapDTO> KIVETELNAP_TO_DTO_FUNCTION = (Kivetelnap k) -> new KivetelnapDTO(k.getId(), k.getDatum(), k.getTipus());
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_NEM_VEDETT)
	public KivetelnapokResponseDTO getOsszesKivetelnap() {
		List<Kivetelnap> kivetelnapok = kivetelnapDAO.findAllByDateDesc();
		return new KivetelnapokResponseDTO(kivetelnapok.stream().map(KIVETELNAP_TO_DTO_FUNCTION).collect(Collectors.toList()));
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_NEM_VEDETT)
	public KivetelnapokResponseDTO getOsszesKivetelnap(Date month) {
		List<Kivetelnap> kivetelnapok = kivetelnapDAO.findByMonth(month);
		return new KivetelnapokResponseDTO(kivetelnapok.stream().map(KIVETELNAP_TO_DTO_FUNCTION).collect(Collectors.toList()));
	}
}
