package hu.interconnect.hr.module.exceptiondays;

import static hu.interconnect.util.DateUtils.isHetvege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.KivetelnapCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.KivetelnapTipus;
import hu.interconnect.hr.backend.api.service.ExceptiondaysCommandService;
import hu.interconnect.hr.dao.KivetelnapDAO;
import hu.interconnect.hr.dao.SzabadsagDAO;
import hu.interconnect.hr.domain.Kivetelnap;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class ExceptiondaysCommandServiceImpl implements ExceptiondaysCommandService {

	@Autowired
	private KivetelnapDAO kivetelnapDAO;
	
	@Autowired
	private SzabadsagDAO szabadsagDAO;
	
	@Autowired
	private ExceptiondayCreateValidator kivetelnapLetrehozValidator;

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void create(KivetelnapCreateDTO dto) {
		kivetelnapLetrehozValidator.validate(dto);

		Kivetelnap kivetelnap = toDomain(dto);
		pihenonapLetrehozasaEsetenAzAdottNapraKivettSzabadsagokatTorli(kivetelnap);

		kivetelnapDAO.persist(kivetelnap);
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void delete(int id) {
		Kivetelnap kivetelnap = kivetelnapDAO.findById(id);
		if (kivetelnap.getTipus() == KivetelnapTipus.MUNKANAP && isHetvege(kivetelnap.getDatum())) {
			szabadsagDAO.torolByNap(kivetelnap.getDatum());
		}
		kivetelnapDAO.delete(kivetelnap.getId());
	}

	private void pihenonapLetrehozasaEsetenAzAdottNapraKivettSzabadsagokatTorli(Kivetelnap kivetelnap) {
		if (kivetelnap.getTipus() == KivetelnapTipus.PIHENONAP) {
			szabadsagDAO.torolByNap(kivetelnap.getDatum());
		}
	}
	
	private static Kivetelnap toDomain(KivetelnapCreateDTO dto) {
		return new Kivetelnap(dto.getDatum(), dto.getTipus());
	}
}
