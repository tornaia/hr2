package test.unit.hu.interconnect.util;

import static hu.interconnect.util.DateUtils.aktualisIdo;
import static hu.interconnect.util.DateUtils.getNapokSzama;
import static hu.interconnect.util.DateUtils.getNapokSzamaEvbenEsHonapban;
import static hu.interconnect.util.DateUtils.isBetween;
import static hu.interconnect.util.DateUtils.oraPercFormaz;
import static hu.interconnect.util.DateUtils.parseHonap;
import static hu.interconnect.util.DateUtils.parseNap;
import static hu.interconnect.util.DateUtils.parseNapOraPercMasodperc;
import static hu.interconnect.util.DateUtils.tesztDatumotBeallit;
import static hu.interconnect.util.DateUtils.tesztIdopontotBeallit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.util.DateUtils;
import test.matcher.DateMatcher;
import test.matcher.TimestampMatcher;
import test.unit.AbstractUnitTest;

public class DateUtilsTest extends AbstractUnitTest {

	@Test
	public void betweenNullInput() {
		assertFalse(isBetween(null, null, null));
	}
	
	@Test
	public void betweenInputEqualsToStart() {
		assertTrue(isBetween(parseNap("2013.01.01"), parseNap("2013.01.01"), parseNap("2013.01.02")));
	}
	
	@Test
	public void betweenInputEqualsToStartAndEnd() {
		assertTrue(isBetween(parseNap("2013.01.01"), parseNap("2013.01.01"), parseNap("2013.01.01")));
	}
	
	@Test
	public void betweenInputIsEarlier() {
		assertFalse(isBetween(parseNap("2013.01.01"), parseNap("2013.01.02"), parseNap("2013.01.03")));
	}
	
	@Test
	public void betweenInputIsLater() {
		assertFalse(isBetween(parseNap("2013.01.03"), parseNap("2013.01.01"), parseNap("2013.01.02")));
	}
	
	@Test
	public void betweenInputEqualsToEnd() {
		assertFalse(isBetween(parseNap("2013.01.02"), parseNap("2013.01.01"), parseNap("2013.01.02")));
	}
	
	@Test
	public void between() {
		assertTrue(isBetween(parseNap("2013.01.02"), parseNap("2013.01.01"), parseNap("2013.01.03")));
	}
	
	@Test
	public void getNapokSzamaDateDateAlapeset() {
		Date _20120603 = parseNap("2012.06.03");
		Date _20120615 = parseNap("2012.06.15");
		
		int napokSzama = getNapokSzama(_20120603, _20120615);
		assertEquals(13, napokSzama);
	}
	
	@Test
	public void getNapokSzamaDateDateOraEloreAllitas() {
		Date _20120301 = parseNap("2012.03.01");
		Date _20120331 = parseNap("2012.03.31");
		
		int napokSzama = getNapokSzama(_20120301, _20120331);
		assertEquals(31, napokSzama);
	}
	
	@Test
	public void getNapokSzamaDateDateOraVisszaAllitas() {
		Date _20121001 = parseNap("2012.10.01");
		Date _20121031 = parseNap("2012.10.31");
		
		int napokSzama = getNapokSzama(_20121001, _20121031);
		assertEquals(31, napokSzama);
	}
	
	@Test
	public void getNapokSzamaEvbenEsHonapban2013Februar() {
		assertEquals(28, getNapokSzamaEvbenEsHonapban(parseNap("2013.2.1")));
	}
	
	@Test
	public void getNapokSzamaEvbenEsHonapban2012Februar() {
		assertEquals(29, getNapokSzamaEvbenEsHonapban(parseNap("2012.2.1")));
	}
	
	@Test
	public void getNapokSzamaEvbenEsHonapban2013Januar() {
		assertEquals(31, getNapokSzamaEvbenEsHonapban(parseNap("2013.01.01")));
	}
	
	@Test
	public void getNapokSzamaEvbenEsHonapban2013November() {
		assertEquals(30, getNapokSzamaEvbenEsHonapban(parseNap("2013.11.30")));
	}
	
	@Test
	public void tesztDatumotBeallitTest() {
		tesztDatumotBeallit("2013.02.20");
		
		assertThat(aktualisIdo(), new DateMatcher("2013.02.20"));
		assertThat(aktualisIdo(), new TimestampMatcher("2013.02.20 00:00:00"));
	}
	
	@Test
	public void tesztIdopontotBeallitTest() {
		tesztIdopontotBeallit("2013.02.20 21:56:44");
		
		assertThat(aktualisIdo(), new TimestampMatcher("2013.02.20 21:56:44"));
	}
	
