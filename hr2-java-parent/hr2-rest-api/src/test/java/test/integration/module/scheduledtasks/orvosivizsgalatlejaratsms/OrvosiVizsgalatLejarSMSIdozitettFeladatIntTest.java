package test.integration.module.scheduledtasks.orvosivizsgalatlejaratsms;

import static hu.interconnect.util.DateUtils.tesztIdopontotBeallit;
import static hu.interconnect.util.StringUtils.NEW_LINE;
import static java.util.Arrays.asList;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

import hu.interconnect.hr.backend.api.dto.IdozitettFuttatasokResponseDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat.OrvosiVizsgalatLejarSMSIdozitettFeladat;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.OrvosiVizsgalatDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher;
import test.matcher.IdozitettFuttatasokResponseDTOMatcher.IdozitettFuttatasDTOMatcher;

public class OrvosiVizsgalatLejarSMSIdozitettFeladatIntTest extends AbstractRestAPIIntTest {

	private File tesztKonyvtar;

	@Before
	public void adatokkalFeltolt() throws FtpException {
		adminBejelentkezik();
		tesztKonyvtar = Files.createTempDir();
		
		tesztIdopontotBeallit("2013.02.18 17:44:55");

		SzemelyitorzsCreateDTO pappKrisztina = new SzemelyitorzsCreateDTOBuilder()
											.tsz(2)
											.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																		.vezeteknev("Papp")
																		.keresztnev("Krisztina")
																		.mobil("205566999")
																		.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																		.allomanymod(Allomanymod.AKTIV)
																		.letrehoz())
											.orvosiVizsgalat(new OrvosiVizsgalatDTOBuilder()
																		.gyakorisag(12)
																		.utolsoOrvosiVizsgalatIdopontja("2012.03.19")
																		.letrehoz())
											.letrehoz();

		personaldataRestController.create(pappKrisztina);

		SzemelyitorzsCreateDTO nagyNikolett = new SzemelyitorzsCreateDTOBuilder()
											.tsz(3)
											.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																		.vezeteknev("Nagy")
																		.keresztnev("Nikolett")
																		.mobil("708885511")
																		.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																		.allomanymod(Allomanymod.AKTIV)
																		.letrehoz())
											.orvosiVizsgalat(new OrvosiVizsgalatDTOBuilder()
																		.gyakorisag(12)
																		.utolsoOrvosiVizsgalatIdopontja("2012.03.30")
																		.letrehoz())
											.letrehoz();

		personaldataRestController.create(nagyNikolett);

		beagyazottFTPServertFello();
	}

	private void beagyazottFTPServertFello() throws FtpException {
		FtpServerFactory serverFactory = new FtpServerFactory();
		UserManager userManager = serverFactory.getUserManager();

		BaseUser felhasznalo = new BaseUser();
		felhasznalo.setEnabled(true);
		felhasznalo.setHomeDirectory(tesztKonyvtar.getAbsolutePath());
		felhasznalo.setName("felhasznalonev");
		felhasznalo.setPassword("jelszo");
		List<Authority> authorities = Lists.newArrayList();
		authorities.add(new WritePermission());
		felhasznalo.setAuthorities(authorities);

		userManager.save(felhasznalo);

		ListenerFactory factory = new ListenerFactory();
		// set the port of the listener
		factory.setPort(2221);
		// replace the default listener
		serverFactory.addListener("default", factory.createListener());
		// start the server
		FtpServer server = serverFactory.createServer();
		server.start();
	}

	@Test
	public void orvosiVizsgalatLejar1HonapMulvaSMSFeladatotFuttat() throws IOException {
		scheduledtasksRestController.start(OrvosiVizsgalatLejarSMSIdozitettFeladat.class.getSimpleName());
		waitUntilJobsAreRunning();
		
		int allomanyokSzamaAKimenetiKonyvtarban = tesztKonyvtar.list().length;
		assertEquals(2, allomanyokSzamaAKimenetiKonyvtarban);

		File pappKrisztinaSMSAllomany = new File(tesztKonyvtar, "20130218174455_000000");
		assertTrue(pappKrisztinaSMSAllomany.exists());
		String pappKrisztinaKapottFileTartalom = readFileToString(pappKrisztinaSMSAllomany);
		String pappKrisztinaElvartFileTartalom = "To: 205566999" + NEW_LINE + NEW_LINE + "Kedves Kolléga! Az üzemorvosi vizsgálatod 2013.03.19-kor le fog járni. Időpont egyeztetésért keresd Gubicza Máriát a 06202519346-os számon. Üdv, HR osztály";
		assertEquals(pappKrisztinaElvartFileTartalom, pappKrisztinaKapottFileTartalom);

		File nagyNikolettSMSAllomany = new File(tesztKonyvtar, "20130218174455_000001");
		assertTrue(nagyNikolettSMSAllomany.exists());
		String nagyNikolettKapottFileTartalom = readFileToString(nagyNikolettSMSAllomany);
		String nagyNikolettElvartFileTartalom = "To: 708885511" + NEW_LINE + NEW_LINE + "Kedves Kolléga! Az üzemorvosi vizsgálatod 2013.03.30-kor le fog járni. Időpont egyeztetésért keresd Gubicza Máriát a 06202519346-os számon. Üdv, HR osztály";
		assertEquals(nagyNikolettElvartFileTartalom, nagyNikolettKapottFileTartalom);


		IdozitettFuttatasokResponseDTO reszletek = scheduledtasksRestController.details(OrvosiVizsgalatLejarSMSIdozitettFeladat.class.getSimpleName());
		assertThat(reszletek, new IdozitettFuttatasokResponseDTOMatcher().idozitettFuttatasok(contains(asList(
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("OrvosiVizsgalatLejarSMSIdozitettFeladat")
																									.inditas("2013.02.18 17:44:55")
																									.befejezes("2013.02.18 17:44:55")
																									.eredmeny("Tartalom: KuldendoSMSAllomanyok (2) {KuldendoSMSAllomany, filenev: 20130218174455_000000, tartalom: To: 205566999" + NEW_LINE + NEW_LINE + "Kedves Kolléga! Az üzemorvosi vizsgálatod 2013.03.19-kor le fog járni. Időpont egyeztetésért keresd Gubicza Máriát a 06202519346-os számon. Üdv, HR osztály; KuldendoSMSAllomany, filenev: 20130218174455_000001, tartalom: To: 708885511" + NEW_LINE + NEW_LINE + "Kedves Kolléga! Az üzemorvosi vizsgálatod 2013.03.30-kor le fog járni. Időpont egyeztetésért keresd Gubicza Máriát a 06202519346-os számon. Üdv, HR osztály}"),
																								new IdozitettFuttatasDTOMatcher()
																									.azonosito("OrvosiVizsgalatLejarSMSIdozitettFeladat")
																									.inditas("2012.03.29 18:50:07")
																									.befejezes("2012.03.29 18:50:08")
																									.eredmeny("OrvosiVizsgalatLejarSMSIdozitettFeladat - init")))));
	}
}