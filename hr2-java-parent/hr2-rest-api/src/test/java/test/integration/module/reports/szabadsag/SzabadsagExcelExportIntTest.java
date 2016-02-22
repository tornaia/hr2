package test.integration.module.reports.szabadsag;

import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.BETEGSZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.TANULMANYI_SZABADSAG;
import static hu.interconnect.util.FileUtils.getClasspathInputStream;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import hu.interconnect.excel.Excel;
import hu.interconnect.excel.ExcelOsszeshasonlito;
import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import test.builder.EvesSzabadsagAdatCreateDTOBuilder;
import test.builder.SzabadsagFelhasznalasCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class SzabadsagExcelExportIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();

		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
												.tsz(2)
												.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																			.vezeteknev("Papp")
																			.keresztnev("Krisztina")
																			.letrehoz())
												.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																			.allomanymod(Allomanymod.AKTIV)
																			.letrehoz())
												.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
		
		vacationsRestController.create(new EvesSzabadsagAdatCreateDTOBuilder()
													.tsz(szemelyitorzs.getTsz())
													.ev(2013)
													.alapszabadsag(28)
													.gyermekekUtan(1)
													.fiatalkoru(2)
													.vakSzemely(3)
													.egyebMunkakor(4)
													.ktKaPotszabadsag(5)
													.ktKaVezetoi(6)
													.egyebCsokkento(7)
													.tanulmanyi(8)
													.multEviSzabadsag(9)
													.bszJarandosagAdottEvi(10)
													.letrehoz());
		
		SzabadsagFelhasznalasCreateDTO elozoDecemberiSzabadsagAmiAzEveMiattNemSzamitBeleAzIdeiRiportba = new SzabadsagFelhasznalasCreateDTOBuilder()
																						.tsz(szemelyitorzs.getTsz())
																						.kezdet("2012.12.03")
																						.veg("2012.12.04")
																						.jelleg(SZABADSAG)
																						.letrehoz();
														
		vacationsRestController.createConsumption(elozoDecemberiSzabadsagAmiAzEveMiattNemSzamitBeleAzIdeiRiportba);
		
		SzabadsagFelhasznalasCreateDTO januariSzabadsagAmiAHonapjaMiattCsakKozvetveSzamitBeleARiportba = new SzabadsagFelhasznalasCreateDTOBuilder()
																.tsz(szemelyitorzs.getTsz())
																.kezdet("2013.01.07")
																.veg("2013.01.11")
																.jelleg(SZABADSAG)
																.letrehoz();
		
		vacationsRestController.createConsumption(januariSzabadsagAmiAHonapjaMiattCsakKozvetveSzamitBeleARiportba);
		
		SzabadsagFelhasznalasCreateDTO januariBetegSzabadsagAmiAJellegeMiattKozvetveSemSzamitBeleARiportba = new SzabadsagFelhasznalasCreateDTOBuilder()
																.tsz(szemelyitorzs.getTsz())
																.kezdet("2013.01.29")
																.veg("2013.01.30")
																.jelleg(BETEGSZABADSAG)
																.letrehoz();
		
		vacationsRestController.createConsumption(januariBetegSzabadsagAmiAJellegeMiattKozvetveSemSzamitBeleARiportba);
		
		SzabadsagFelhasznalasCreateDTO februariTanulmanyiSzabadsagAmiTeljesenBeleszamitAzRiportba = new SzabadsagFelhasznalasCreateDTOBuilder()
																.tsz(szemelyitorzs.getTsz())
																.kezdet("2013.02.18")
																.veg("2013.02.21")
																.jelleg(TANULMANYI_SZABADSAG)
																.letrehoz();

		vacationsRestController.createConsumption(februariTanulmanyiSzabadsagAmiTeljesenBeleszamitAzRiportba);
		
		SzabadsagFelhasznalasCreateDTO marciusiTanulmanyiSzabadsagAmiAHonapjaMiattMarKozvetveSemSzamitBeleARiportba = new SzabadsagFelhasznalasCreateDTOBuilder()
																.tsz(szemelyitorzs.getTsz())
																.kezdet("2013.03.01")
																.veg("2013.03.04")
																.jelleg(TANULMANYI_SZABADSAG)
																.letrehoz();

		vacationsRestController.createConsumption(marciusiTanulmanyiSzabadsagAmiAHonapjaMiattMarKozvetveSemSzamitBeleARiportba);
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getSzabadsag("2013.02");
		
		MapMatcher<String, String> elsoSor = new MapMatcher<String, String>()
									.adottKulcsErtekParral("nev", "Papp Krisztina")
									.adottKulcsErtekParral("szabadsag_szabadsagok", "")
									.adottKulcsErtekParral("betegszabadsag_szabadsagok", "")
									.adottKulcsErtekParral("temetesi_szabadsag_szabadsagok", "")
									.adottKulcsErtekParral("apa_szabadsag_szabadsagok", "")
									.adottKulcsErtekParral("tanulmanyi_szabadsag_szabadsagok", "2013.02.18, 2013.02.19, 2013.02.20, 2013.02.21")
									.adottKulcsErtekParral("maradek_szabadsag_a_honap_vegen", "50");
		
		MapMatcher<String, String> masodikSor = new MapMatcher<String, String>()
									.adottKulcsErtekParral("nev", "Idősebb Nagyguci Imre")
									.adottKulcsErtekParral("szabadsag_szabadsagok", "")
									.adottKulcsErtekParral("betegszabadsag_szabadsagok", "")
									.adottKulcsErtekParral("temetesi_szabadsag_szabadsagok", "")
									.adottKulcsErtekParral("apa_szabadsag_szabadsagok", "")
									.adottKulcsErtekParral("tanulmanyi_szabadsag_szabadsagok", "")
									.adottKulcsErtekParral("maradek_szabadsag_a_honap_vegen", "0");
		
		assertThat(excel.getRows(), contains(asList(elsoSor, masodikSor)));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getSzabadsag("2013.02");
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_szabadsag.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
