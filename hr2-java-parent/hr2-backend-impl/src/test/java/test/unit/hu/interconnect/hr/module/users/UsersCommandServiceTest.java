package test.unit.hu.interconnect.hr.module.users;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import hu.interconnect.hr.backend.api.dto.FelhasznaloEditDTO;
import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;
import hu.interconnect.hr.dao.FelhasznaloDAO;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.users.UsersCommandServiceImpl;
import test.builder.FelhasznaloBuilder;
import test.builder.FelhasznaloEditDTOBuilder;
import test.matcher.FelhasznaloMatcher;
import test.unit.AbstractBackendUnitTest;

public class UsersCommandServiceTest extends AbstractBackendUnitTest {

	@Mock
	private FelhasznaloDAO felhasznaloDAO;

	@Mock
	private ShaPasswordEncoder encoder;

	@InjectMocks
	private UsersCommandServiceImpl usersCommandService;

	@Test
	public void nullJelszovalModositNemValtoztatAJelszonTest() {
		Felhasznalo felhasznalo = new FelhasznaloBuilder()
													.nev("admin")
													.jelszo("jelszo")
													.szerep(Szerep.ADMINISTRATOR)
													.szemelyitorzs(null)
													.enabled(true)
													.locale(Locale.hu_HU)
													.letrehoz();

		when(felhasznaloDAO.loadUserByUsername("admin")).thenReturn(felhasznalo);

		FelhasznaloEditDTO dto = new FelhasznaloEditDTOBuilder()
										.nev("admin")
										.jelszo(null)
										.szerep(Szerep.BETEKINTO)
										.enabled(false)
										.locale(Locale.hu_HU)
										.letrehoz();

		usersCommandService.edit(dto);

		FelhasznaloMatcher elvartFelhasznalo = new FelhasznaloMatcher()
														.adottNevvel("admin")
														.adottJelszoval("jelszo")
														.adottSzereppel(Szerep.BETEKINTO)
														.adottSzemelyitorzzsel(CoreMatchers.nullValue(Szemelyitorzs.class))
														.adottEnableddel(false);

		assertThat(felhasznalo, elvartFelhasznalo);

		verifyZeroInteractions(encoder);
	}

	@Test
	public void uresJelszovalModositNemValtoztatAJelszonTest() {
		Felhasznalo perzisztaltFelhasznalo = new FelhasznaloBuilder()
													.nev("admin")
													.jelszo("jelszo")
													.szerep(Szerep.ADMINISTRATOR)
													.szemelyitorzs(null)
													.enabled(true)
													.locale(Locale.hu_HU)
													.letrehoz();

		when(felhasznaloDAO.loadUserByUsername("admin")).thenReturn(perzisztaltFelhasznalo);

		FelhasznaloEditDTO dto = new FelhasznaloEditDTOBuilder()
										.nev("admin")
										.jelszo("")
										.szerep(Szerep.BETEKINTO)
										.enabled(false)
										.locale(Locale.hu_HU)
										.letrehoz();

		usersCommandService.edit(dto);

		FelhasznaloMatcher elvartFelhasznalo = new FelhasznaloMatcher()
														.adottNevvel("admin")
														.adottJelszoval("jelszo")
														.adottSzereppel(Szerep.BETEKINTO)
														.adottSzemelyitorzzsel(CoreMatchers.nullValue(Szemelyitorzs.class))
														.adottEnableddel(false);

		assertThat(perzisztaltFelhasznalo, elvartFelhasznalo);

		verifyZeroInteractions(encoder);
	}

	@Test
	public void ujJelszovalModositNemValtoztatAJelszonTest() {
		Felhasznalo perzisztaltFelhasznalo = new FelhasznaloBuilder()
													.nev("admin")
													.jelszo("jelszo")
													.szerep(Szerep.ADMINISTRATOR)
													.szemelyitorzs(null)
													.enabled(true)
													.locale(Locale.hu_HU)
													.letrehoz();

		when(felhasznaloDAO.loadUserByUsername("admin")).thenReturn(perzisztaltFelhasznalo);

		when(encoder.encodePassword("ujJelszo", "admin")).thenReturn("kodoltJelszo");

		FelhasznaloEditDTO dto = new FelhasznaloEditDTOBuilder()
										.nev("admin")
										.jelszo("ujJelszo")
										.szerep(Szerep.BETEKINTO)
										.enabled(false)
										.locale(Locale.hu_HU)
										.letrehoz();

		usersCommandService.edit(dto);

		FelhasznaloMatcher elvartFelhasznalo = new FelhasznaloMatcher()
														.adottNevvel("admin")
														.adottJelszoval("kodoltJelszo")
														.adottSzereppel(Szerep.BETEKINTO)
														.adottSzemelyitorzzsel(CoreMatchers.nullValue(Szemelyitorzs.class))
														.adottEnableddel(false);

		assertThat(perzisztaltFelhasznalo, elvartFelhasznalo);

		verify(encoder).encodePassword("ujJelszo", "admin");
		verifyNoMoreInteractions(encoder);
	}
}
