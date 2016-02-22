package test.integration;

import org.junit.Test;
import org.springframework.security.authentication.BadCredentialsException;

public class AdminRestLoginIntTest extends AbstractRestAPIIntTest {

	@Test
	public void sikeresAdminBejelentkezes() {
		felhasznaloBejelentkezik("admin", "admin");
	}
	
	@Test(expected=BadCredentialsException.class)
	public void sikertelenBejelentkezes() {
		felhasznaloBejelentkezik("adminX", "admin");
	}
}
