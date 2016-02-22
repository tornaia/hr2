package test.integration.module.scheduledtasks.igazolvanykepzettseglejarat;

import static hu.interconnect.util.DateUtils.tesztIdopontotBeallit;
import static hu.interconnect.util.StringUtils.NEW_LINE;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;

import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasokResponseDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.module.scheduledtasks.igazolvanykepzettseglejarat.IgazolvanyKepzettsegLejarIdozitettFeladat;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher.IdozitettFuttatasDTOMatcher;
import test.matcher.SimpleMailMessageMatcher;

public class IgazolvanyKepzettsegLejarIdozitettFeladatIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();

		tesztIdopontotBeallit("2013.02.18 17:44:55");

		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
											.tsz(2)
											.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																		.vezeteknev("Papp")
																		.keresztnev("Krisztina")
																		.szemelyiIgazolvanySzamLejarat("2013.05.01")
																		.utlevelSzamLejarat("2013.06.01")
																		.email("papp.krisztina@szolgaltato.xy")
																		.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																		.allomanymod(Allomanymod.AKTIV)
																		.letrehoz())
											.letrehoz();

		personaldataRestController.create(szemelyitorzs);
	}

	@Test
	public void igazolvanyKepzettsegLejar3HonapMulvaFeladatotFuttat() {
		scheduledtasksRestController.start(IgazolvanyKepzettsegLejarIdozitettFeladat.class.getSimpleName());
		waitUntilJobsAreRunning();
		
		List<SimpleMailMessage> elkuldottLevelek = fakeMailSender.getElkuldottLevelek();
		SimpleMailMessageMatcher elvartLevel = new SimpleMailMessageMatcher()
													.to("fake@test.address")
													.cc()
													.bcc()
													.subject("Igazolvány, képzettség lejár 3 hónap múlva - Papp Krisztina")
													.text("Kedves Kolléga!" + NEW_LINE + NEW_LINE + "A személyi igazolványod 2013.05.01-kor le fog járni." + NEW_LINE + "Kérlek intézkedj az igazolvány aktualizálásáról." + NEW_LINE + NEW_LINE + "Üdv," + NEW_LINE + "HR osztály" + NEW_LINE);
		assertThat(elkuldottLevelek, contains(elvartLevel));

		IdozitettFuttatasokResponseDTO reszletek = scheduledtasksRestController.details(IgazolvanyKepzettsegLejarIdozitettFeladat.class.getSimpleName());
		assertThat(reszletek, new IdozitettFuttatasokResponseDTOMatcher().idozitettFuttatasok(contains(asList(
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("IgazolvanyKepzettsegLejarIdozitettFeladat")
																									.inditas("2013.02.18 17:44:55")
																									.befejezes("2013.02.18 17:44:55")
																									.eredmeny("1 címzett"),
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("IgazolvanyKepzettsegLejarIdozitettFeladat")
																									.inditas("2012.03.29 18:50:01")
																									.befejezes("2012.03.29 18:50:02")
																									.eredmeny("IgazolvanyKepzettsegLejarIdozitettFeladat - init")))));
	}

	@Test
	public void igazolvanyKepzettsegLejar3HonapMulvaFeladatotKilepettEsGyesGyedNemKerulErtesitesreFuttat() {
		SzemelyitorzsCreateDTO kilepettSzemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
													.tsz(3)
													.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																				.vezeteknev("Nagy")
																				.keresztnev("Niki")
																				.szemelyiIgazolvanySzamLejarat("2013.05.01")
																				.utlevelSzamLejarat("2013.06.01")
																				.email("nagy.niki@szolgaltato.xy")
																				.letrehoz())
													.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																				.allomanymod(Allomanymod.KILEPETT)
																				.letrehoz())
													.letrehoz();

		personaldataRestController.create(kilepettSzemelyitorzs);

		SzemelyitorzsCreateDTO gyesGyesSzemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
													.tsz(4)
													.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																				.vezeteknev("Kis")
																				.keresztnev("Brigi")
																				.szemelyiIgazolvanySzamLejarat("2013.05.01")
																				.utlevelSzamLejarat("2013.06.01")
																				.email("kis.brigi@szolgaltato.xy")
																				.letrehoz())
													.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																				.allomanymod(Allomanymod.GYESGYED)
																				.letrehoz())
													.letrehoz();

		personaldataRestController.create(gyesGyesSzemelyitorzs);

		scheduledtasksRestController.start(IgazolvanyKepzettsegLejarIdozitettFeladat.class.getSimpleName());
		waitUntilJobsAreRunning();
		
		IdozitettFuttatasokResponseDTO reszletek = scheduledtasksRestController.details(IgazolvanyKepzettsegLejarIdozitettFeladat.class.getSimpleName());
		assertThat(reszletek, new IdozitettFuttatasokResponseDTOMatcher().idozitettFuttatasok(contains(asList(
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("IgazolvanyKepzettsegLejarIdozitettFeladat")
																									.inditas("2013.02.18 17:44:55")
																									.befejezes("2013.02.18 17:44:55")
																									.eredmeny("1 címzett"),
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("IgazolvanyKepzettsegLejarIdozitettFeladat")
																									.inditas("2012.03.29 18:50:01")
																									.befejezes("2012.03.29 18:50:02")
																									.eredmeny("IgazolvanyKepzettsegLejarIdozitettFeladat - init")))));

		List<SimpleMailMessage> elkuldottLevelek = fakeMailSender.getElkuldottLevelek();
		SimpleMailMessageMatcher elvartLevel = new SimpleMailMessageMatcher()
													.to("fake@test.address")
													.cc()
													.bcc()
													.subject("Igazolvány, képzettség lejár 3 hónap múlva - Papp Krisztina")
													.text("Kedves Kolléga!" + NEW_LINE + NEW_LINE + "A személyi igazolványod 2013.05.01-kor le fog járni." + NEW_LINE + "Kérlek intézkedj az igazolvány aktualizálásáról." + NEW_LINE + NEW_LINE + "Üdv," + NEW_LINE + "HR osztály" + NEW_LINE);
		assertThat(elkuldottLevelek, contains(elvartLevel));
	}
}
