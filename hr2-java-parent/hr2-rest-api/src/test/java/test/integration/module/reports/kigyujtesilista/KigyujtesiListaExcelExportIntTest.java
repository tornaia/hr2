package test.integration.module.reports.kigyujtesilista;

import static hu.interconnect.hr.backend.api.enumeration.RiportAllomanymod.AKTIV;
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
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;
import hu.interconnect.hr.backend.api.enumeration.KozteruletTipus;
import hu.interconnect.hr.backend.api.enumeration.LakcimAktualis;
import hu.interconnect.hr.backend.api.enumeration.Nem;
import test.builder.ErtekkeszletElemCreateDTOBuilder;
import test.builder.KepzettsegCreateDTOBuilder;
import test.builder.MunkakoriHistorikusAdatCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.JogviszonyAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder;
import test.builder.SzemelyitorzsCreateDTOBuilder.SzemelyiAdatokDTOBuilder.LakcimDTOBuilder;
import test.integration.AbstractRestAPIIntTest;
import test.matcher.MapMatcher;

public class KigyujtesiListaExcelExportIntTest extends AbstractRestAPIIntTest {

	@Before
	public void adatokkalFeltolt() {
		adminBejelentkezik();
		
		valuesetsRestController.create(new ErtekkeszletElemCreateDTOBuilder()
											.tipus(ErtekkeszletElemTipus.ALLAMPOLGARSAG)
											.megnevezesMagyar("Svéd")
											.megnevezesAngol("Swedish")
											.letrehoz());
		
		valuesetsRestController.create(new ErtekkeszletElemCreateDTOBuilder()
											.tipus(ErtekkeszletElemTipus.FEOR)
											.megnevezesMagyar("Sofőr")
											.megnevezesAngol("Driver")
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
																			.allomanymod(Allomanymod.AKTIV)
																			.letrehoz())
												.letrehoz();
		
		personaldataRestController.create(szemelyitorzs);
		
		qualificationsRestController.create(new KepzettsegCreateDTOBuilder()
											.tsz(szemelyitorzs.getTsz())
											.tipus("Típus1")
											.megnevezes("Megnevezés1")
											.modFok("Mód/Fok1")
											.ev("2011")
											.ervenyessegVeg("2013.02.22")
											.megjegyzes("Megjegyzés1")
											.letrehoz());
		
		qualificationsRestController.create(new KepzettsegCreateDTOBuilder()
											.tsz(szemelyitorzs.getTsz())
											.tipus("Típus2")
											.megnevezes("Megnevezés2")
											.modFok("Mód/Fok2")
											.ev("1969")
											.ervenyessegVeg("2015.10.11")
											.megjegyzes("Megjegyzés2")
											.letrehoz());
		
		jobhistoryRestController.create(new MunkakoriHistorikusAdatCreateDTOBuilder()
															.tsz(szemelyitorzs.getTsz())
															.datum("2010.06.15")
															.fEOR(getErtekkeszletElemId(ErtekkeszletElemTipus.FEOR, "Sofőr"))
															.fizetes(105000)
															.letrehoz());
		
		jobhistoryRestController.create(new MunkakoriHistorikusAdatCreateDTOBuilder()
															.tsz(szemelyitorzs.getTsz())
															.datum("2012.01.01")
															.fEOR(getErtekkeszletElemId(ErtekkeszletElemTipus.FEOR, "Sofőr"))
															.fizetes(125000)
															.letrehoz());
		
		jobhistoryRestController.create(new MunkakoriHistorikusAdatCreateDTOBuilder()
															.tsz(szemelyitorzs.getTsz())
															.datum("2013.03.01")
															.fEOR(getErtekkeszletElemId(ErtekkeszletElemTipus.FEOR, "Sofőr"))
															.fizetes(188000)
															.letrehoz());
	}
	
