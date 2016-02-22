package test.integration.module.reports.igazolvanyokeskepzettsegeklejarat;

import static hu.interconnect.util.FileUtils.getClasspathInputStream;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import hu.interconnect.excel.Excel;
import hu.interconnect.excel.ExcelOsszeshasonlito;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import test.builder.KepzettsegCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class IgazolvanyokEsKepzettsegekLejaratExcelExportIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();

		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
											.tsz(1)
											.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																		.vezeteknev("Papp")
																		.keresztnev("Krisztina")
																		.szemelyiIgazolvanySzam("478892KA")
																		.szemelyiIgazolvanySzamLejarat("2018.08.30")
																		.letrehoz())
											.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																		.allomanymod(Allomanymod.AKTIV)
																		.letrehoz())
											.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);

		qualificationsRestController.create(new KepzettsegCreateDTOBuilder()
											.tsz(szemelyitorzs.getTsz())
											.megnevezes("Jogosítvány - A")
											.ev("2008")
											.ervenyessegVeg("2018.12.18")
											.letrehoz());
		
		qualificationsRestController.create(new KepzettsegCreateDTOBuilder()
											.tsz(szemelyitorzs.getTsz())
											.megnevezes("Jogosítvány - B")
											.ev("2008")
											.ervenyessegVeg("2018.12.29")
											.letrehoz());
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getIgazolvanyokEsKepzettsegekLejarat("2018.07.01", "2019.06.30");
		
		MapMatcher<String, String> elvartElsoSor = new MapMatcher<String, String>()
										.adottKulcsErtekParral("nev", "Papp Krisztina")
										.adottKulcsErtekParral("megnevezes", "személyi igazolvány")
										.adottKulcsErtekParral("ervenyesseg_vege", "2018.08.30");

		MapMatcher<String, String> elvartMasodikSor= new MapMatcher<String, String>()
										.adottKulcsErtekParral("nev", "Papp Krisztina")
										.adottKulcsErtekParral("megnevezes", "Jogosítvány - A")
										.adottKulcsErtekParral("ervenyesseg_vege", "2018.12.18");
		
		MapMatcher<String, String> elvartHarmadikSor = new MapMatcher<String, String>()
										.adottKulcsErtekParral("nev", "Papp Krisztina")
										.adottKulcsErtekParral("megnevezes", "Jogosítvány - B")
										.adottKulcsErtekParral("ervenyesseg_vege", "2018.12.29");
		
		assertThat(excel.getRows(), contains(asList(elvartElsoSor, elvartMasodikSor, elvartHarmadikSor)));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getIgazolvanyokEsKepzettsegekLejarat("2018.07.01", "2019.06.30");
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_igazolvanyok_es_kepzettsegek_lejarat.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
