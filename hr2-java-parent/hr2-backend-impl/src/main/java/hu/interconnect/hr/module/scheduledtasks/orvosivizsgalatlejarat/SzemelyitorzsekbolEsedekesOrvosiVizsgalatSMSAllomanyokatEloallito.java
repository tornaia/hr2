package hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.util.DateUtils.aktualisIdo;
import static hu.interconnect.util.DateUtils.napFormaz;
import static hu.interconnect.util.StringUtils.NEW_LINE;
import static org.apache.commons.lang.StringUtils.leftPad;
import static org.apache.commons.lang.time.DateUtils.addMonths;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import hu.interconnect.hr.domain.OrvosiVizsgalat;
import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.scheduledtasks.KuldendoSMSAllomany;

public class SzemelyitorzsekbolEsedekesOrvosiVizsgalatSMSAllomanyokatEloallito {

	private static final Log LOG = LogFactory.getLog(SzemelyitorzsekbolEsedekesOrvosiVizsgalatSMSAllomanyokatEloallito.class);
	
	private static final int EGYEDI_POSTFIX_MINIMUM_HOSSZ = 6;

	private List<Szemelyitorzs> szemelyitorzsek;

	public SzemelyitorzsekbolEsedekesOrvosiVizsgalatSMSAllomanyokatEloallito(Iterable<Szemelyitorzs> szemelyitorzsek) {
		this.szemelyitorzsek = newArrayList(szemelyitorzsek);
	}

	public KuldendoSMSAllomanyok letrehoz() {
		KuldendoSMSAllomanyok kuldendoSMSAllomanyok = new KuldendoSMSAllomanyok();

		for (int i=0;i<szemelyitorzsek.size();++i) {
			Szemelyitorzs szemelyitorzs = szemelyitorzsek.get(i);
			SzemelyiAdatok szemelyiAdatok = szemelyitorzs.getSzemelyiAdatok();
			String mobil = szemelyiAdatok.getMobil();
			if (mobil == null || mobil.trim().isEmpty()) {
				LOG.warn("Egy szemelyitorzsnek kuldenek SMS-t de nincs mobil szama ezert nem kuldok neki! Tsz: " + szemelyitorzs.getTsz());
				continue;
			}

			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

			String uniquePostfix = leftPad(""+i, EGYEDI_POSTFIX_MINIMUM_HOSSZ, '0');
			String filnev = df.format(aktualisIdo()) + "_" + uniquePostfix;

			OrvosiVizsgalat orvosiVizsgalat = szemelyitorzs.getOrvosiVizsgalat();
			Date utolsoOrvosiVizsgalatIdopontja = orvosiVizsgalat.getUtolsoOrvosiVizsgalatIdopontja();
			int gyakorisag = orvosiVizsgalat.getGyakorisag();
			Date kovetkezoOrvosiVizsgalatIdopontja = addMonths(utolsoOrvosiVizsgalatIdopontja, gyakorisag);

			String tartalom = "To: " + mobil + NEW_LINE + NEW_LINE + "Kedves Kolléga! Az üzemorvosi vizsgálatod " + napFormaz(kovetkezoOrvosiVizsgalatIdopontja) + "-kor le fog járni. Időpont egyeztetésért keresd Gubicza Máriát a 06202519346-os számon. Üdv, HR osztály";

			LOG.info("SMS filenev: " + filnev + ", tartalom: " + tartalom + ", mobil: " + mobil);

			kuldendoSMSAllomanyok.add(new KuldendoSMSAllomany(filnev, tartalom));
		}
		return kuldendoSMSAllomanyok;
	}
}