	@Test
	public void oraPercFormaz0() {
		assertEquals("00:00", oraPercFormaz(parseNapOraPercMasodperc("1970.01.01 00:00:00")));
	}
	
	@Test
	public void oraPercFormaz1() {
		assertEquals("05:06", oraPercFormaz(parseNapOraPercMasodperc("1970.01.01 05:06:00")));
	}
	
	@Test
	public void oraPercFormaz2() {
		assertEquals("20:42", oraPercFormaz(parseNapOraPercMasodperc("1970.01.01 20:42:55")));
	}
	
	@Test
	public void oraPercFormaz3() {
		assertEquals("00:04", oraPercFormaz(parseNapOraPercMasodperc("1970.01.01 0:4:50")));
	}
	
	@Test
	public void oraPercFormaz4() {
		assertEquals("00:00", oraPercFormaz(parseNapOraPercMasodperc("1970.01.01 0:0:01")));
	}
	
	@Test
	public void oraPercFormaz5() {
		assertEquals("24:00", oraPercFormaz(parseNapOraPercMasodperc("1970.01.02 0:0:01")));
	}
	
	@Test
	public void oraPercFormaz6() {
		assertEquals("24:10", oraPercFormaz(parseNapOraPercMasodperc("1970.01.02 00:10:01")));
	}
	
	@Test
	public void oraPercFormaz7() {
		assertEquals("72:05", oraPercFormaz(parseNapOraPercMasodperc("1970.01.04 00:05:01")));
	}
	
	@Test
	public void oraPercFormaz8() {
		assertEquals("101:05", oraPercFormaz(parseNapOraPercMasodperc("1970.01.05 05:05:01")));
	}
	
	@Test
	public void parseHonap0() {
		assertEquals(1388530800000L, parseHonap("2014.01").getTime());
	}
	
	@Test
	public void parseHonap1() {
		assertEquals(1388530800000L, parseHonap("2014.1").getTime());
	}
	
	@Test
	public void parseHonap2() {
		assertEquals(parseNap("2014.10.01"), parseHonap("2014.10"));
	}
	
	@Test(expected=ProgramozasiHiba.class)
	public void parseHonapHibasFormatum() {
		parseHonap("2014.");
	}
	
	@Test
	public void tesztIdoBeVanAllitva() {
		DateUtils.tesztDatumotBeallit("2015.09.29");
		assertEquals(DateUtils.parseNap("2015.09.29"), DateUtils.aktualisIdo());
	}
	
	@Test
	public void tesztIdoNincsBeallitva() {
		assertEquals(new Date().getTime(), DateUtils.aktualisIdo().getTime(), 1D);
	}
	
	@Test
	public void aSzombatHetvege() {
		assertTrue(DateUtils.isHetvege(parseNap("2015.09.26")));
	}
	
	@Test
	public void aVasarnapHetvege() {
		assertTrue(DateUtils.isHetvege(parseNap("2015.09.27")));
	}
	
	@Test
	public void aHetfoNemHetvege() {
		assertFalse(DateUtils.isHetvege(parseNap("2015.09.28")));
	}
	
	@Test
	public void aPentekMunknanap() {
		assertTrue(DateUtils.isMunkanap(parseNap("2015.09.25")));
	}
	
	@Test
	public void aSzombtaNemMunknanap() {
		assertFalse(DateUtils.isMunkanap(parseNap("2015.09.26")));
	}
	
	@Test
	public void aNapBeleesikTeljes() {
		assertTrue(DateUtils.isInInclusive(parseNap("2015.09.29"), parseNap("2015.09.28"), parseNap("2015.09.30")));
	}
	
	@Test
	public void aNapMegegyezikAKezdettel() {
		assertTrue(DateUtils.isInInclusive(parseNap("2015.09.28"), parseNap("2015.09.28"), parseNap("2015.09.30")));
	}
	
	@Test
	public void aNapMegegyezikAVeggel() {
		assertTrue(DateUtils.isInInclusive(parseNap("2015.09.29"), parseNap("2015.09.28"), parseNap("2015.09.30")));
	}
	
	@Test
	public void aNapAKezdetEsAVegIsMegegyezik() {
		assertTrue(DateUtils.isInInclusive(parseNap("2015.09.28"), parseNap("2015.09.28"), parseNap("2015.09.28")));
	}
	
	@Test
	public void aNapKorabbanVan() {
		assertFalse(DateUtils.isInInclusive(parseNap("2015.09.28"), parseNap("2015.09.29"), parseNap("2015.09.30")));
	}
	
