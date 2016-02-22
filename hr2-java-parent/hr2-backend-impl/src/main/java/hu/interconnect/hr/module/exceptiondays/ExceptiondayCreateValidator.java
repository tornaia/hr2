package hu.interconnect.hr.module.exceptiondays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.interconnect.exception.UzletiHiba;
import hu.interconnect.hr.backend.api.dto.KivetelnapCreateDTO;
import hu.interconnect.hr.dao.KivetelnapDAO;

@Component
public class ExceptiondayCreateValidator {

	@Autowired
	private KivetelnapDAO kivetelnapDAO;

	public void validate(KivetelnapCreateDTO dto) {
		if (kivetelnapDAO.findByNap(dto.getDatum()).isPresent()) {
			throw new UzletiHiba("UZLETIHIBA_KIVETELNAP_HASZNALATBAN_VAN");
		}
	}
}
