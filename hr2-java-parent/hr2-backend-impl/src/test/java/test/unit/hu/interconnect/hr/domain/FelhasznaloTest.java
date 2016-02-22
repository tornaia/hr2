package test.unit.hu.interconnect.hr.domain;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import hu.interconnect.hr.backend.api.dto.FelhasznaloEditDTO;
import hu.interconnect.hr.backend.api.enumeration.Locale;
import hu.interconnect.hr.backend.api.enumeration.Szerep;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.Szemelyitorzs;
import test.builder.FelhasznaloBuilder;
import test.builder.FelhasznaloEditDTOBuilder;
import test.builder.SzemelyitorzsBuilder;
import test.matcher.FelhasznaloMatcher;
import test.matcher.SzemelyitorzsMatcher;
import test.unit.AbstractBackendUnitTest;

public class FelhasznaloTest extends AbstractBackendUnitTest {

	@Test(expected=IllegalArgumentException.class)
	public void dolgozonakKellHogyLegyenSzemelyitorzseTest() {
		Felhasznalo felhasznalo = new FelhasznaloBuilder()
										.nev("admin")
										.jelszo("jelszo")
										.szerep(Szerep.ADMINISTRATOR)
										.enabled(true)
										.szemelyitorzs(null)
										.letrehoz();

		FelhasznaloEditDTO dto = new FelhasznaloEditDTOBuilder()
										.nev("admin")
										.jelszo("jelszo")
										.szerep(Szerep.DOLGOZO)
										.enabled(true)
										.tsz(null)
										.locale(Locale.hu_HU)
										.letrehoz();

		felhasznalo.merge(dto);
	}

	@Test(expected=IllegalArgumentException.class)
	public void adminisztratornakNemLehetSzemelyitorzseTest() {
		Felhasznalo felhasznalo = new FelhasznaloBuilder()
										.nev("admin")
										.jelszo("jelszo")
										.szerep(Szerep.ADMINISTRATOR)
										.enabled(true)
										.szemelyitorzs(null)
										.letrehoz();

		FelhasznaloEditDTO dto = new FelhasznaloEditDTOBuilder()
										.nev("admin")
										.jelszo("jelszo")
										.szerep(Szerep.ADMINISTRATOR)
										.enabled(true)
										.tsz(1)
										.locale(Locale.hu_HU)
										.letrehoz();

		felhasznalo.merge(dto);
	}

	@Test(expected=IllegalArgumentException.class)
	public void betekintonekNemLehetSzemelyitorzseTest() {
		Felhasznalo felhasznalo = new FelhasznaloBuilder()
										.nev("admin")
										.jelszo("jelszo")
										.szerep(Szerep.ADMINISTRATOR)
										.enabled(true)
										.szemelyitorzs(null)
										.letrehoz();

		FelhasznaloEditDTO dto = new FelhasznaloEditDTOBuilder()
										.nev("admin")
										.jelszo("jelszo")
										.szerep(Szerep.BETEKINTO)
										.enabled(true)
										.tsz(1)
										.locale(Locale.hu_HU)
										.letrehoz();

		felhasznalo.merge(dto);
	}

	@Test
	public void nullJelszoNemIrodikAtTest() {
		Felhasznalo felhasznalo = new FelhasznaloBuilder()
										.nev("admin")
										.jelszo("jelszo")
										.szerep(Szerep.ADMINISTRATOR)
										.szemelyitorzs(null)
										.enabled(true)
										.locale(Locale.hu_HU)
										.letrehoz();

		FelhasznaloEditDTO dto = new FelhasznaloEditDTOBuilder()
										.nev("admin")
										.jelszo(null)
										.szerep(Szerep.BETEKINTO)
										.tsz(null)
										.locale(Locale.hu_HU)
										.enabled(false)
										.letrehoz();

		felhasznalo.merge(dto);

		FelhasznaloMatcher elvartFelhasznalo = new FelhasznaloMatcher()
													.adottNevvel("admin")
													.adottJelszoval("jelszo")
													.adottSzereppel(Szerep.BETEKINTO)
													.adottSzemelyitorzzsel(CoreMatchers.nullValue(Szemelyitorzs.class))
													.adottEnableddel(false);

		assertThat(felhasznalo, elvartFelhasznalo);
	}

	@Test
	public void uresJelszoNemIrodikAtTest() {
		Felhasznalo felhasznalo = new FelhasznaloBuilder()
										.nev("admin")
										.jelszo("jelszo")
										.szerep(Szerep.ADMINISTRATOR)
										.szemelyitorzs(null)
										.enabled(true)
										.locale(Locale.hu_HU)
										.letrehoz();

		FelhasznaloEditDTO dto = new FelhasznaloEditDTOBuilder()
										.nev("admin")
										.jelszo("")
										.szerep(Szerep.BETEKINTO)
										.tsz(null)
										.enabled(false)
										.locale(Locale.hu_HU)
										.letrehoz();

		felhasznalo.merge(dto);

		FelhasznaloMatcher elvartFelhasznalo = new FelhasznaloMatcher()
													.adottNevvel("admin")
													.adottJelszoval("jelszo")
													.adottSzereppel(Szerep.BETEKINTO)
													.adottSzemelyitorzzsel(CoreMatchers.nullValue(Szemelyitorzs.class))
													.adottEnableddel(false);

		assertThat(felhasznalo, elvartFelhasznalo);
	}

	@Test
	public void ujJelszoAtirodikIrodikAtTest() {
		Felhasznalo felhasznalo = new FelhasznaloBuilder()
										.nev("admin")
										.jelszo("jelszo")
										.szerep(Szerep.DOLGOZO)
										.szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz())
										.enabled(true)
										.locale(Locale.hu_HU)
										.letrehoz();

		FelhasznaloEditDTO dto = new FelhasznaloEditDTOBuilder()
										.nev("admin")
										.jelszo("ujJelszo")
										.szerep(Szerep.DOLGOZO)
										.enabled(false)
										.tsz(2)
										.locale(Locale.hu_HU)
										.letrehoz();

		felhasznalo.merge(dto);

		FelhasznaloMatcher elvartFelhasznalo = new FelhasznaloMatcher()
													.adottNevvel("admin")
													.adottJelszoval("ujJelszo")
													.adottSzereppel(Szerep.DOLGOZO)
													.adottSzemelyitorzzsel(new SzemelyitorzsMatcher().adottTsszel(2))
													.adottEnableddel(false);

		assertThat(felhasznalo, elvartFelhasznalo);
	}
}
