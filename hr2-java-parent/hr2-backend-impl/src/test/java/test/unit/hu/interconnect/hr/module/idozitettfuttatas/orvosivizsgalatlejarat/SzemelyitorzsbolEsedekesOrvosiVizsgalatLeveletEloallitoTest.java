package test.unit.hu.interconnect.hr.module.idozitettfuttatas.orvosivizsgalatlejarat;

import static hu.interconnect.util.StringUtils.NEW_LINE;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.interconnect.common.KuldendoLevel;
import hu.interconnect.hr.backend.api.enumeration.EmailTemplateType;
import hu.interconnect.hr.dao.EmailTemplateDAO;
import hu.interconnect.hr.domain.EmailTemplate;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat.SzemelyitorzsbolEsedekesOrvosiVizsgalatLeveletEloallito;
import test.builder.OrvosiVizsgalatBuilder;
import test.builder.SzemelyiAdatokBuilder;
import test.builder.SzemelyitorzsBuilder;
import test.integration.AbstractBackendIntTest;
import test.matcher.KuldendoLevelMatcher;

public class SzemelyitorzsbolEsedekesOrvosiVizsgalatLeveletEloallitoTest extends AbstractBackendIntTest {

	@Autowired
	private EmailTemplateDAO emailTemplateDAO;
	
	private SzemelyitorzsbolEsedekesOrvosiVizsgalatLeveletEloallito eloallito;

	@Before
	public void init() {
		EmailTemplate template = emailTemplateDAO.findByType(EmailTemplateType.MEDICAL_EXAMINATION_EXPIRES);
		eloallito = new SzemelyitorzsbolEsedekesOrvosiVizsgalatLeveletEloallito(template.getContent());
	}
	
	@Test
	public void orvosiVizsgalatEsedekesEsVanEmailCime() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder()
											.tsz(1)
											.szemelyiAdatok(new SzemelyiAdatokBuilder()
																			.vezeteknev("Papp")
																			.keresztnev("Krisztina")
																			.email("papp.krisztina@szolgaltato.xy")
																			.letrehoz())
											.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
																			.gyakorisag(12)
																			.utolsoOrvosiVizsgalatIdopontja("2012.04.11")
																			.letrehoz())
											.letrehoz();

		KuldendoLevel kapottLevel = eloallito.apply(szemelyitorzs);

		KuldendoLevelMatcher elvartLevelMatcher = new KuldendoLevelMatcher()
														.cimzettek("papp.krisztina@szolgaltato.xy")
														.targy("Orvosi vizsgálat lejár - Papp Krisztina")
														.tartalom("Kedves Kolléga!" + NEW_LINE +
																			NEW_LINE +
																			"Üzemorvosi vizsgálatod érvényessége 2013.04.11-n lejár. Időpont egyeztetésért keresd Doktor Bubót a 06201234567-os számon." + NEW_LINE +
																			"A vizsgálatra az Super Medical rendelőjében fog sor kerülni. Az időpont előtt 2 órával már ne egyél, viszont igyál sokat. A vizsgálat alatt sor kerül vércukor- és koleszterinszint mérésre, valamint lehetőséged van szemészeti ellenőrzést is kérni (ezt kérlek külön jelezd az időpont egyeztetésnél)." + NEW_LINE +
																			"Fizikai dolgozóknál légzésfunkció vizsgálatot is végeznek." + NEW_LINE +
																			NEW_LINE +
																			"A vizsgálat elvégzéséhez szükséged van egy \"beutalóra\", amit a HR kordinátortól tudsz elkérni." + NEW_LINE +
																			"Kérlek, hogy a kiállított alkalmassági igazolásodat juttasd el hozzánk." + NEW_LINE +
																			NEW_LINE +
																			"Köszönettel," + NEW_LINE +
																			"HR kordinátor" + NEW_LINE);


		assertThat(kapottLevel, elvartLevelMatcher);
	}

	@Test
	public void orvosiVizsgalatEsedekesEsNincsEmailCime() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder()
											.tsz(1)
											.szemelyiAdatok(new SzemelyiAdatokBuilder()
																			.vezeteknev("Papp")
																			.keresztnev("Krisztina")
																			.letrehoz())
											.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
																			.gyakorisag(12)
																			.utolsoOrvosiVizsgalatIdopontja("2012.04.11")
																			.letrehoz())
											.letrehoz();

		KuldendoLevel kapottLevel = eloallito.apply(szemelyitorzs);

		KuldendoLevelMatcher elvartLevelMatcher = new KuldendoLevelMatcher()
														.cimzettek()
														.targy("Orvosi vizsgálat lejár - Papp Krisztina")
														.tartalom("Kedves Kolléga!" + NEW_LINE +
																			NEW_LINE +
																			"Üzemorvosi vizsgálatod érvényessége 2013.04.11-n lejár. Időpont egyeztetésért keresd Doktor Bubót a 06201234567-os számon." + NEW_LINE +
																			"A vizsgálatra az Super Medical rendelőjében fog sor kerülni. Az időpont előtt 2 órával már ne egyél, viszont igyál sokat. A vizsgálat alatt sor kerül vércukor- és koleszterinszint mérésre, valamint lehetőséged van szemészeti ellenőrzést is kérni (ezt kérlek külön jelezd az időpont egyeztetésnél)." + NEW_LINE +
																			"Fizikai dolgozóknál légzésfunkció vizsgálatot is végeznek." + NEW_LINE +
																			NEW_LINE +
																			"A vizsgálat elvégzéséhez szükséged van egy \"beutalóra\", amit a HR kordinátortól tudsz elkérni." + NEW_LINE +
																			"Kérlek, hogy a kiállított alkalmassági igazolásodat juttasd el hozzánk." + NEW_LINE +
																			NEW_LINE +
																			"Köszönettel," + NEW_LINE +
																			"HR kordinátor" + NEW_LINE +
																			"A személyitörzsnek NINCS email címe!" + NEW_LINE);


		assertThat(kapottLevel, elvartLevelMatcher);
	}
}
