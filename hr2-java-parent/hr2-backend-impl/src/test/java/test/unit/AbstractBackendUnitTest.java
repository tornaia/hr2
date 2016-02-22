package test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;
import hu.interconnect.hr.domain.Felhasznalo;
import test.builder.SzemelyitorzsBuilder;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractBackendUnitTest extends AbstractUnitTest {

	protected static final Felhasznalo ADMINISTRATOR = new Felhasznalo("admin", Szerep.ADMINISTRATOR, "admin_jelszo", null, true, Locale.hu_HU);

	protected static final Felhasznalo BETEKINTO = new Felhasznalo("betekinto", Szerep.BETEKINTO, "betekinto_jelszo", null, true, Locale.hu_HU);
	
	protected static final Felhasznalo BETEKINTO_EN_US = new Felhasznalo("betekinto_en_us", Szerep.BETEKINTO, "betekinto_en_us_jelszo", null, true, Locale.en_US);

	protected static final Felhasznalo DOLGOZO = new Felhasznalo("dolgozo", Szerep.DOLGOZO, "dolgozo", new SzemelyitorzsBuilder().tsz(1).letrehoz(), true, Locale.hu_HU);

	protected void adminBejelentkezik() {
		SecurityContextHolder.getContext().setAuthentication(new RememberMeAuthenticationToken("admin", ADMINISTRATOR, null));
	}

	protected void betekintoBejelentkezik() {
		SecurityContextHolder.getContext().setAuthentication(new RememberMeAuthenticationToken("betekinto", BETEKINTO, null));
	}
	
	protected void betekintoEnUsBejelentkezik() {
		SecurityContextHolder.getContext().setAuthentication(new RememberMeAuthenticationToken("betekinto_en_us", BETEKINTO_EN_US, null));
	}

	protected void dolgozoBejelentkezik() {
		SecurityContextHolder.getContext().setAuthentication(new RememberMeAuthenticationToken("dolgozo", DOLGOZO, null));
	}

	protected void kijelentkezik() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@Before
	@After
	public final void mindigKijelentkezik() {
		kijelentkezik();
	}
}