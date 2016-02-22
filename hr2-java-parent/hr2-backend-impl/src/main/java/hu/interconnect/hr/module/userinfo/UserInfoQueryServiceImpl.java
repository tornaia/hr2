package hu.interconnect.hr.module.userinfo;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.UserInfoResponseDTO;
import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.service.UserInfoQueryService;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.util.FelhasznaloUtils;

@Component
public class UserInfoQueryServiceImpl implements UserInfoQueryService {

	@Override
	public UserInfoResponseDTO getUserInfo() {
		Felhasznalo aktualisFelhasznalo = FelhasznaloUtils.aktualisFelhasznalo();
		String username = aktualisFelhasznalo.getUsername();
		Locale locale = aktualisFelhasznalo.getLocale();
		Set<String> grantedAuthorities = aktualisFelhasznalo.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet());
		return new UserInfoResponseDTO(username, locale, grantedAuthorities);
	}
}
