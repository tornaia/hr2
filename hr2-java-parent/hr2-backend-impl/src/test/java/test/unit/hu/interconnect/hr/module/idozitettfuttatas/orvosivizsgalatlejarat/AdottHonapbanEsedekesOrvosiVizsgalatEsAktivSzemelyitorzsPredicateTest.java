package test.unit.hu.interconnect.hr.module.idozitettfuttatas.orvosivizsgalatlejarat;

import static hu.interconnect.util.DateUtils.parseHonap;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat.AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicate;
import test.builder.JogviszonyAdatokBuilder;
import test.builder.OrvosiVizsgalatBuilder;
import test.builder.SzemelyitorzsBuilder;
import test.unit.AbstractBackendUnitTest;

public class AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicateTest extends AbstractBackendUnitTest {

	@Test
	public void aktivSzemelyitorzsKivalasztasraKerul() {
		Szemelyitorzs kilepettSzemelyitorzs = new SzemelyitorzsBuilder()
														.tsz(1)
														.jogviszonyAdatok(new JogviszonyAdatokBuilder()
																						.allomanymod(Allomanymod.AKTIV)
																						.letrehoz())
														.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
																						.gyakorisag(12)
																						.utolsoOrvosiVizsgalatIdopontja("2012.04.12")
																						.letrehoz())
														.letrehoz();
		
		assertTrue(new AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicate(parseHonap("2013.04")).test(kilepettSzemelyitorzs));
	}
	
	@Test
	public void kilepettSzemelyitorzsSzuresreKerul() {
		Szemelyitorzs kilepettSzemelyitorzs = new SzemelyitorzsBuilder()
														.tsz(1)
														.jogviszonyAdatok(new JogviszonyAdatokBuilder()
																						.allomanymod(Allomanymod.KILEPETT)
																						.letrehoz())
														.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
																						.gyakorisag(12)
																						.utolsoOrvosiVizsgalatIdopontja("2012.04.12")
																						.letrehoz())
														.letrehoz();
		
		assertFalse(new AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicate(parseHonap("2013.04")).test(kilepettSzemelyitorzs));
	}
	
	@Test
	public void gyesgyedSzemelyitorzsSzuresreKerul() {
		Szemelyitorzs kilepettSzemelyitorzs = new SzemelyitorzsBuilder()
														.tsz(1)
														.jogviszonyAdatok(new JogviszonyAdatokBuilder()
																						.allomanymod(Allomanymod.GYESGYED)
																						.letrehoz())
														.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
																						.gyakorisag(12)
																						.utolsoOrvosiVizsgalatIdopontja("2012.04.12")
																						.letrehoz())
														.letrehoz();
		
		assertFalse(new AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicate(parseHonap("2013.04")).test(kilepettSzemelyitorzs));
	}
	
	@Test
	public void honapVegeHogyMukodik() {
		Szemelyitorzs honapUtolsoNapjaSzemelyitorzs1 = new SzemelyitorzsBuilder()
														.tsz(1)
														.jogviszonyAdatok(new JogviszonyAdatokBuilder()
																						.allomanymod(Allomanymod.AKTIV)
																						.letrehoz())
														.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
																						.gyakorisag(1)
																						.utolsoOrvosiVizsgalatIdopontja("2012.10.31")
																						.letrehoz())
														.letrehoz();
		
		assertTrue(new AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicate(parseHonap("2012.11")).test(honapUtolsoNapjaSzemelyitorzs1));
	}
	
	@Test
	public void honapVegeHogyMukodikJanuarban() {
		Szemelyitorzs honapUtolsoNapjaSzemelyitorzs2 = new SzemelyitorzsBuilder()
														.tsz(1)
														.jogviszonyAdatok(new JogviszonyAdatokBuilder()
																						.allomanymod(Allomanymod.AKTIV)
																						.letrehoz())
														.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
																						.gyakorisag(1)
																						.utolsoOrvosiVizsgalatIdopontja("2012.01.31")
																						.letrehoz())
														.letrehoz();
		
		assertTrue(new AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicate(parseHonap("2012.02")).test(honapUtolsoNapjaSzemelyitorzs2));
	}
}
