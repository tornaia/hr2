package hu.interconnect.hr.module.reports;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.excel.Excel.DEFAULT_FEJLEC_HATTERSZIN;
import static hu.interconnect.excel.ExcelFejlecMezo.DEFAULT_KESKENY_OSZLOP_SZELESSEG;
import static hu.interconnect.util.DateUtils.napFormaz;
import static hu.interconnect.util.FileUtils.getClasspathByteArray;
import static hu.interconnect.util.StringUtils.NEW_LINE;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import hu.interconnect.excel.Excel;
import hu.interconnect.excel.ExcelFejlecMezo;
import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.module.exceptiondays.KivetelnapokHelper;
import hu.interconnect.util.DateIterator;

public enum Riport {
	
	ORVOSI_VIZSGALAT_LEJARAT_SABLON("orvosi_vizsgalat_lejarat_sablon.xls"),
	IGAZOLVANYOK_ES_KEPZETTSEGEK_LEJARAT_SABLON("igazolvanyok_es_kepzettsegek_lejarat_sablon.xls"),
	PROBAIDO_VEGE_SABLON("probaido_vege_sablon.xls"),
	NEVSOR_SABLON("nevsor_sablon.xls"),
	ALLOMANYI_LISTA_SABLON("allomanyi_lista_sablon.xls"),
	EMAIL_CIMEK_SABLON("email_cimek_sablon.xls"),
	ALAPADATOK_SABLON("alapadatok_sablon.xls"),
	SZABADSAG_SABLON("szabadsag_sablon.xls"),
	BE_ES_KILEPOK("be_es_kilepok_sablon.xls"),
	KIGYUJTESI_LISTA_SABLON("kigyujtesi_lista_sablon.xls"),
	ATVETT_ESZKOZOK_SABLON("atvett_eszkozok_sablon.xls"),
	HAVI_SZAMFEJTESI_ADATOK_SABLON("havi_szamfejtesi_adatok_sablon.xls") {
		@Override
		public Excel getExcelSablon(Object... params) {
			// Sablon dinamikus generalasa
			List<ExcelFejlecMezo> sablonFejlecMezok = newArrayList();
			sablonFejlecMezok.add(new ExcelFejlecMezo("Törzsszám", "torzsszam", DEFAULT_KESKENY_OSZLOP_SZELESSEG));
			sablonFejlecMezok.add(new ExcelFejlecMezo("Név", "nev"));
			sablonFejlecMezok.add(new ExcelFejlecMezo("Jelenléti ív leadva", "jelenleti_iv_leadva", DEFAULT_KESKENY_OSZLOP_SZELESSEG));
			sablonFejlecMezok.add(new ExcelFejlecMezo("Km hozzájárulás", "km_hozzajarulas", DEFAULT_KESKENY_OSZLOP_SZELESSEG));
			sablonFejlecMezok.add(new ExcelFejlecMezo("Hétközi túlóra (150%)" + NEW_LINE + "8 órán felül", "to50", DEFAULT_KESKENY_OSZLOP_SZELESSEG));
			sablonFejlecMezok.add(new ExcelFejlecMezo("Hétvégi túlóra (200%)" + NEW_LINE + "szombat-vasárnap-ünnepnap", "to100", DEFAULT_KESKENY_OSZLOP_SZELESSEG));
			sablonFejlecMezok.add(new ExcelFejlecMezo("Éjszakás pótlék (30%)" + NEW_LINE + "műszakosok 22-06 között", "m30", DEFAULT_KESKENY_OSZLOP_SZELESSEG));
			
			DateIterator napIterator = DateIterator.daysOfMonthIterator((Date) params[0]);
			KivetelnapokHelper kivetelnapok = (KivetelnapokHelper) params[1];
			for (Date nap : napIterator) {
				String napStr = napFormaz(nap);
				Short cellaHatterszin = kivetelnapok.isPihenonap(nap) ? DEFAULT_FEJLEC_HATTERSZIN : null;
				sablonFejlecMezok.add(new ExcelFejlecMezo(napStr, "ledolgozott_ora_" + napStr, DEFAULT_KESKENY_OSZLOP_SZELESSEG, cellaHatterszin));
			}
			
			return new Excel(sablonFejlecMezok);
		}
	};
	
	private static final String EXCEL_SABLONOK_UTVONAL = "/excel/";
	
	private String sablonEleresiUtvonal;
	
	Riport(String sablonFileNev) {
		this.sablonEleresiUtvonal = EXCEL_SABLONOK_UTVONAL + sablonFileNev;
	}
	
	public Excel getExcelSablon(Object... params) {
		if (params.length > 0) {
			throw new ProgramozasiHiba("Kaptam parametereket de nem hasznalom semmire: " + Arrays.toString(params));
		}
		return new Excel(getClasspathByteArray(sablonEleresiUtvonal));
	}
}
