package test.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

import hu.interconnect.hr.backend.api.service.UsersQueryService;

public class LoginIntTest extends AbstractBackendIntTest {

	@Autowired
	private UsersQueryService usersQueryService;
	
	@Test
	public void sikeresAdminBejelentkezes() {
		adminBejelentkezik();
		usersQueryService.list();
	}
	
	@Test(expected = BadCredentialsException.class)
	public void sikertelenBejelentkezes() {
		felhasznaloBejelentkezik("adminX", "admin");
	}
	
	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void vedettMetodusHivasa() {
		usersQueryService.list();
	}
}
