package test.integration.module.scheduledtasks.jogviszonyvege;

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
import hu.interconnect.hr.module.scheduledtasks.jogviszonyvege.JogviszonyVegeIdozitettFeladat;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher.IdozitettFuttatasDTOMatcher;
import test.matcher.SimpleMailMessageMatcher;

public class JogviszonyVegeEmailIdozitettFeladatIntTest extends AbstractRestAPIIntTest {

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
																		.jogviszonyVege("2013.03.15")
																		.letrehoz())
											.letrehoz();

		personaldataRestController.create(szemelyitorzs);
	}
	
	@Test
	public void jogviszonyVege1HonapMulvaFeladatotFuttat() {
		scheduledtasksRestController.start(JogviszonyVegeIdozitettFeladat.class.getSimpleName());
		waitUntilJobsAreRunning();
		
		List<SimpleMailMessage> elkuldottLevelek = fakeMailSender.getElkuldottLevelek();
		SimpleMailMessageMatcher elvartLevel = new SimpleMailMessageMatcher()
													.to("fake@test.address")
													.cc()
													.bcc()
													.subject("Jogviszony vége")
													.text("A(z) Papp Krisztina dolgozó munkaviszonya 2013.03.15-kor le fog járni." + NEW_LINE);
		assertThat(elkuldottLevelek, contains(elvartLevel));

		IdozitettFuttatasokResponseDTO reszletek = scheduledtasksRestController.details(JogviszonyVegeIdozitettFeladat.class.getSimpleName());
		assertThat(reszletek, new IdozitettFuttatasokResponseDTOMatcher().idozitettFuttatasok(contains(asList(
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("JogviszonyVegeIdozitettFeladat")
																									.inditas("2013.02.18 17:44:55")
																									.befejezes("2013.02.18 17:44:55")
																									.eredmeny("1 személy"),
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("JogviszonyVegeIdozitettFeladat")
																									.inditas("2012.03.29 18:50:03")
																									.befejezes("2012.03.29 18:50:04")
																									.eredmeny("JogviszonyVegeIdozitettFeladat - init")))));
	}

	@Test
	public void jogviszonyVege1HonapMulvaFeladatotKilepettEsGyesGyedNemKerulRiportolasraFuttat() {
		SzemelyitorzsCreateDTO gyesGyedSzemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
													.tsz(3)
													.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																				.vezeteknev("Nagy")
																				.keresztnev("Niki")
																				.email("nagy.niki@szolgaltato.xy")
																				.letrehoz())
													.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																				.allomanymod(Allomanymod.GYESGYED)
																				.jogviszonyVege("2013.03.15")
																				.letrehoz())
													.letrehoz();

		personaldataRestController.create(gyesGyedSzemelyitorzs);

		SzemelyitorzsCreateDTO kilepettSzemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
													.tsz(4)
													.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																				.vezeteknev("Kis")
																				.keresztnev("Brigi")
																				.email("kis.brigi@szolgaltato.xy")
																				.letrehoz())
													.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																				.allomanymod(Allomanymod.KILEPETT)
																				.jogviszonyVege("2013.03.15")
																				.letrehoz())
													.letrehoz();

		personaldataRestController.create(kilepettSzemelyitorzs);

		scheduledtasksRestController.start(JogviszonyVegeIdozitettFeladat.class.getSimpleName());
		waitUntilJobsAreRunning();
		
		List<SimpleMailMessage> elkuldottLevelek = fakeMailSender.getElkuldottLevelek();
		SimpleMailMessageMatcher elvartLevel = new SimpleMailMessageMatcher()
													.to("fake@test.address")
													.cc()
													.bcc()
													.subject("Jogviszony vége")
													.text("A(z) Papp Krisztina dolgozó munkaviszonya 2013.03.15-kor le fog járni." + NEW_LINE);
		assertThat(elkuldottLevelek, contains(elvartLevel));

		IdozitettFuttatasokResponseDTO reszletek = scheduledtasksRestController.details(JogviszonyVegeIdozitettFeladat.class.getSimpleName());
		assertThat(reszletek, new IdozitettFuttatasokResponseDTOMatcher().idozitettFuttatasok(contains(asList(
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("JogviszonyVegeIdozitettFeladat")
																									.inditas("2013.02.18 17:44:55")
																									.befejezes("2013.02.18 17:44:55")
																									.eredmeny("1 személy"),
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("JogviszonyVegeIdozitettFeladat")
																									.inditas("2012.03.29 18:50:03")
																									.befejezes("2012.03.29 18:50:04")
																									.eredmeny("JogviszonyVegeIdozitettFeladat - init")))));
	}
}
