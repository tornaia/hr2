package test.unit.hu.interconnect.hr.module.reports.monthlypayrolldata;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.util.DateUtils.parseHonap;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus;
import hu.interconnect.hr.dao.JelenletiAdatDAO;
import hu.interconnect.hr.domain.JelenletiAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.monthlypayrolldata.MonthlyPayrollDataRowFromPersonaldataCreator;
import hu.interconnect.util.DateIterator;
import test.builder.JelenletiAdatBuilder;
import test.builder.SzemelyitorzsBuilder;
import test.matcher.MapMatcher;
import test.unit.AbstractBackendUnitTest;

public class MonthlyPayrollDataRowFromPersonaldataCreatorTest extends AbstractBackendUnitTest {
	
	@Mock
	private JelenletiAdatDAO jelenletiAdatDAO;
	
	private Date februar = parseHonap("2014.02");
	
	private MonthlyPayrollDataRowFromPersonaldataCreator eloallito;
	
	@Before
	public void setup() {
		eloallito = new MonthlyPayrollDataRowFromPersonaldataCreator(jelenletiAdatDAO, februar);
	}
	
	@Test
	public void toTobbMint99Ora59perc() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder()
											.tsz(2)
											.letrehoz();
		
		List<JelenletiAdat> jelenletiAdatok = newArrayList();
		for (Date date : DateIterator.daysOfMonthIterator(februar)) {
			jelenletiAdatok.add(new JelenletiAdatBuilder()
											.szemelyitorzs(szemelyitorzs)
											.tipus(JelenletiAdatTipus.MUNKA)
											.datum(date)
											.kezdet("08:00")
											.veg("18:00")
											.ledolgozott("09:00")
											.m30("10:10")
											.letrehoz());
		}
		when(jelenletiAdatDAO.findBySzemelyitorzsEsHonap(szemelyitorzs.getTsz(), februar)).thenReturn(jelenletiAdatok);
		
		Map<String, Object> sor = eloallito.apply(szemelyitorzs);
		
		MapMatcher<String, Object> elvartSorMatcher = new MapMatcher<String, Object>()
											.adottKulcsErtekParral("torzsszam", 2)
											.adottKulcsErtekParral("nev", "")
											.adottKulcsErtekParral("to50", "00:00")
											.adottKulcsErtekParral("to100", "00:00")
											.adottKulcsErtekParral("m30", "284:40")
											.adottKulcsErtekParral("km_hozzajarulas", "")
											.adottKulcsErtekParral("jelenleti_iv_leadva", "")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.01", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.02", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.03", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.04", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.05", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.06", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.07", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.08", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.09", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.10", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.11", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.12", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.13", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.14", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.15", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.16", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.17", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.18", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.19", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.20", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.21", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.22", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.23", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.24", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.25", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.26", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.27", "09:00")
											.adottKulcsErtekParral("ledolgozott_ora_2014.02.28", "09:00");
		
		assertThat(sor, elvartSorMatcher);
	}
}