	@Test
	public void exportalaskorAVartSorokKerulSzuresre() throws IOException {
		Excel excel = reportsView.getKigyujtesiLista(AKTIV);
		
		MapMatcher<String, String> elvartSor1 = new MapMatcher<String, String>()
									.adottKulcsErtekParral("tsz", "9999")
									.adottKulcsErtekParral("vezeteknev", "Idősebb Nagyguci")
									.adottKulcsErtekParral("keresztnev", "Imre")
									.adottKulcsErtekParral("nem", "Férfi")
									.adottKulcsErtekParral("allampolgarsag", "Magyar")
									.adottKulcsErtekParral("szuletesi_datum", "1921.01.01")
									.adottKulcsErtekParral("szuletesi_hely", "Mucsaröcsöge")
									.adottKulcsErtekParral("szuletesi_orszag", "Magyarország")
									.adottKulcsErtekParral("szuletesi_nev", "Születési név1")
									.adottKulcsErtekParral("szuletesi_nev_anyja", "Anyja születési neve1")
									.adottKulcsErtekParral("adoazonosito_jel", "adójel1")
									.adottKulcsErtekParral("taj", "taj1")
									.adottKulcsErtekParral("szemelyi_igazolvany_szam", "személyiszám1")
									.adottKulcsErtekParral("szemelyi_igazolvany_szam_lejarat", "2012.12.31")
									.adottKulcsErtekParral("utlevel_szam", "útlevélszám1")
									.adottKulcsErtekParral("utlevel_szam_lejarat", "2015.04.15")
									.adottKulcsErtekParral("telefon", "")
									.adottKulcsErtekParral("mobil", "")
									.adottKulcsErtekParral("email", "")
									.adottKulcsErtekParral("aktualis_lakcim", "")
									.adottKulcsErtekParral("allando_lakcim_iranyitoszam", "")
									.adottKulcsErtekParral("allando_lakcim_telepules", "")
									.adottKulcsErtekParral("allando_lakcim_kerulet", "")
									.adottKulcsErtekParral("allando_lakcim_kozterulet_nev", "")
									.adottKulcsErtekParral("allando_lakcim_kozterulet_tipus", "")
									.adottKulcsErtekParral("allando_lakcim_kozterulet_szam", "")
									.adottKulcsErtekParral("allando_lakcim_epulet", "")
									.adottKulcsErtekParral("allando_lakcim_lepcsohaz", "")
									.adottKulcsErtekParral("allando_lakcim_emelet", "")
									.adottKulcsErtekParral("allando_lakcim_ajto", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_iranyitoszam", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_telepules", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_kerulet", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_kozterulet_nev", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_kozterulet_tipus", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_kozterulet_szam", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_epulet", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_lepcsohaz", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_emelet", "")
									.adottKulcsErtekParral("ideiglenes_lakcim_ajto", "")
									.adottKulcsErtekParral("kepzettseg_tipus", "")
									.adottKulcsErtekParral("kepzettseg_megnevezes", "")
									.adottKulcsErtekParral("kepzettseg_mod_fok", "")
									.adottKulcsErtekParral("kepzettseg_ev", "")
									.adottKulcsErtekParral("kepzettseg_ervenyesseg_vege", "")
									.adottKulcsErtekParral("kepzettseg_megjegyzes", "")
									.adottKulcsErtekParral("fizetesi_adat_datum", "")
									.adottKulcsErtekParral("fizetesi_adat_feor", "")
									.adottKulcsErtekParral("fizetesi_adat_fizetes", "");
		
		MapMatcher<String, String> elvartSor2 = new MapMatcher<String, String>()
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
									.adottKulcsErtekParral("ideiglenes_lakcim_ajto", "301")
									.adottKulcsErtekParral("kepzettseg_tipus", "Típus2")
									.adottKulcsErtekParral("kepzettseg_megnevezes", "Megnevezés2")
									.adottKulcsErtekParral("kepzettseg_mod_fok", "Mód/Fok2")
									.adottKulcsErtekParral("kepzettseg_ev", "1969")
									.adottKulcsErtekParral("kepzettseg_ervenyesseg_vege", "2015.10.11")
									.adottKulcsErtekParral("kepzettseg_megjegyzes", "Megjegyzés2")
									.adottKulcsErtekParral("fizetesi_adat_datum", "2010.06.15")
									.adottKulcsErtekParral("fizetesi_adat_feor", "Sofőr")
									.adottKulcsErtekParral("fizetesi_adat_fizetes", "105000");
		
		MapMatcher<String, String> elvartSor3 = new MapMatcher<String, String>()
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
									.adottKulcsErtekParral("ideiglenes_lakcim_ajto", "301")
									.adottKulcsErtekParral("kepzettseg_tipus", "Típus1")
									.adottKulcsErtekParral("kepzettseg_megnevezes", "Megnevezés1")
									.adottKulcsErtekParral("kepzettseg_mod_fok", "Mód/Fok1")
									.adottKulcsErtekParral("kepzettseg_ev", "2011")
									.adottKulcsErtekParral("kepzettseg_ervenyesseg_vege", "2013.02.22")
									.adottKulcsErtekParral("kepzettseg_megjegyzes", "Megjegyzés1")
									.adottKulcsErtekParral("fizetesi_adat_datum", "2012.01.01")
									.adottKulcsErtekParral("fizetesi_adat_feor", "Sofőr")
									.adottKulcsErtekParral("fizetesi_adat_fizetes", "125000");
		
		MapMatcher<String, String> elvartSor4 = new MapMatcher<String, String>()
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
									.adottKulcsErtekParral("ideiglenes_lakcim_ajto", "301")
									.adottKulcsErtekParral("kepzettseg_tipus", "")
									.adottKulcsErtekParral("kepzettseg_megnevezes", "")
									.adottKulcsErtekParral("kepzettseg_mod_fok", "")
									.adottKulcsErtekParral("kepzettseg_ev", "")
									.adottKulcsErtekParral("kepzettseg_ervenyesseg_vege", "")
									.adottKulcsErtekParral("kepzettseg_megjegyzes", "")
									.adottKulcsErtekParral("fizetesi_adat_datum", "2013.03.01")
									.adottKulcsErtekParral("fizetesi_adat_feor", "Sofőr")
									.adottKulcsErtekParral("fizetesi_adat_fizetes", "188000");
		
		assertThat(excel.getRows(), contains(asList(elvartSor1, elvartSor2, elvartSor3, elvartSor4)));
	}
	
	@Test
	public void exportalaskorAVartExceltKapom() throws IOException {
		Excel actual = reportsView.getKigyujtesiLista(AKTIV);
		Excel expected = new Excel(getClasspathInputStream("/riportok/elvart_kigyujtesi_lista.xls"));
		ExcelOsszeshasonlito.compareExcelFiles(expected, actual);
	}
}
