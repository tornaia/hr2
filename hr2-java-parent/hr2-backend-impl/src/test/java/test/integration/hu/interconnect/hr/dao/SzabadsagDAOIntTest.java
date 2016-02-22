package test.integration.hu.interconnect.hr.dao;

import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.BETEGSZABADSAG;
import static hu.interconnect.util.DateUtils.parseNap;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.dao.SzabadsagDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.util.DateIterator;
import test.builder.JogviszonyAdatokBuilder;
import test.builder.MunkakoriBesorolasBuilder;
import test.builder.OrvosiVizsgalatBuilder;
import test.builder.SzabadsagBuilder;
import test.builder.SzabadsagnyilvantartasBuilder;
import test.builder.SzemelyiAdatokBuilder;
import test.builder.SzemelyitorzsBuilder;
import test.integration.AbstractBackendIntTest;
import test.matcher.SzabadsagMatcher;
import test.matcher.SzemelyitorzsMatcher;

public class SzabadsagDAOIntTest extends AbstractBackendIntTest {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private SzabadsagDAO szabadsagDAO;
	
	private Szemelyitorzs szemelyitorzs;

	@Before
	public void szemelyitorzsetLetrehoz() {
		szemelyitorzs = new SzemelyitorzsBuilder()
							.tsz(1)
							.szemelyiAdatok(new SzemelyiAdatokBuilder()
									.vezeteknev("vezeteknev")
									.keresztnev("keresztnev")
									.letrehoz())
							.jogviszonyAdatok(new JogviszonyAdatokBuilder()
									.allomanymod(Allomanymod.AKTIV)
									.letrehoz())
							.munkakoriBesorolas(new MunkakoriBesorolasBuilder()
									.uzemanyagElszamolas(false)
									.letrehoz())
							.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
									.gyakorisag(12)
									.letrehoz())
							.szabadsagNyilvantartas(new SzabadsagnyilvantartasBuilder()
									.megvaltottSzabadsag(0)
									.letrehoz())
							.letrehoz();
		szemelyitorzsDAO.persist(szemelyitorzs);
	}
	
	@Test
	public void yearMonthDayBetweenClauseHelyes1() {
		szabadsagokatLetrehoz("2009.11.22", "2015.06.09");
		
		szabadsagDAO.removeBySzemelyitorzsEsIdoszak(szemelyitorzs.getTsz(), parseNap("2009.11.22"), parseNap("2013.07.06"));
		szabadsagDAO.removeBySzemelyitorzsEsIdoszak(szemelyitorzs.getTsz(), parseNap("2013.07.08"), parseNap("2015.06.09"));
		
		List<Szabadsag> kapottSzabadsagok = szabadsagDAO.findAll();
		
		SzabadsagMatcher elvartSzabadsag = new SzabadsagMatcher()
													.szemelyitorzs(new SzemelyitorzsMatcher()
																					.adottTsszel(1))
													.datum("2013.07.07")
													.jelleg(BETEGSZABADSAG);
		
		assertThat(kapottSzabadsagok, contains(elvartSzabadsag));
	}
	
	@Test
	public void yearMonthDayBetweenClauseHelyes2() {
		szabadsagokatLetrehoz("2009.11.20", "2009.11.30");
		
		szabadsagDAO.removeBySzemelyitorzsEsIdoszak(szemelyitorzs.getTsz(), parseNap("2009.11.21"), parseNap("2009.11.24"));
		
		szabadsagDAO.removeBySzemelyitorzsEsIdoszak(szemelyitorzs.getTsz(), parseNap("2009.11.26"), parseNap("2009.11.29"));
		
		List<Szabadsag> kapottSzabadsagok = szabadsagDAO.findAll();
		
		SzabadsagMatcher elvartSzabadsag1 = new SzabadsagMatcher()
													.szemelyitorzs(new SzemelyitorzsMatcher()
																					.adottTsszel(1))
													.datum("2009.11.20")
													.jelleg(BETEGSZABADSAG);
		
		SzabadsagMatcher elvartSzabadsag2 = new SzabadsagMatcher()
													.szemelyitorzs(new SzemelyitorzsMatcher()
																					.adottTsszel(1))
													.datum("2009.11.25")
													.jelleg(BETEGSZABADSAG);
													
		SzabadsagMatcher elvartSzabadsag3 = new SzabadsagMatcher()
													.szemelyitorzs(new SzemelyitorzsMatcher()
																					.adottTsszel(1))
													.datum("2009.11.30")
													.jelleg(BETEGSZABADSAG);
		
		assertThat(kapottSzabadsagok, contains(asList(elvartSzabadsag1, elvartSzabadsag2, elvartSzabadsag3)));
	}
	
	@Test
	public void yearMonthDayClauseHelyesenNemAdVisszaSemmit() {
		szabadsagokatLetrehoz("2013.01.26", "2013.01.27");
		
		Optional<Szabadsag> optionalSzabadsag = szabadsagDAO.findBySzemelyitorzsEsNap(szemelyitorzs.getTsz(), parseNap("2013.01.25"));
	
		assertFalse(optionalSzabadsag.isPresent());
	}
	
	@Test
	public void yearMonthDayClauseHelyesVisszaadEgySzabadsagot() {
		szabadsagokatLetrehoz("2013.01.26", "2013.01.27");

		Optional<Szabadsag> optionalSzabadsag = szabadsagDAO.findBySzemelyitorzsEsNap(szemelyitorzs.getTsz(), parseNap("2013.01.26"));

		SzabadsagMatcher elvartSzabadsag = new SzabadsagMatcher()
													.szemelyitorzs(new SzemelyitorzsMatcher()
																					.adottTsszel(1))
													.datum("2013.01.26")
													.jelleg(BETEGSZABADSAG);
		
		assertThat(optionalSzabadsag.get(), elvartSzabadsag);
	}

	private void szabadsagokatLetrehoz(String kezdetStr, String vegStr) {
		for (Date nap : new DateIterator(parseNap(kezdetStr), parseNap(vegStr))) {
			Szabadsag szabadsag = new SzabadsagBuilder()
										.szemelyitorzs(szemelyitorzs)
										.nap(nap)
										.jelleg(BETEGSZABADSAG)
										.letrehoz();
			
			szabadsagDAO.persist(szabadsag);
		}
	}
}
