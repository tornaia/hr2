package test.unit.hu.interconnect.hr.module.reports.detailedlistofpeopleplussalaryandqualification;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import hu.interconnect.hr.backend.api.enumeration.KozteruletTipus;
import hu.interconnect.hr.backend.api.enumeration.LakcimAktualis;
import hu.interconnect.hr.backend.api.enumeration.Nem;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.detailedlistofpeopleplussalaryandqualification.DetailedListOfPeoplePlusSalaryAndQualificationRowFromPersonaldataCreator;
import test.builder.LakcimBuilder;
import test.builder.MunkakoriBesorolasBuilder;
import test.builder.SzemelyiAdatokBuilder;
import test.builder.SzemelyitorzsBuilder;
import test.matcher.MapMatcher;
import test.unit.AbstractBackendUnitTest;

public class DetailedListOfPeoplePlusSalaryAndQualificationRowFromPersonaldataCreatorTest extends AbstractBackendUnitTest {

	private DetailedListOfPeoplePlusSalaryAndQualificationRowFromPersonaldataCreator eloallito = new DetailedListOfPeoplePlusSalaryAndQualificationRowFromPersonaldataCreator();
	
	@Test
	public void szuletesiDatumHaNincsKitoltveAkkorUresenJelenikMeg() {
		adminBejelentkezik();
		
		Szemelyitorzs szemelyitorzs = new SzemelyitorzsBuilder()
											.tsz(2)
											.szemelyiAdatok(new SzemelyiAdatokBuilder()
																		.vezeteknev("Kovácsné Papp")
																		.keresztnev("Krisztina")
																		.nem(Nem.NO)
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
																		.lakcimAllando(new LakcimBuilder()
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
												                       .lakcimIdeiglenes(new LakcimBuilder()
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
											.munkakoriBesorolas(new MunkakoriBesorolasBuilder()
																		.uzemanyagElszamolas(false)
																		.letrehoz())
											.letrehoz();
		
		List<Map<String, Object>> sor = eloallito.apply(szemelyitorzs);
		
		MapMatcher<String, Object> elvartSorMatcher = new MapMatcher<String, Object>()
											.adottKulcsErtekParral("tsz", 2)
											.adottKulcsErtekParral("vezeteknev", "Kovácsné Papp")
											.adottKulcsErtekParral("keresztnev", "Krisztina")
											.adottKulcsErtekParral("nem", "Nő")
											.adottKulcsErtekParral("allampolgarsag", "")
											.adottKulcsErtekParral("szuletesi_datum", "")
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
											.adottKulcsErtekParral("fizetesi_adat_datum", "")
											.adottKulcsErtekParral("fizetesi_adat_feor", "")
											.adottKulcsErtekParral("fizetesi_adat_fizetes", "");
		
		assertThat(sor, contains(elvartSorMatcher));
	}
}
