package test.integration.module.scheduledtasks.orvosivizsgalatlejaratemail;

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
import hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat.OrvosiVizsgalatLejarEmailIdozitettFeladat;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.OrvosiVizsgalatDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher.IdozitettFuttatasDTOMatcher;
import test.matcher.SimpleMailMessageMatcher;

public class OrvosiVizsgalatLejarEmailIdozitettFeladatIntTest extends AbstractRestAPIIntTest {

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
																		.letrehoz())
											.orvosiVizsgalat(new OrvosiVizsgalatDTOBuilder()
																		.gyakorisag(12)
																		.utolsoOrvosiVizsgalatIdopontja("2012.03.19")
																		.letrehoz())
											.letrehoz();

		personaldataRestController.create(szemelyitorzs);
	}

	@Test
	public void orvosiVizsgalatLejar1HonapMulvaFeladatotFuttat() {
		scheduledtasksRestController.start(OrvosiVizsgalatLejarEmailIdozitettFeladat.class.getSimpleName());
		waitUntilJobsAreRunning();
		
		List<SimpleMailMessage> elkuldottLevelek = fakeMailSender.getElkuldottLevelek();

		SimpleMailMessageMatcher elvartLevel = new SimpleMailMessageMatcher()
													.to("fake@test.address")
													.cc()
													.bcc()
													.subject("Orvosi vizsgálat lejár - Papp Krisztina")
													.text("Kedves Kolléga!" + NEW_LINE +
																	NEW_LINE +
																	"Üzemorvosi vizsgálatod érvényessége 2013.03.19-n lejár. Időpont egyeztetésért keresd Doktor Bubót a 06201234567-os számon." + NEW_LINE +
																	"A vizsgálatra az Super Medical rendelőjében fog sor kerülni. Az időpont előtt 2 órával már ne egyél, viszont igyál sokat. A vizsgálat alatt sor kerül vércukor- és koleszterinszint mérésre, valamint lehetőséged van szemészeti ellenőrzést is kérni (ezt kérlek külön jelezd az időpont egyeztetésnél)." + NEW_LINE +
																	"Fizikai dolgozóknál légzésfunkció vizsgálatot is végeznek." + NEW_LINE +
																	NEW_LINE +
																	"A vizsgálat elvégzéséhez szükséged van egy \"beutalóra\", amit a HR kordinátortól tudsz elkérni." + NEW_LINE +
																	"Kérlek, hogy a kiállított alkalmassági igazolásodat juttasd el hozzánk." + NEW_LINE +
																	NEW_LINE +
																	"Köszönettel," + NEW_LINE +
																	"HR kordinátor" + NEW_LINE);
		assertThat(elkuldottLevelek, contains(elvartLevel));

		IdozitettFuttatasokResponseDTO reszletek = scheduledtasksRestController.details(OrvosiVizsgalatLejarEmailIdozitettFeladat.class.getSimpleName());
		assertThat(reszletek, new IdozitettFuttatasokResponseDTOMatcher().idozitettFuttatasok(contains(asList(
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("OrvosiVizsgalatLejarEmailIdozitettFeladat")
																									.inditas("2013.02.18 17:44:55")
																									.befejezes("2013.02.18 17:44:55")
																									.eredmeny("1 címzett"),
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("OrvosiVizsgalatLejarEmailIdozitettFeladat")
																									.inditas("2012.03.29 18:50:05")
																									.befejezes("2012.03.29 18:50:06")
																									.eredmeny("OrvosiVizsgalatLejarEmailIdozitettFeladat - init")))));
	}
}