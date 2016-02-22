package hu.interconnect.hr.module.personaldata.timeandattendance;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus.MUNKA;
import static hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus.SZABADNAP;
import static hu.interconnect.util.DateUtils.getHoElsoNapja;
import static hu.interconnect.util.DateUtils.getHoUtolsoNapja;
import static hu.interconnect.util.DateUtils.parseOraPerc;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvResponseDTO;
import hu.interconnect.hr.backend.api.dto.JelenletiAdatDTO;
import hu.interconnect.hr.dao.HaviJelenletiIvAllapotDAO;
import hu.interconnect.hr.dao.JelenletiAdatDAO;
import hu.interconnect.hr.dao.KivetelnapDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.HaviJelenletiIvAllapot;
import hu.interconnect.hr.domain.JelenletiAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.exceptiondays.KivetelnapokHelper;
import hu.interconnect.util.DateIterator;

@Component
public class TimeandattendanceMonthlyDataGenerator {

	private static final Date DEFAULT_KEZDET = parseOraPerc("08:00");
	private static final Date DEFAULT_VEG = parseOraPerc("16:30");
	private static final Date DEFAULT_LEDOLGOZOTT = parseOraPerc("08:00");
	private static final Date DEFAULT_PIHENONAP_KITOLTETLEN = null;

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private JelenletiAdatDAO jelenletiAdatDAO;
	
	@Autowired
	private HaviJelenletiIvAllapotDAO haviJelenletiIvAllapotDAO;
	
	@Autowired
	private KivetelnapDAO kivetelnapDAO;
	
	public HaviJelenletiIvResponseDTO getHaviJelenletiIv(int tsz, Date honap) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findById(tsz);
		KivetelnapokHelper kivetelnapok = new KivetelnapokHelper(kivetelnapDAO.findAll());

		HaviJelenletiAdatokHelper haviJelenletiAdatok = new HaviJelenletiAdatokHelper(jelenletiAdatDAO.findBySzemelyitorzsEsHonap(tsz, honap));
		List<JelenletiAdatDTO> adatok = newArrayList();
		for (Date day : new DateIterator(getHoElsoNapja(honap), getHoUtolsoNapja(honap))) {
			boolean vanAdatErreANapra = haviJelenletiAdatok.containsJelenletiAdat(day);
			if (vanAdatErreANapra) {
				JelenletiAdat jelenletiAdat = haviJelenletiAdatok.getJelenletiAdat(day);
				adatok.add(toDTO(jelenletiAdat));
			} else {
				if (kivetelnapok.isPihenonap(day)) {
					JelenletiAdat pihenonapSor = new JelenletiAdat(szemelyitorzs, day, SZABADNAP, DEFAULT_PIHENONAP_KITOLTETLEN, DEFAULT_PIHENONAP_KITOLTETLEN, DEFAULT_PIHENONAP_KITOLTETLEN, DEFAULT_PIHENONAP_KITOLTETLEN, DEFAULT_PIHENONAP_KITOLTETLEN, DEFAULT_PIHENONAP_KITOLTETLEN);
					adatok.add(toDTO(pihenonapSor));
				} else {
					JelenletiAdat munkanapSor = new JelenletiAdat(szemelyitorzs, day, MUNKA, DEFAULT_KEZDET, DEFAULT_VEG, DEFAULT_LEDOLGOZOTT, DEFAULT_PIHENONAP_KITOLTETLEN, DEFAULT_PIHENONAP_KITOLTETLEN, DEFAULT_PIHENONAP_KITOLTETLEN);
					adatok.add(toDTO(munkanapSor));
				}
			}
		}
		
		Optional<HaviJelenletiIvAllapot> optionalAllapot = haviJelenletiIvAllapotDAO.findByHonap(honap);
		boolean szerkesztheto = !optionalAllapot.isPresent() ? true : optionalAllapot.get().isSzerkesztheto();
		
		return new HaviJelenletiIvResponseDTO(tsz, honap, adatok, szerkesztheto);
	}

	private static JelenletiAdatDTO toDTO(JelenletiAdat jelenletiAdat) {
		JelenletiAdatDTO dto = new JelenletiAdatDTO();
		dto.setDatum(jelenletiAdat.getDatum());
		dto.setTipus(jelenletiAdat.getTipus());
		dto.setKezdet(jelenletiAdat.getKezdet());
		dto.setVeg(jelenletiAdat.getVeg());
		dto.setLedolgozott(jelenletiAdat.getLedolgozott());
		dto.setTo50(jelenletiAdat.getTo50());
		dto.setTo100(jelenletiAdat.getTo100());
		dto.setM30(jelenletiAdat.getM30());
		return dto;
	}
}
