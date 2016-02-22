package test.unit.hu.interconnect.hr.module.idozitettfuttatas;

import static com.google.common.collect.Lists.newArrayList;
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
import hu.interconnect.hr.module.scheduledtasks.probaidolejarat.SzemelyitorzsbolProbaidoVegeLeveletEloallito;
import test.builder.JogviszonyAdatokBuilder;
import test.builder.SzemelyiAdatokBuilder;
import test.builder.SzemelyitorzsBuilder;
import test.integration.AbstractBackendIntTest;
import test.matcher.KuldendoLevelMatcher;

public class SzemelyitorzsbolProbaidoVegeLeveletEloallitoTest extends AbstractBackendIntTest {

	@Autowired
	private EmailTemplateDAO emailTemplateDAO;
	
	private SzemelyitorzsbolProbaidoVegeLeveletEloallito eloallito;
	
	@Before
	public void init() {
		EmailTemplate template = emailTemplateDAO.findByType(EmailTemplateType.PROBATIONARY_PERIOD_EXPIRES);
		eloallito = new SzemelyitorzsbolProbaidoVegeLeveletEloallito(template.getContent());
	}
	
	@Test
	public void ketSzemelyitorzsProbaidejeLejarTest() {
		Szemelyitorzs pappKrisztina = new SzemelyitorzsBuilder()
											.tsz(1)
											.szemelyiAdatok(new SzemelyiAdatokBuilder()
																			.vezeteknev("Papp")
																			.keresztnev("Krisztina")
																			.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokBuilder()
																			.probaidoVege("2013.03.22")
																			.letrehoz())
											.letrehoz();

		Szemelyitorzs kisNiki = new SzemelyitorzsBuilder()
											.tsz(1)
											.szemelyiAdatok(new SzemelyiAdatokBuilder()
																			.vezeteknev("Kis")
																			.keresztnev("Niki")
																			.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokBuilder()
																			.probaidoVege("2013.03.23")
																			.letrehoz())
											.letrehoz();

		KuldendoLevel kapottLevel = eloallito.apply(newArrayList(pappKrisztina, kisNiki));
		
		KuldendoLevelMatcher elvartLevelMatcher = new KuldendoLevelMatcher()
														.cimzettek()
														.targy("Próbaidő vége")
														.tartalom("A(z) Papp Krisztina dolgozó próbaideje 2013.03.22-kor le fog járni." + NEW_LINE +
												                  "A(z) Kis Niki dolgozó próbaideje 2013.03.23-kor le fog járni." + NEW_LINE);
		

		assertThat(kapottLevel, elvartLevelMatcher);
	}
}
