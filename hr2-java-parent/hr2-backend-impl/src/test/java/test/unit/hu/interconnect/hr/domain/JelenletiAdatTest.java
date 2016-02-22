package test.unit.hu.interconnect.hr.domain;

import static org.springframework.util.Assert.notNull;

import java.util.Date;

import org.junit.Test;

import hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus;
import hu.interconnect.hr.domain.JelenletiAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.util.DateUtils;
import test.builder.SzemelyitorzsBuilder;
import test.unit.AbstractBackendUnitTest;

public class JelenletiAdatTest extends AbstractBackendUnitTest {

	@Test(expected=IllegalArgumentException.class)
	public void szemelyitorzsKotelezo() {
		Szemelyitorzs szemelyitorzs = null;
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.MUNKA;
		Date kezdet = new Date();
		Date veg = new Date();
		Date ledolgozott = new Date();
		Date to50 = new Date();
		Date to100 = new Date();
		Date m30 = new Date();
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void datumKotelezo() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = null;
		JelenletiAdatTipus tipus = JelenletiAdatTipus.MUNKA;
		Date kezdet = new Date();
		Date veg = new Date();
		Date ledolgozott = new Date();
		Date to50 = new Date();
		Date to100 = new Date();
		Date m30 = new Date();
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test
	public void munkaEsetenMindenMezoToltheto() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.MUNKA;
		Date kezdet = new Date();
		Date veg = new Date();
		Date ledolgozott = new Date();
		Date to50 = new Date();
		Date to100 = new Date();
		Date m30 = new Date();
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test
	public void munkaEsetenCsakAKezdetVegEsLedolgozottKotelezo() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.MUNKA;
		Date kezdet = new Date();
		Date veg = new Date();
		Date ledolgozott = new Date();
		Date to50 = null;
		Date to100 = null;
		Date m30 = null;
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}

	@Test(expected=IllegalArgumentException.class)
	public void tipusKotelezo() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = null;
		Date kezdet = null;
		Date veg = null;
		Date ledolgozott = null;
		Date to50 = null;
		Date to100 = null;
		Date m30 = null;
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void munkaEsetenAKezdetKotelezo() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.MUNKA;
		Date kezdet = null;
		Date veg = new Date();
		Date ledolgozott = new Date();
		Date to50 = new Date();
		Date to100 = new Date();
		Date m30 = new Date();
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void munkaEsetenAVegKotelezo() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.MUNKA;
		Date kezdet = new Date();
		Date veg = null;
		Date ledolgozott = new Date();
		Date to50 = new Date();
		Date to100 = new Date();
		Date m30 = new Date();
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void munkaEsetenALedolgozottKotelezo() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.MUNKA;
		Date kezdet = new Date();
		Date veg = new Date();
		Date ledolgozott = null;
		Date to50 = new Date();
		Date to100 = new Date();
		Date m30 = new Date();
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test
	public void nemMunkaEsetenSemmiSemToltheto() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.SZABADNAP;
		Date kezdet = null;
		Date veg = null;
		Date ledolgozott = null;
		Date to50 = null;
		Date to100 = null;
		Date m30 = null;
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nemMunkaEsetenKezdetNemToltheto() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.SZABADNAP;
		Date kezdet = new Date();
		Date veg = null;
		Date ledolgozott = null;
		Date to50 = null;
		Date to100 = null;
		Date m30 = null;
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nemMunkaEsetenVegNemToltheto() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.SZABADNAP;
		Date kezdet = null;
		Date veg = new Date();
		Date ledolgozott = null;
		Date to50 = null;
		Date to100 = null;
		Date m30 = null;
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nemMunkaEsetenLedolgozottNemToltheto() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.SZABADNAP;
		Date kezdet = null;
		Date veg = null;
		Date ledolgozott = new Date();
		Date to50 = null;
		Date to100 = null;
		Date m30 = null;
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nemMunkaEsetenTo50NemToltheto() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.SZABADNAP;
		Date kezdet = null;
		Date veg = null;
		Date ledolgozott = null;
		Date to50 = new Date();
		Date to100 = null;
		Date m30 = null;
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nemMunkaEsetenTo100NemToltheto() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.SZABADNAP;
		Date kezdet = null;
		Date veg = null;
		Date ledolgozott = null;
		Date to50 = null;
		Date to100 = new Date();
		Date m30 = null;
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nemMunkaEsetenM30NemToltheto() {
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder().tsz(1).letrehoz();
		Date datum = DateUtils.aktualisIdo();
		JelenletiAdatTipus tipus = JelenletiAdatTipus.SZABADNAP;
		Date kezdet = null;
		Date veg = null;
		Date ledolgozott = null;
		Date to50 = null;
		Date to100 = null;
		Date m30 = new Date();
		notNull(new JelenletiAdat(szemelyitorzs, datum, tipus, kezdet, veg, ledolgozott, to50, to100, m30));
	}
}
