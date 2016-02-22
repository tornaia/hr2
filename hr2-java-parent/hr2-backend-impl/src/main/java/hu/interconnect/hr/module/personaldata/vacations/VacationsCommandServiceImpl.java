package hu.interconnect.hr.module.personaldata.vacations;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatCreateDTO;
import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatSaveDTO;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasDeleteDTO;
import hu.interconnect.hr.backend.api.service.VacationsCommandService;
import hu.interconnect.hr.dao.EvesSzabadsagAdatDAO;
import hu.interconnect.hr.dao.KivetelnapDAO;
import hu.interconnect.hr.dao.SzabadsagDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.EvesSzabadsagAdat;
import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.exceptiondays.KivetelnapokHelper;
import hu.interconnect.hr.security.AuthorityConstants;
import hu.interconnect.util.DateIterator;

@Service
public class VacationsCommandServiceImpl implements VacationsCommandService {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private SzabadsagDAO szabadsagDAO;
	
	@Autowired
	private EvesSzabadsagAdatDAO evesSzabadsagAdatDAO;

	@Autowired
	private KivetelnapDAO kivetelnapDAO;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void create(EvesSzabadsagAdatCreateDTO dto) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(dto.getTsz());
		EvesSzabadsagAdat evesSzabadsagAdat = new EvesSzabadsagAdat(szemelyitorzs, dto.getEv(), dto.getAlapszabadsag(), dto.getGyermekekUtan(), dto.getFiatalkoru(), dto.getVakSzemely(), dto.getEgyebMunkakor(), dto.getKtKaPotszabadsag(), dto.getKtKaVezetoi(), dto.getEgyebCsokkento(), dto.getTanulmanyi(), dto.getMultEviSzabadsag(), dto.getBszJarandosagAdottEvi());
		evesSzabadsagAdatDAO.persist(evesSzabadsagAdat);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void save(EvesSzabadsagAdatSaveDTO dto) {
		EvesSzabadsagAdat evesSzabadsagAdat = evesSzabadsagAdatDAO.findById(dto.getId());
		evesSzabadsagAdat.merge(dto);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void createConsumption(SzabadsagFelhasznalasCreateDTO dto) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(dto.getTsz());
		KivetelnapokHelper kivetelnapok = new KivetelnapokHelper(kivetelnapDAO.findAll());
		for (Date nap : new DateIterator(dto.getKezdet(), dto.getVeg())) {
			if (kivetelnapok.isMunkanap(nap)) {
				Optional<Szabadsag> optionalSzabadsag = szabadsagDAO.findBySzemelyitorzsEsNap(dto.getTsz(), nap);
				if (optionalSzabadsag.isPresent()) {
					optionalSzabadsag.get().merge(dto);
				} else {
					szabadsagDAO.persist(new Szabadsag(szemelyitorzs, nap, dto.getJelleg()));
				}
			} else {
				szabadsagDAO.removeBySzemelyitorzsEsNap(szemelyitorzs, nap);
			}
		}
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void deleteConsumption(SzabadsagFelhasznalasDeleteDTO dto) {
		szabadsagDAO.removeBySzemelyitorzsEsIdoszak(dto.getTsz(), dto.getKezdet(), dto.getVeg());
	}
}
