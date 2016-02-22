package hu.interconnect.hr.module.personaldata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.interconnect.exception.UzletiHiba;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;

@Component
public class PersonaldataCreateValidator {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;

	public void validate(SzemelyitorzsCreateDTO dto) {
		if (dto.getTsz() < 1) {
			throw new UzletiHiba("UZLETIHIBA_SZEMELYITORZS_LETREHOZAS_TSZ_ERVENYTELEN");
		}
		if (szemelyitorzsDAO.findById(dto.getTsz()) != null) {
			throw new UzletiHiba("UZLETIHIBA_SZEMELYITORZS_LETREHOZAS_TSZ_FOGLALT");
		}
	}
}
