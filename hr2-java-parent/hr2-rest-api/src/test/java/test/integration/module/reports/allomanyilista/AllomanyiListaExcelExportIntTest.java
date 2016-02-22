package test.integration.module.reports.allomanyilista;

import static hu.interconnect.util.FileUtils.getClasspathInputStream;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import hu.interconnect.excel.Excel;
import hu.interconnect.excel.ExcelOsszeshasonlito;
import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatCreateDTO;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;
import test.builder.ErtekkeszletElemCreateDTOBuilder;
import test.builder.MunkakoriHistorikusAdatCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.MunkakoriBesorolasDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class AllomanyiListaExcelExportIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();
		
		valuesetsRestController.create(new ErtekkeszletElemCreateDTOBuilder()
											.tipus(ErtekkeszletElemTipus.KOLTSEGHELY)
											.megnevezesMagyar("Értékesítés")
											.letrehoz());
		
		valuesetsRestController.create(new ErtekkeszletElemCreateDTOBuilder()
											.tipus(ErtekkeszletElemTipus.MUNKAKOR)
											.megnevezesMagyar("Kiszervezett")
											.letrehoz());
		
		valuesetsRestController.create(new ErtekkeszletElemCreateDTOBuilder()
											.tipus(ErtekkeszletElemTipus.FEOR)
											.megnevezesMagyar("Kiszervezett")
											.letrehoz());
		
		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
												.tsz(2)
												.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																			.vezeteknev("Papp")
																			.keresztnev("Krisztina")
																			.letrehoz())
												.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																			.jogviszonyKezdete("2012.01.02")
																			.jogviszonyVege("2013.01.06")
																			.allomanymod(Allomanymod.AKTIV)
																			.letrehoz())
												.munkakoriBesorolas(new MunkakoriBesorolasDTOBuilder()
																			.koltseghely(getErtekkeszletElemId(ErtekkeszletElemTipus.KOLTSEGHELY, "Értékesítés"))
																			.munkakor(getErtekkeszletElemId(ErtekkeszletElemTipus.MUNKAKOR, "Kiszervezett"))
																			.letrehoz())
												.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
		
		MunkakoriHistorikusAdatCreateDTO munkakoriHistorikusAdat = new MunkakoriHistorikusAdatCreateDTOBuilder()
																.tsz(szemelyitorzs.getTsz())
																.datum("2012.01.02")
																.fEOR(getErtekkeszletElemId(ErtekkeszletElemTipus.FEOR, "Kiszervezett"))
																.fizetes(123000)
																.letrehoz();
		
		jobhistoryRestController.create(munkakoriHistorikusAdat);
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getAllomanyiLista();
		
		MapMatcher<String, String> elvartSor1 = new MapMatcher<String, String>()
										.adottKulcsErtekParral("nev", "Papp Krisztina")
										.adottKulcsErtekParral("jogviszony_kezdete", "2012.01.02")
										.adottKulcsErtekParral("jogviszony_vege", "2013.01.06")
										.adottKulcsErtekParral("aktualis_havi_munkaber", "123000")
										.adottKulcsErtekParral("koltseghely", "Értékesítés")
										.adottKulcsErtekParral("munkakor", "Kiszervezett");
		
		MapMatcher<String, String> elvartSor2 = new MapMatcher<String, String>()
										.adottKulcsErtekParral("nev", "Idősebb Nagyguci Imre")
										.adottKulcsErtekParral("jogviszony_kezdete", "2008.11.09")
										.adottKulcsErtekParral("jogviszony_vege", "2018.10.22")
										.adottKulcsErtekParral("aktualis_havi_munkaber", "")
										.adottKulcsErtekParral("koltseghely", "HR - Munkaügy")
										.adottKulcsErtekParral("munkakor", "Személyi");
		
		
		assertThat(excel.getRows(), contains(asList(elvartSor1, elvartSor2)));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getAllomanyiLista();
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_allomanyi_lista.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
