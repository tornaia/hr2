package hu.interconnect.hr.module.profile;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import hu.interconnect.hr.backend.api.dto.ProfileEditDTO;
import hu.interconnect.hr.backend.api.service.ProfileCommandService;
import hu.interconnect.hr.dao.FelhasznaloDAO;
import hu.interconnect.hr.domain.Felhasznalo;

@Service
public class ProfileCommandServiceImp implements ProfileCommandService {

	@Autowired
	private FelhasznaloDAO felhasznaloDAO;
	
	@Autowired
	private ShaPasswordEncoder encoder;

	@Override
	@PreAuthorize("authentication.principal.nev == #dto.azonosito")
	public void editProfile(ProfileEditDTO dto) {
		if (!isEmpty(dto.getJelszo())) {
			dto.setJelszo(encoder.encodePassword(dto.getJelszo(), dto.getAzonosito()));
		}
		
		Optional<Felhasznalo> optionalFelhasznalo = felhasznaloDAO.findByNev(dto.getAzonosito());
		Felhasznalo felhasznalo = optionalFelhasznalo.get();
		felhasznalo.merge(dto);
		
		Felhasznalo principal = (Felhasznalo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		principal.merge(dto);
	}
}
