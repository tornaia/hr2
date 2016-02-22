package hu.interconnect.hr.module.users;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import hu.interconnect.exception.UzletiHiba;
import hu.interconnect.hr.backend.api.dto.FelhasznaloCreateDTO;
import hu.interconnect.hr.backend.api.dto.FelhasznaloEditDTO;
import hu.interconnect.hr.backend.api.service.UsersCommandService;
import hu.interconnect.hr.dao.FelhasznaloDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Service
public class UsersCommandServiceImpl implements UsersCommandService {
	
	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private ShaPasswordEncoder encoder;
	
	@Autowired
	private FelhasznaloDAO felhasznaloDAO;

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void create(FelhasznaloCreateDTO dto) {
		Optional<Felhasznalo> optionalFelhasznalo = felhasznaloDAO.findByNev(dto.getNev());
		if (optionalFelhasznalo.isPresent()) {
			throw new UzletiHiba("UZLETIHIBA_FELHASZNALO_LETREHOZAS_AZONOSITO_FOGLALT");
		}
		String kodoltJelszo = encoder.encodePassword(dto.getJelszo(), dto.getNev());
		dto.setJelszo(kodoltJelszo);
		Felhasznalo felhasznalo = toDomain(dto);
		felhasznaloDAO.persist(felhasznalo);
	}

	private Felhasznalo toDomain(FelhasznaloCreateDTO dto) {
		Szemelyitorzs szemelyitorzs = dto.getTsz() != null ? szemelyitorzsDAO.findById(dto.getTsz()) : null;
		return new Felhasznalo(dto.getNev(), dto.getSzerep(), dto.getJelszo(), szemelyitorzs, dto.isEnabled(), dto.getLocale());
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void edit(FelhasznaloEditDTO dto) {
		if (!isEmpty(dto.getJelszo())) {
			dto.setJelszo(encoder.encodePassword(dto.getJelszo(), dto.getNev()));
		}

		Felhasznalo felhasznalo = felhasznaloDAO.loadUserByUsername(dto.getNev());
		felhasznalo.merge(dto);
	}

	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void delete(int id) {
		felhasznaloDAO.delete(id);
	}
}
