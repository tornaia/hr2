package hu.interconnect.hr.backend.api.dto;

import java.util.Set;

import hu.interconnect.hr.backend.api.enumeration.Locale;

public final class UserInfoResponseDTO {

	public String username;
	public Locale locale;
	public Set<String> grantedAuthorities;

	public UserInfoResponseDTO(String username, Locale locale, Set<String> grantedAuthorities) {
		this.username = username;
		this.locale = locale;
		this.grantedAuthorities = grantedAuthorities;
	}
}
