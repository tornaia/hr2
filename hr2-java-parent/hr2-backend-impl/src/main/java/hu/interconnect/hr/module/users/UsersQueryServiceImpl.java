package hu.interconnect.hr.module.users;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.FelhasznaloResponseDTO;
import hu.interconnect.hr.backend.api.dto.FelhasznalokResponseDTO;
import hu.interconnect.hr.backend.api.dto.FelhasznalokResponseDTO.FelhasznaloDTO;
import hu.interconnect.hr.backend.api.service.UsersQueryService;
import hu.interconnect.hr.dao.FelhasznaloDAO;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class UsersQueryServiceImpl implements UsersQueryService {
	
	@Autowired
	private FelhasznaloDAO felhasznaloDAO;
	
	private static final Function<Felhasznalo, FelhasznaloDTO> FELHASZNALO_TO_FELHASZNALOK_DTO_FUNCTION = new Function<Felhasznalo, FelhasznaloDTO>() {
		@Override
		public FelhasznaloDTO apply(Felhasznalo felhasznalo) {
			SzemelyiAdatok szemelyiAdatok = felhasznalo.getSzemelyitorzs() != null ? felhasznalo.getSzemelyitorzs().getSzemelyiAdatok() : null;
			String vezeteknev = szemelyiAdatok != null ? szemelyiAdatok.getVezeteknev() : null;
			String keresztnev = szemelyiAdatok != null ? szemelyiAdatok.getKeresztnev() : null;
			return new FelhasznaloDTO(felhasznalo.getId(), felhasznalo.getNev(), felhasznalo.getSzerep(), vezeteknev, keresztnev, felhasznalo.isEnabled(), felhasznalo.getLocale());
		}
	};
	
	private static final Function<Felhasznalo, FelhasznaloResponseDTO> FELHASZNALO_TO_FELHASZNALO_DTO_FUNCTION = new Function<Felhasznalo, FelhasznaloResponseDTO>() {
		@Override
		public FelhasznaloResponseDTO apply(Felhasznalo felhasznalo) {
			Integer szemelyitorzs = felhasznalo.getSzemelyitorzs() != null ? felhasznalo.getSzemelyitorzs().getTsz() : null;
			return new FelhasznaloResponseDTO(felhasznalo.getId(), felhasznalo.getNev(), felhasznalo.getSzerep(), szemelyitorzs, felhasznalo.isEnabled(), felhasznalo.getLocale());
		}
	};
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public FelhasznalokResponseDTO list() {
		List<Felhasznalo> felhasznalok = felhasznaloDAO.findOsszesFelhasznaloFetchelve();
		return new FelhasznalokResponseDTO(felhasznalok.stream().map(FELHASZNALO_TO_FELHASZNALOK_DTO_FUNCTION).collect(Collectors.toList()));
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public FelhasznaloResponseDTO get(int id) {
		Felhasznalo felhasznalo = felhasznaloDAO.findById(id);
		return FELHASZNALO_TO_FELHASZNALO_DTO_FUNCTION.apply(felhasznalo);
	}
}