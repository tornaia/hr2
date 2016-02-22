package hu.interconnect.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import hu.interconnect.hr.domain.Felhasznalo;

public final class FelhasznaloUtils {

	public static Felhasznalo aktualisFelhasznalo() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			throw new AccessDeniedException("SecurityContext not found");
		}

		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			throw new AccessDeniedException("Authentication not found");
		}

		Object aktualisFelhasznalo = authentication.getPrincipal();
		if (aktualisFelhasznalo == null) {
			throw new AccessDeniedException("Principal not found");
		}

		if (aktualisFelhasznalo instanceof Felhasznalo) {
			return (Felhasznalo) aktualisFelhasznalo;
		} else {
			throw new AccessDeniedException("Principal is not Felhasznalo");
		}
	}

	private FelhasznaloUtils() {
	}
}
