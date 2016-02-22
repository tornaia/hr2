package test.unit.hu.interconnect.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hu.interconnect.hr.backend.api.enumeration.LakcimAktualis;
import hu.interconnect.util.Localizer;
import test.unit.AbstractBackendUnitTest;

public class LocalizerTest extends AbstractBackendUnitTest {
	
	@Test
	public void getEnumhuHUFelhasznalonak() {
		betekintoBejelentkezik();
		String allandoLakcim = Localizer.getMessage(LakcimAktualis.ALLANDO);
		assertEquals("\u00c1lland\u00f3", allandoLakcim);
	}
	
	@Test
	public void getEnumenUSFelhasznalonak() {
		betekintoEnUsBejelentkezik();
		String allandoLakcim = Localizer.getMessage(LakcimAktualis.ALLANDO);
		assertEquals("Permanent", allandoLakcim);
	}
	
	@Test
	public void getMessageBundle() {
		betekintoBejelentkezik();
		String str = Localizer.getMessage("common", "confirm.delete");
		assertEquals("Biztosan t\u00f6rl\u00f6d?", str);
	}
}
