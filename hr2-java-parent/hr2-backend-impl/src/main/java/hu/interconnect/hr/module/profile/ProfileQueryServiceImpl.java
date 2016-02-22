package hu.interconnect.hr.module.profile;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.ProfileResponseDTO;
import hu.interconnect.hr.backend.api.service.ProfileQueryService;
import hu.interconnect.hr.dao.FelhasznaloDAO;
import hu.interconnect.hr.domain.Felhasznalo;

@Component
public class ProfileQueryServiceImpl implements ProfileQueryService {

	private static final Function<Felhasznalo, ProfileResponseDTO> FELHASZNALO_TO_PROFILE_RESPONSE_DTO_FUNCTION = new Function<Felhasznalo, ProfileResponseDTO>() {
		@Override
		public ProfileResponseDTO apply(Felhasznalo felhasznalo) {
			String sztVezeteknev = felhasznalo.getSzemelyitorzs() != null ? felhasznalo.getSzemelyitorzs().getSzemelyiAdatok().getVezeteknev() : null;
			String sztKeresztnev = felhasznalo.getSzemelyitorzs() != null ? felhasznalo.getSzemelyitorzs().getSzemelyiAdatok().getKeresztnev() : null;
			return new ProfileResponseDTO(felhasznalo.getNev(), felhasznalo.getSzerep(), sztVezeteknev, sztKeresztnev, felhasznalo.getLocale());
		}
	};
	
	@Autowired
	private FelhasznaloDAO felhasznaloDAO;
	
	@Override
	@PreAuthorize("authentication.principal.nev == #name")
	public ProfileResponseDTO getByName(String name) {
		Optional<Felhasznalo> optionalFelhasznalo = felhasznaloDAO.findByNev(name);
		Felhasznalo felhasznalo = optionalFelhasznalo.get();
		return FELHASZNALO_TO_PROFILE_RESPONSE_DTO_FUNCTION.apply(felhasznalo);
	}

}
