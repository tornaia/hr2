package test.integration.module.scheduledtasks.probaidolejarat;

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
import hu.interconnect.hr.module.scheduledtasks.probaidolejarat.ProbaidoLejarIdozitettFeladat;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher.IdozitettFuttatasDTOMatcher;
import test.matcher.SimpleMailMessageMatcher;

public class ProbaidoLejarEmailIdozitettFeladatIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();

		tesztIdopontotBeallit("2013.02.18 17:44:55");

		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
											.tsz(2)
											.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																		.vezeteknev("Papp")
																		.keresztnev("Krisztina")
																		.email("papp.krisztina@szolgaltato.xy")
																		.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																		.allomanymod(Allomanymod.AKTIV)
																		.probaidoVege("2013.03.04")
																		.letrehoz())
											.letrehoz();

		personaldataRestController.create(szemelyitorzs);
	}

	@Test
	public void probaidoLejar14NapMulvaFeladatotFuttat() {
		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
											.tsz(3)
											.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																		.vezeteknev("Kis")
																		.keresztnev("Niki")
																		.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																		.allomanymod(Allomanymod.AKTIV)
																		.probaidoVege("2013.03.04")
																		.letrehoz())
											.letrehoz();

		personaldataRestController.create(szemelyitorzs);

		
		scheduledtasksRestController.start(ProbaidoLejarIdozitettFeladat.class.getSimpleName());
		waitUntilJobsAreRunning();

		List<SimpleMailMessage> elkuldottLevelek = fakeMailSender.getElkuldottLevelek();
		SimpleMailMessageMatcher elvartLevel = new SimpleMailMessageMatcher()
													.to("fake@test.address")
													.cc()
													.bcc()
													.subject("Próbaidő vége")
													.text("A(z) Papp Krisztina dolgozó próbaideje 2013.03.04-kor le fog járni." + NEW_LINE + "A(z) Kis Niki dolgozó próbaideje 2013.03.04-kor le fog járni." + NEW_LINE);
		assertThat(elkuldottLevelek, contains(elvartLevel));

		IdozitettFuttatasokResponseDTO reszletek = scheduledtasksRestController.details(ProbaidoLejarIdozitettFeladat.class.getSimpleName());
		assertThat(reszletek, new IdozitettFuttatasokResponseDTOMatcher().idozitettFuttatasok(contains(asList(
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("ProbaidoLejarIdozitettFeladat")
																									.inditas("2013.02.18 17:44:55")
																									.befejezes("2013.02.18 17:44:55")
																									.eredmeny("2 címzett"),
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("ProbaidoLejarIdozitettFeladat")
																									.inditas("2012.03.29 18:50:09")
																									.befejezes("2012.03.29 18:50:10")
																									.eredmeny("ProbaidoLejarIdozitettFeladat - init")))));
	}

	@Test
	public void probaidoLejar14NapMulvaFeladatotKilepettEsGyesGyedNemKerulErtesitesreFuttat() {
		SzemelyitorzsCreateDTO gyesGyedSzemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
											.tsz(3)
											.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																		.vezeteknev("Nagy")
																		.keresztnev("Brigitta")
																		.email("nagy.brigitta@szolgaltato.xy")
																		.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																		.allomanymod(Allomanymod.GYESGYED)
																		.probaidoVege("2013.03.04")
																		.letrehoz())
											.letrehoz();

		personaldataRestController.create(gyesGyedSzemelyitorzs);

		SzemelyitorzsCreateDTO kilepettSzemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
													.tsz(4)
													.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																				.vezeteknev("Kis")
																				.keresztnev("Niki")
																				.email("kis.niki@szolgaltato.xy")
																				.letrehoz())
													.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																				.allomanymod(Allomanymod.KILEPETT)
																				.probaidoVege("2013.03.04")
																				.letrehoz())
													.letrehoz();

		personaldataRestController.create(kilepettSzemelyitorzs);

		scheduledtasksRestController.start(ProbaidoLejarIdozitettFeladat.class.getSimpleName());
		waitUntilJobsAreRunning();
		
		List<SimpleMailMessage> elkuldottLevelek = fakeMailSender.getElkuldottLevelek();
		SimpleMailMessageMatcher elvartLevel = new SimpleMailMessageMatcher()
													.to("fake@test.address")
													.cc()
													.bcc()
													.subject("Próbaidő vége")
													.text("A(z) Papp Krisztina dolgozó próbaideje 2013.03.04-kor le fog járni." + NEW_LINE);
		assertThat(elkuldottLevelek, contains(elvartLevel));

		IdozitettFuttatasokResponseDTO reszletek = scheduledtasksRestController.details(ProbaidoLejarIdozitettFeladat.class.getSimpleName());
		assertThat(reszletek, new IdozitettFuttatasokResponseDTOMatcher().idozitettFuttatasok(contains(asList(
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("ProbaidoLejarIdozitettFeladat")
																									.inditas("2013.02.18 17:44:55")
																									.befejezes("2013.02.18 17:44:55")
																									.eredmeny("1 címzett"),
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("ProbaidoLejarIdozitettFeladat")
																									.inditas("2012.03.29 18:50:09")
																									.befejezes("2012.03.29 18:50:10")
																									.eredmeny("ProbaidoLejarIdozitettFeladat - init")))));
	}
}