	@Test
	public void aNapKesobbVan() {
		assertFalse(DateUtils.isInInclusive(parseNap("2015.09.30"), parseNap("2015.09.28"), parseNap("2015.09.29")));
	}
	
	@Test
	public void napokSzamaNormalEset() {
		assertEquals(2, DateUtils.getNapokSzama(parseNap("2015.09.28"), parseNap("2015.09.29")));
	}
	
	@Test
	public void napokSzamaParOraKulonbseggelDeKulonbozoNapEseten() {
		assertEquals(2, DateUtils.getNapokSzama(parseNapOraPercMasodperc("2015.09.27 23:59:58"), parseNapOraPercMasodperc("2015.09.28 01:02:03")));
	}
	
	@Test
	public void napokSzamaHaAKezdetEsAVegMegegyezik() {
		assertEquals(1, DateUtils.getNapokSzama(parseNap("2015.09.29"), parseNap("2015.09.29")));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void napokSzamaHaAKezdetKesobbVanMintAVeg() {
		assertEquals(1, DateUtils.getNapokSzama(parseNap("2015.09.30"), parseNap("2015.09.29")));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void napokSzamaHaAKezdetNull() {
		assertEquals(1, DateUtils.getNapokSzama(null, parseNap("2015.09.29")));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void napokSzamaHaAVegNull() {
		assertEquals(1, DateUtils.getNapokSzama(parseNap("2015.09.29"), null));
	}
	
	@Test
	public void honapElsoNapja() {
		assertEquals(parseNap("2015.01.01"), DateUtils.getHoElsoNapja(parseNap("2015.01.31")));
	}
	
	@Test
	public void honapElsoNapjaHaElsejetVizsgalok() {
		assertEquals(parseNap("2015.09.01"), DateUtils.getHoElsoNapja(parseNap("2015.09.01")));
	}
	
	@Test
	public void evUtolsoNapja() {
		assertEquals(parseNap("2015.12.31"), DateUtils.getEvUtolsoNapja(parseNap("2015.09.26")));
	}
	
	@Test
	public void evUtolsoNapjaHaAzEvUtolsoNapjatVizsgalom() {
		assertEquals(parseNap("2015.12.31"), DateUtils.getEvUtolsoNapja(parseNap("2015.12.31")));
	}
	
	@Test
	public void evUtolsoNapjaHaAzEvElsoNapjatVizsgalom() {
		assertEquals(parseNap("2015.12.31"), DateUtils.getEvUtolsoNapja(parseNap("2015.01.01")));
	}
	
	@Test
	public void evUtolsoNapjaSzamkent() {
		assertEquals(parseNap("2015.12.31"), DateUtils.getEvUtolsoNapja(2015));
	}
	
	@Test
	public void evElsoNapjaSzamkent() {
		assertEquals(parseNap("2015.01.01"), DateUtils.getEvElsoNapja(2015));
	}
	
	@Test
	public void evElsoNapjaHaAVizsgaltNapAzElsoNap() {
		assertEquals(parseNap("2015.01.01"), DateUtils.getEvElsoNapja(parseNap("2015.01.01")));
	}
	
	@Test
	public void evElsoNapja() {
		assertEquals(parseNap("2015.01.01"), DateUtils.getEvElsoNapja(parseNap("2015.02.28")));
	}
	
	@Test
	public void napHozzaAdas() {
		assertEquals(parseNap("2015.03.02"), DateUtils.hozzaadNapot(parseNap("2015.02.28"), 2));
	}
	
	@Test
	public void napHozzaAdas0() {
		assertEquals(parseNap("2015.02.28"), DateUtils.hozzaadNapot(parseNap("2015.02.28"), 0));
	}
	
	@Test
	public void napHozzaAdasMinusz1() {
		assertEquals(parseNap("2015.02.27"), DateUtils.hozzaadNapot(parseNap("2015.02.28"), -1));
	}
	
	@Test
	public void azonosHonapHaParameter1Null() {
		assertFalse(DateUtils.azonosHonap(null, parseNap("2015.02.28")));
	}
	
	@Test
	public void azonosHonapHaParameter2Null() {
		assertFalse(DateUtils.azonosHonap(parseNap("2015.02.28"), null));
	}
	
	@Test
	public void azonosHonapHaMindketParameterNull() {
		assertFalse(DateUtils.azonosHonap(null, null));
	}
	
	@Test
	public void azonosHonapAzonosNapEseten() {
		assertTrue(DateUtils.azonosHonap(parseNap("2015.02.28"), parseNap("2015.02.28")));
	}
	
	@Test
	public void azonosHonapKulonbozoNapEseten() {
		assertTrue(DateUtils.azonosHonap(parseNap("2015.02.01"), parseNap("2015.02.28")));
	}
	
	@Test
	public void nemAzonosHonap() {
		assertFalse(DateUtils.azonosHonap(parseNap("2015.02.01"), parseNap("2015.03.01")));
	}
	
	@Test
	public void azonosNap() {
		assertTrue(DateUtils.azonosNap(parseNap("2015.02.01"), parseNap("2015.02.01")));
	}
	
	@Test
	public void azonosNapDeKulonbozoOraPercMasodperc1() {
		assertTrue(DateUtils.azonosNap(parseNapOraPercMasodperc("2015.02.01 05:06:07"), parseNap("2015.02.01")));
	}
	
	@Test
	public void azonosNapDeKulonbozoOraPercMasodperc2() {
		assertTrue(DateUtils.azonosNap(parseNapOraPercMasodperc("2015.02.01 05:06:07"), parseNapOraPercMasodperc("2015.02.01 23:59:59")));
	}
	
	@Test
	public void nemAzonosNap() {
		assertFalse(DateUtils.azonosNap(parseNap("2015.02.01"), parseNap("2015.02.02")));
	}
	
	@Test
	public void nemAzonosNapHaParam1Null() {
		assertFalse(DateUtils.azonosNap(null, parseNap("2015.02.02")));
	}
	
	@Test
	public void nemAzonosNapHaParam2Null() {
		assertFalse(DateUtils.azonosNap(parseNap("2015.02.02"), null));
	}
	
	@Test
	public void nemAzonosNapHaMindketParamNull() {
		assertFalse(DateUtils.azonosNap(null, null));
	}
	
	@Test
	public void napFormaz() {
		assertEquals("2015.09.26", DateUtils.napFormaz(parseNap("2015.09.26")));
	}
	
	@Test
	public void honapFormaz() {
		assertEquals("2015.09", DateUtils.honapFormaz(parseNap("2015.09.26")));
	}
	
	@Test
	public void evFormaz() {
		assertEquals("2015", DateUtils.evFormaz(parseNap("2015.09.26")));
	}
	
	@Test
	public void parseOraPercMasodperc1() {
		assertEquals(DateUtils.parseNapOraPercMasodperc("1970.01.01 01:02:00"), DateUtils.parseOraPerc("01:02"));
	}
	
	@Test
	public void parseOraPercMasodperc2() {
		assertEquals(DateUtils.parseNapOraPercMasodperc("1970.01.01 01:02:00"), DateUtils.parseOraPerc("1:02"));
	}
	
	@Test
	public void parseOraPercMasodperc3() {
		assertEquals(DateUtils.parseNapOraPercMasodperc("1970.01.01 01:02:00"), DateUtils.parseOraPerc("01:2"));
	}
	
	@Test
	public void parseOraPercMasodperc4() {
		assertEquals(DateUtils.parseNapOraPercMasodperc("1970.01.01 01:02:00"), DateUtils.parseOraPerc("1:2"));
	}
	
	@Test
	public void parseOraPercMasodpercNullaNulla() {
		assertEquals(DateUtils.parseNapOraPercMasodperc("1970.01.01 00:00:00"), DateUtils.parseOraPerc("0:0"));
	}
	
	@Test
	public void parseOraPercMasodpercTulcsordulaskor() {
		assertEquals(DateUtils.parseNapOraPercMasodperc("1970.01.01 01:01:00"), DateUtils.parseOraPerc("0:61"));
	}
	
	@Test(expected = ProgramozasiHiba.class)
	public void parseOraPercMasodpercHibasFormatum() {
		DateUtils.parseOraPerc("01 02");
	}
	
	@Test
	public void parseEv() {
		assertEquals(parseNap("2015.01.01"), DateUtils.parseEv("2015"));
	}
	
	@Test(expected = ProgramozasiHiba.class)
	public void parseEvHibasFormatum() {
		DateUtils.parseEv("2015.");
	}
	
	@Test(expected = ProgramozasiHiba.class)
	public void parseNapHibasFormatum() {
		DateUtils.parseNap("2a0.12.12");
	}
	
	@Test(expected = ProgramozasiHiba.class)
	public void parseNapOraPercMasodpercHibasFormatum() {
		parseNapOraPercMasodperc("2015.09.26");
	}
	
	@Test
	public void nullaIdopillanat() {
		assertEquals(parseNap("1970.01.01"), DateUtils.getNullaIdopillanat());
	}
}
