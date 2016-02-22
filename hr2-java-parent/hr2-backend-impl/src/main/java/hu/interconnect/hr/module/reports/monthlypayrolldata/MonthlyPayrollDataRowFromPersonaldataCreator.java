package hu.interconnect.hr.module.reports.monthlypayrolldata;

import static hu.interconnect.util.DateUtils.getHoElsoNapja;
import static hu.interconnect.util.DateUtils.getHoUtolsoNapja;
import static hu.interconnect.util.DateUtils.getNapokSzamaEvbenEsHonapban;
import static hu.interconnect.util.DateUtils.getNullaIdopillanat;
import static hu.interconnect.util.DateUtils.napFormaz;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus;
import hu.interconnect.hr.dao.JelenletiAdatDAO;
import hu.interconnect.hr.domain.JelenletiAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.reports.NullValueToEmptyStringMap;
import hu.interconnect.util.DateIterator;
import hu.interconnect.util.Localizer;

public class MonthlyPayrollDataRowFromPersonaldataCreator implements Function<Szemelyitorzs, Map<String, Object>> {

	private JelenletiAdatDAO jelenletiAdatDAO;
	
	private Date honap;
	
	public MonthlyPayrollDataRowFromPersonaldataCreator(JelenletiAdatDAO jelenletiAdatDAO, Date honap) {
		this.jelenletiAdatDAO = jelenletiAdatDAO;
		this.honap = honap;
	}

	@Override
	public Map<String, Object> apply(Szemelyitorzs szemelyitorzs) {
		NullValueToEmptyStringMap sor = new NullValueToEmptyStringMap();
		sor.put("torzsszam", szemelyitorzs.getTsz());
		
		sor.put("nev", szemelyitorzs.getTeljesNev());
		sor.putEmpty("jelenleti_iv_leadva");
		sor.putEmpty("km_hozzajarulas");
		
		List<JelenletiAdat> haviJelenletiAdatok = jelenletiAdatDAO.findBySzemelyitorzsEsHonap(szemelyitorzs.getTsz(), honap);

		Date to50 = getNullaIdopillanat();
		Date to100 = getNullaIdopillanat();
		Date m30 = getNullaIdopillanat();
		
		int kapottJelenletiAdatokSzama = haviJelenletiAdatok.size();
		int napokSzamaAzAdottHonapban = getNapokSzamaEvbenEsHonapban(honap);
		if (kapottJelenletiAdatokSzama != 0 && kapottJelenletiAdatokSzama != napokSzamaAzAdottHonapban) {
			throw new ProgramozasiHiba("Vagy 0 vagy pontosan annyi jelenletiAdatot varok, mint ahany nap van az adott honapban! Vártam: " + napokSzamaAzAdottHonapban + ", kaptam: " + kapottJelenletiAdatokSzama);
		}
		
		if (haviJelenletiAdatok.isEmpty()) {
			for (Date nap : new DateIterator(getHoElsoNapja(honap), getHoUtolsoNapja(honap))) {
				String napStr = napFormaz(nap);
				sor.put("ledolgozott_ora_" + napStr, "00:00");
			}
		} else {
			for (JelenletiAdat ja : haviJelenletiAdatok) {
				String napStr = napFormaz(ja.getDatum());
				if (ja.getTipus() == JelenletiAdatTipus.MUNKA || ja.getTipus() == JelenletiAdatTipus.SZABADNAP) {
					sor.putTime("ledolgozott_ora_" + napStr, ja.getLedolgozott() == null ? getNullaIdopillanat() : ja.getLedolgozott());
				} else {
					sor.put("ledolgozott_ora_" + napStr, Localizer.getMessage(ja.getTipus()));
				}
				
				if (ja.getTipus() == JelenletiAdatTipus.MUNKA) {
					to50.setTime(to50.getTime() + (ja.getTo50() != null ? (ja.getTo50().getTime() - getNullaIdopillanat().getTime()) : 0L));
					to100.setTime(to100.getTime() + (ja.getTo100() != null ? (ja.getTo100().getTime()- getNullaIdopillanat().getTime()) : 0L));
					m30.setTime(m30.getTime() + (ja.getM30() != null ? (ja.getM30().getTime()- getNullaIdopillanat().getTime()) : 0L));
				}
			}
		}
		
		sor.putTime("to50", to50);
		sor.putTime("to100", to100);
		sor.putTime("m30", m30);
		
		return sor.getMap();
	}
}
