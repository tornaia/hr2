package test.unit.hu.interconnect.hr.module.idozitettfuttatas.igazolvanykepzettseglejarat;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newLinkedHashSet;
import static hu.interconnect.util.DateUtils.parseHonap;
import static hu.interconnect.util.StringUtils.NEW_LINE;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.interconnect.common.KuldendoLevel;
import hu.interconnect.hr.backend.api.enumeration.EmailTemplateType;
import hu.interconnect.hr.dao.EmailTemplateDAO;
import hu.interconnect.hr.domain.EmailTemplate;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.scheduledtasks.igazolvanykepzettseglejarat.SzemelyitorzsbolIgazolvanyKepzettsegLejarLeveletEloallito;
import test.builder.KepzettsegBuilder;
import test.builder.SzemelyiAdatokBuilder;
import test.integration.AbstractBackendIntTest;
import test.matcher.KuldendoLevelMatcher;

public class SzemelyitorzsbolIgazolvanyKepzettsegLejarLeveletEloallitoTest extends AbstractBackendIntTest {

	@Autowired
	private EmailTemplateDAO emailTemplateDAO;
	
	private SzemelyitorzsbolIgazolvanyKepzettsegLejarLeveletEloallito eloallito;
	
	@Before
	public void initke() {
		EmailTemplate template = emailTemplateDAO.findByType(EmailTemplateType.IDENTIFICATION_QUALIFICATION_EXPIRES);
		eloallito = new SzemelyitorzsbolIgazolvanyKepzettsegLejarLeveletEloallito(template.getContent(), parseHonap("2013.03"), parseHonap("2013.04"));
	}
	
	@Test
	public void mindenLejarEsVanEmailCime() {
		Szemelyitorzs szemelyitorzs = mock(Szemelyitorzs.class);
		
		when(szemelyitorzs.getTeljesNev()).thenReturn("Papp Krisztina");
		when(szemelyitorzs.getSzemelyiAdatok()).thenReturn(new SzemelyiAdatokBuilder()
														.vezeteknev("Papp")
														.keresztnev("Krisztina")
														.szemelyiIgazolvanySzamLejarat("2013.03.31")
														.utlevelSzamLejarat("2013.03.15")
														.jogositvanySzamLejarat("2013.03.01")
														.email("papp.krisztina@szolgaltato.xy")
														.letrehoz());
		
		when(szemelyitorzs.getKepzettsegek()).thenReturn(newLinkedHashSet(newArrayList(
															new KepzettsegBuilder()
																.megnevezes("Minőségellenőri vizsga")
																.ervenyessegVege("2013.03.02")
																.letrehoz(),
															new KepzettsegBuilder()
																.megnevezes("Targoncakezelő vizsga")
																.ervenyessegVege("2013.03.10")
																.letrehoz())));
	
		KuldendoLevel kapottLevel = eloallito.apply(szemelyitorzs);
		
		KuldendoLevelMatcher elvartLevelMatcher = new KuldendoLevelMatcher()
														.cimzettek("papp.krisztina@szolgaltato.xy")
														.targy("Igazolvány, képzettség lejár 3 hónap múlva - Papp Krisztina")
														.tartalom("Kedves Kolléga!" + NEW_LINE +
														                  NEW_LINE +
														                  "A személyi igazolványod 2013.03.31-kor le fog járni." + NEW_LINE +
														                  "Az útleveled 2013.03.15-kor le fog járni." + NEW_LINE +
														                  "A jogosítványod 2013.03.01-kor le fog járni." + NEW_LINE +
														                  "A(z) Minőségellenőri vizsga képzettséged 2013.03.02-kor le fog járni." + NEW_LINE +
														                  "A(z) Targoncakezelő vizsga képzettséged 2013.03.10-kor le fog járni." + NEW_LINE +
														                  "Kérlek intézkedj az igazolvány aktualizálásáról." + NEW_LINE +
														                  NEW_LINE +
														                  "Üdv," + NEW_LINE +
														                  "HR osztály" + NEW_LINE);
														
		
		assertThat(kapottLevel, elvartLevelMatcher);
	}
	
	@Test
	public void mindenLejarEsNincsEmailCime() {
		Szemelyitorzs szemelyitorzs = mock(Szemelyitorzs.class);
		
		when(szemelyitorzs.getTeljesNev()).thenReturn("Papp Krisztina");
		when(szemelyitorzs.getSzemelyiAdatok()).thenReturn(new SzemelyiAdatokBuilder()
														.vezeteknev("Papp")
														.keresztnev("Krisztina")
														.szemelyiIgazolvanySzamLejarat("2013.03.31")
														.utlevelSzamLejarat("2013.03.15")
														.jogositvanySzamLejarat("2013.03.01")
														.letrehoz());
		
		when(szemelyitorzs.getKepzettsegek()).thenReturn(newLinkedHashSet(newArrayList(
															new KepzettsegBuilder()
																.megnevezes("Minőségellenőri vizsga")
																.ervenyessegVege("2013.03.02")
																.letrehoz(),
															new KepzettsegBuilder()
																.megnevezes("Targoncakezelő vizsga")
																.ervenyessegVege("2013.03.10")
																.letrehoz())));
	
		KuldendoLevel kapottLevel = eloallito.apply(szemelyitorzs);
		
		KuldendoLevelMatcher elvartLevelMatcher = new KuldendoLevelMatcher()
														.cimzettek()
														.targy("Igazolvány, képzettség lejár 3 hónap múlva - Papp Krisztina")
														.tartalom("Kedves Kolléga!" + NEW_LINE +
														                  NEW_LINE +
														                  "A személyi igazolványod 2013.03.31-kor le fog járni." + NEW_LINE +
														                  "Az útleveled 2013.03.15-kor le fog járni." + NEW_LINE +
														                  "A jogosítványod 2013.03.01-kor le fog járni." + NEW_LINE +
														                  "A(z) Minőségellenőri vizsga képzettséged 2013.03.02-kor le fog járni." + NEW_LINE +
														                  "A(z) Targoncakezelő vizsga képzettséged 2013.03.10-kor le fog járni." + NEW_LINE +
														                  "Kérlek intézkedj az igazolvány aktualizálásáról." + NEW_LINE +
														                  NEW_LINE +
														                  "Üdv," + NEW_LINE +
														                  "HR osztály" + NEW_LINE +
														                  "A személyitörzsnek NINCS email címe!" + NEW_LINE);
														
		
		assertThat(kapottLevel, elvartLevelMatcher);
	}
}
