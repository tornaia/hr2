package hu.interconnect.hr.module.valuesets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemCreateDTO;
import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemEditDTO;
import hu.interconnect.hr.backend.api.service.ValuesetsCommandService;
import hu.interconnect.hr.dao.ErtekkeszletElemDAO;
import hu.interconnect.hr.domain.Allampolgarsag;
import hu.interconnect.hr.domain.ErtekkeszletElem;
import hu.interconnect.hr.domain.FEOR;
import hu.interconnect.hr.domain.FoglalkozasiViszony;
import hu.interconnect.hr.domain.FoglalkoztatasJellege;
import hu.interconnect.hr.domain.Koltseghely;
import hu.interconnect.hr.domain.Munkakor;
import hu.interconnect.hr.domain.SzervezetiEgyseg;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class ValuesetsCommandServiceImpl implements ValuesetsCommandService {

	@Autowired
	private ErtekkeszletElemDAO ertekkeszletElemDAO;
	
	@Autowired
	private ValuesetDeleteValidator valuesetDeleteValidator;
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void create(ErtekkeszletElemCreateDTO dto) {
		ErtekkeszletElem ertekkeszletElem = toDomain(dto);
		ertekkeszletElemDAO.persist(ertekkeszletElem);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void edit(ErtekkeszletElemEditDTO dto) {
		ErtekkeszletElem ertekkeszletElem = ertekkeszletElemDAO.findById(dto.getId());
		ertekkeszletElem.merge(dto);
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void delete(int id) {
		ErtekkeszletElem valamilyenErtekkeszletElem = ertekkeszletElemDAO.findById(id);
		valuesetDeleteValidator.validal(valamilyenErtekkeszletElem);
		ertekkeszletElemDAO.delete(valamilyenErtekkeszletElem.getId());
	}
	
	private static ErtekkeszletElem toDomain(ErtekkeszletElemCreateDTO dto) {
		String megnevezesMagyar = dto.getMegnevezesMagyar();
		String megnevezesAngol = dto.getMegnevezesAngol();
		switch (dto.getTipus())  {
			case ALLAMPOLGARSAG : return new Allampolgarsag(megnevezesMagyar, megnevezesAngol);
			case SZERVEZETI_EGYSEG : return new SzervezetiEgyseg(megnevezesMagyar, megnevezesAngol);
			case KOLTSEGHELY : return new Koltseghely(megnevezesMagyar, megnevezesAngol);
			case FOGLALKOZASI_VISZONY : return new FoglalkozasiViszony(megnevezesMagyar, megnevezesAngol);
			case FOGLALKOZTATAS_JELLEGE : return new FoglalkoztatasJellege(megnevezesMagyar, megnevezesAngol);
			case FEOR : return new FEOR(megnevezesMagyar, megnevezesAngol);
			case MUNKAKOR : return new Munkakor(megnevezesMagyar, megnevezesAngol);
			default: throw new ProgramozasiHiba("Nem lekezelt típus: " + dto.getTipus());
		}
	}
}
