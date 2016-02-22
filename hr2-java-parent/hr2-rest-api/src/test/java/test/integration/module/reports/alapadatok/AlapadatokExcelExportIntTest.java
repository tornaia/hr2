package test.integration.module.reports.alapadatok;

import static hu.interconnect.util.FileUtils.getClasspathInputStream;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import hu.interconnect.excel.Excel;
import hu.interconnect.excel.ExcelOsszeshasonlito;
import hu.interconnect.hr.backend.api.dto.SzemelyitorzsCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;
import hu.interconnect.hr.backend.api.enumeration.KozteruletTipus;
import hu.interconnect.hr.backend.api.enumeration.LakcimAktualis;
import hu.interconnect.hr.backend.api.enumeration.Nem;
import hu.interconnect.hr.backend.api.enumeration.RiportAllomanymod;
import test.builder.ErtekkeszletElemCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder.LakcimDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class AlapadatokExcelExportIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();
		
		valuesetsRestController.create(new ErtekkeszletElemCreateDTOBuilder()
											.tipus(ErtekkeszletElemTipus.ALLAMPOLGARSAG)
											.megnevezesMagyar("Svéd")
											.megnevezesAngol("Swedish")
											.letrehoz());
		
		SzemelyitorzsCreateDTO szemelyitorzs = new SzemelyitorzsCreateDTOBuilder()
												.tsz(2)
												.szemelyiAdatok(new SzemelyiAdatokDTOBuilder()
																			.vezeteknev("Kovácsné Papp")
																			.keresztnev("Krisztina")
																			.nem(Nem.NO)
																			.allampolgarsag(getErtekkeszletElemId(ErtekkeszletElemTipus.ALLAMPOLGARSAG, "Svéd"))
																			.szuletesiDatum("1984.08.11")
																			.szuletesiHely("Sopron")
																			.szuletesiOrszag("Magyarország")
																			.szuletesiNev("Papp Krisztina")
																			.szuletesiNevAnyja("Papp Margit")
																			.adoazonositoJel("8430375319")
																			.taj("040665522")
																			.szemelyiIgazolvanySzam("123456789")
																			.szemelyiIgazolvanySzamLejarat("2020.04.22")
																			.utlevelSzam("9876543210")
																			.utlevelSzamLejarat("2022.02.11")
																			.telefon("(+36) 1 432-1258")
																			.mobil("+36/20 453-6699")
																			.email("papp.krisztina@gmail.com")
																			.lakcimAktualis(LakcimAktualis.IDEIGLENES)
																			.lakcimAllando(new LakcimDTOBuilder()
																										.iranyitoszam("9700")
																										.telepules("Szombathely")
																										.kerulet("-")
																										.kozteruletNev("Szily János")
																										.kozteruletTipus(KozteruletTipus.UTCA)
																										.kozteruletSzam("22")
																										.epulet("-")
																										.lepcsohaz("A")
																										.emelet("2")
																										.ajto("3")
																										.letrehoz()
																			                       )
													                       .lakcimIdeiglenes(new LakcimDTOBuilder()
																										.iranyitoszam("1124")
																										.telepules("Budapest")
																										.kerulet("XII.")
																										.kozteruletNev("Hegyalja")
																										.kozteruletTipus(KozteruletTipus.UT)
																										.kozteruletSzam("66-82")
																										.epulet("B")
																										.lepcsohaz("3")
																										.emelet("4")
																										.ajto("301")
																										.letrehoz()
																			                       )
																			.letrehoz())
												.jogviszonyAdatok(new JogviszonyAdatokDTOBuilder()
																			.allomanymod(Allomanymod.GYESGYED)
																			.letrehoz())
												.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getAlapadatok(RiportAllomanymod.GYESGYED);
		
		MapMatcher<String, String> elvartSor = new MapMatcher<String, String>()
									.adottKulcsErtekParral("tsz", "2")
									.adottKulcsErtekParral("vezeteknev", "Kovácsné Papp")
									.adottKulcsErtekParral("keresztnev", "Krisztina")
									.adottKulcsErtekParral("nem", "Nő")
									.adottKulcsErtekParral("allampolgarsag", "Svéd")
									.adottKulcsErtekParral("szuletesi_datum", "1984.08.11")
									.adottKulcsErtekParral("szuletesi_hely", "Sopron")
									.adottKulcsErtekParral("szuletesi_orszag", "Magyarország")
									.adottKulcsErtekParral("szuletesi_nev", "Papp Krisztina")
									.adottKulcsErtekParral("szuletesi_nev_anyja", "Papp Margit")
									.adottKulcsErtekParral("adoazonosito_jel", "8430375319")
									.adottKulcsErtekParral("taj", "040665522")
									.adottKulcsErtekParral("szemelyi_igazolvany_szam", "123456789")
									.adottKulcsErtekParral("szemelyi_igazolvany_szam_lejarat", "2020.04.22")
									.adottKulcsErtekParral("utlevel_szam", "9876543210")
									.adottKulcsErtekParral("utlevel_szam_lejarat", "2022.02.11")
									.adottKulcsErtekParral("telefon", "(+36) 1 432-1258")
									.adottKulcsErtekParral("mobil", "+36/20 453-6699")
									.adottKulcsErtekParral("email", "papp.krisztina@gmail.com")
									.adottKulcsErtekParral("aktualis_lakcim", "Ideiglenes")
									.adottKulcsErtekParral("allando_lakcim_iranyitoszam", "9700")
									.adottKulcsErtekParral("allando_lakcim_telepules", "Szombathely")
									.adottKulcsErtekParral("allando_lakcim_kerulet", "-")
									.adottKulcsErtekParral("allando_lakcim_kozterulet_nev", "Szily János")
									.adottKulcsErtekParral("allando_lakcim_kozterulet_tipus", "utca")
									.adottKulcsErtekParral("allando_lakcim_kozterulet_szam", "22")
									.adottKulcsErtekParral("allando_lakcim_epulet", "-")
									.adottKulcsErtekParral("allando_lakcim_lepcsohaz", "A")
									.adottKulcsErtekParral("allando_lakcim_emelet", "2")
									.adottKulcsErtekParral("allando_lakcim_ajto", "3")
									.adottKulcsErtekParral("ideiglenes_lakcim_iranyitoszam", "1124")
									.adottKulcsErtekParral("ideiglenes_lakcim_telepules", "Budapest")
									.adottKulcsErtekParral("ideiglenes_lakcim_kerulet", "XII.")
									.adottKulcsErtekParral("ideiglenes_lakcim_kozterulet_nev", "Hegyalja")
									.adottKulcsErtekParral("ideiglenes_lakcim_kozterulet_tipus", "út")
									.adottKulcsErtekParral("ideiglenes_lakcim_kozterulet_szam", "66-82")
									.adottKulcsErtekParral("ideiglenes_lakcim_epulet", "B")
									.adottKulcsErtekParral("ideiglenes_lakcim_lepcsohaz", "3")
									.adottKulcsErtekParral("ideiglenes_lakcim_emelet", "4")
									.adottKulcsErtekParral("ideiglenes_lakcim_ajto", "301");
		
		assertThat(excel.getRows(), contains(elvartSor));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getAlapadatok(RiportAllomanymod.GYESGYED);
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_alapadatok.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
