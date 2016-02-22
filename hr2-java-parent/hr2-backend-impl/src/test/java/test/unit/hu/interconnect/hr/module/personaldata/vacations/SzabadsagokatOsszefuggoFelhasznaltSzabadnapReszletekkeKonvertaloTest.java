package test.unit.hu.interconnect.hr.module.personaldata.vacations;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.hr.backend.api.enumeration.KivetelnapTipus.MUNKANAP;
import static hu.interconnect.hr.backend.api.enumeration.KivetelnapTipus.PIHENONAP;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasResponseDTO;
import hu.interconnect.hr.domain.Kivetelnap;
import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.hr.module.exceptiondays.KivetelnapokHelper;
import hu.interconnect.hr.module.personaldata.vacations.SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo;
import test.builder.KivetelnapBuilder;
import test.builder.SzabadsagBuilder;
import test.builder.SzemelyitorzsBuilder;
import test.matcher.SzabadsagFelhasznalasResponseDTOMatcher;
import test.unit.AbstractBackendUnitTest;

public class SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertaloTest extends AbstractBackendUnitTest {

	private SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo konvertalo = new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(new KivetelnapokHelper(new ArrayList<Kivetelnap>()));

	@Test
	public void nullIllegalArgumentExceptiontDob() {
		try {
			konvertalo.konvertal(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getLocalizedMessage(), "A bemeno parameter null!");
		}
	}

	@Test
	public void uresListaUresetAdVissza() {
		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(new ArrayList<Szabadsag>());

		assertThat(kapottReszletek, hasSize(0));
	}

	@Test
	public void egyElemuListaEgyelemetAdVissza() {
		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(newArrayList(
		                                                                               new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.03").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.12.03")
																		.veg("2012.12.03")
																		.munkanapokSzama(1);

		assertThat(kapottReszletek, contains(elvartReszletek));
	}

	@Test
	public void ketEgymasMellettiElemOsszevonodikHaAKetNapKetEgymastKovetkoHetkoznapok() {
		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.03").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.04").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.12.03")
																		.veg("2012.12.04")
																		.munkanapokSzama(2);

		assertThat(kapottReszletek, contains(elvartReszletek));
	}

	@Test
	public void ketEgymasMellettiElemOsszevonodikHaAKetNapKozottHetvegeVan() {
		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.07").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.10").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.12.07")
																		.veg("2012.12.10")
																		.munkanapokSzama(2);

		assertThat(kapottReszletek, contains(elvartReszletek));
	}

	@Test
	public void csutortokEsPentekOsszevonodikDeAHetvegeNemAdodikHozza() {
		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.06").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.07").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.12.06")
																		.veg("2012.12.07")
																		.munkanapokSzama(2);

		assertThat(kapottReszletek, contains(elvartReszletek));
	}

	@Test
	public void hetfotolPentekigOsszevonodikDeAHetvegeNemAdodikHozza() {
		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.03").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.04").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.05").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.06").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.07").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.12.03")
																		.veg("2012.12.07")
																		.munkanapokSzama(5);

		assertThat(kapottReszletek, contains(elvartReszletek));
	}

	@Test
	public void pentekEsKovetkezoHetKedd() {
		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.07").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.11").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek1 = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.12.07")
																		.veg("2012.12.07")
																		.munkanapokSzama(1);

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek2 = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.12.11")
																		.veg("2012.12.11")
																		.munkanapokSzama(1);

		assertThat(kapottReszletek, contains(asList(elvartReszletek1, elvartReszletek2)));
	}

	@Test
	public void pentekEsKovetkezoHetKeddEsAHetvegeNormalisAHetfoPedigPihenonap() {
		KivetelnapokHelper kivetelnapok = new KivetelnapokHelper(newArrayList(new KivetelnapBuilder()
																		.datum("2012.12.10")
																		.tipus(PIHENONAP)
																		.letrehoz()));

		konvertalo = new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(kivetelnapok);

		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.07").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.11").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.12.07")
																		.veg("2012.12.11")
																		.munkanapokSzama(2);

		assertThat(kapottReszletek, contains(elvartReszletek));
	}

	@Test
	public void bugfix() {
		KivetelnapokHelper kivetelnapok = new KivetelnapokHelper(newArrayList(new KivetelnapBuilder()
																		.datum("2012.03.16")
																		.tipus(PIHENONAP)
																		.letrehoz()));

		konvertalo = new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(kivetelnapok);

		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.01").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.02").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.05").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.06").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.07").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.08").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.15").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.19").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.20").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek1 = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.03.01")
																		.veg("2012.03.08")
																		.munkanapokSzama(6);

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek2 = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.03.15")
																		.veg("2012.03.20")
																		.munkanapokSzama(3);

		assertThat(kapottReszletek, contains(asList(elvartReszletek1, elvartReszletek2)));
	}

	@Test
	public void vasarnapMunkanappaTeveEsErreSzabadsagKiveve() {
		KivetelnapokHelper kivetelnapok = new KivetelnapokHelper(newArrayList(new KivetelnapBuilder()
																		.datum("2012.12.23")
																		.tipus(MUNKANAP)
																		.letrehoz()));

		konvertalo = new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(kivetelnapok);

		List<SzabadsagFelhasznalasResponseDTO> kapottReszletek = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.12.23").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.12.23")
																		.veg("2012.12.23")
																		.munkanapokSzama(1);

		assertThat(kapottReszletek, contains(asList(elvartReszletek)));
	}

	@Test
	public void bugfix2() {
		List<SzabadsagFelhasznalasResponseDTO> eredmeny = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.15").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.16").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.17").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.18").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.21").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.22").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.23").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.28").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.29").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.30").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2013.01.31").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek1 = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2013.01.15")
																		.veg("2013.01.23")
																		.munkanapokSzama(7);

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek2 = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2013.01.28")
																		.veg("2013.01.31")
																		.munkanapokSzama(4);

		assertThat(eredmeny, contains(asList(elvartReszletek1, elvartReszletek2)));
	}



	@Test
	public void bugfix3() {
		KivetelnapokHelper kivetelnapok = new KivetelnapokHelper(newArrayList(new KivetelnapBuilder()
																		.datum("2012.03.16")
																		.tipus(PIHENONAP)
																		.letrehoz(),
																new KivetelnapBuilder()
																		.datum("2012.03.24")
																		.tipus(MUNKANAP)
																		.letrehoz())
																		);

		konvertalo = new SzabadsagokatOsszefuggoFelhasznaltSzabadnapReszletekkeKonvertalo(kivetelnapok);

		List<SzabadsagFelhasznalasResponseDTO> eredmeny = konvertalo.konvertal(newArrayList(
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.01").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.02").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.05").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.06").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.07").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.08").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.09").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.12").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.13").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.14").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.15").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.19").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.20").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.21").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.22").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.23").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.24").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.26").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.27").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.28").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.29").letrehoz(),
                                                                                        new SzabadsagBuilder().szemelyitorzs(new SzemelyitorzsBuilder().tsz(1).letrehoz()).nap("2012.03.30").letrehoz()));

		SzabadsagFelhasznalasResponseDTOMatcher elvartReszletek = new SzabadsagFelhasznalasResponseDTOMatcher()
																		.tsz(1)
																		.kezdet("2012.03.01")
																		.veg("2012.03.30")
																		.munkanapokSzama(22);

		assertThat(eredmeny, contains(elvartReszletek));
	}
}
