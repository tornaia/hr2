package hu.interconnect.hr.module.personaldata.timeandattendance;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvEditDTO;
import hu.interconnect.hr.backend.api.dto.JelenletiAdatDTO;
import hu.interconnect.hr.backend.api.service.TimeandattendanceCommandService;
import hu.interconnect.hr.dao.HaviJelenletiIvAllapotDAO;
import hu.interconnect.hr.dao.JelenletiAdatDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.HaviJelenletiIvAllapot;
import hu.interconnect.hr.domain.JelenletiAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;
import hu.interconnect.util.FelhasznaloUtils;

@Service
public class TimeandattendanceCommandServiceImpl implements TimeandattendanceCommandService {

	private static final BiFunction<Szemelyitorzs, JelenletiAdatDTO, JelenletiAdat> SZEMELYITORZS_ES_JELENLETI_ADAT_DTO_TO_JELENLETI_ADAT = (sz, j) -> new JelenletiAdat(sz, j.getDatum(), j.getTipus(), j.getKezdet(), j.getVeg(), j.getLedolgozott(), j.getTo50(), j.getTo100(), j.getM30());
	
	@Autowired
	private JelenletiAdatDAO jelenletiAdatDAO;
	
	@Autowired
	private HaviJelenletiIvAllapotDAO haviJelenletiIvAllapotDAO;
	
	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Override
	@PreAuthorize(AuthorityConstants.PERSONAL_DATA_ID_EQUALS_TO + " #dto.tsz " + AuthorityConstants.OR + AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void edit(HaviJelenletiIvEditDTO dto) {
		Felhasznalo aktualisFelhasznalo = FelhasznaloUtils.aktualisFelhasznalo();
		if (aktualisFelhasznalo.isAdministrator()) {
			szerkesztestVegrehajt(dto);
		} else {
			Optional<HaviJelenletiIvAllapot> optionalAllapot = haviJelenletiIvAllapotDAO.findByHonap(dto.getHonap());
			boolean isMonthEditable = !optionalAllapot.isPresent() || optionalAllapot.get().isSzerkesztheto();
			if (isMonthEditable) {
				szerkesztestVegrehajt(dto);
			} else {
				throw new ProgramozasiHiba("Adott honap le van zarva megis szerkeszteni probalta valaki!");
			}
		}
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void lockMonth(Date month) {
		Optional<HaviJelenletiIvAllapot> allapot = haviJelenletiIvAllapotDAO.findByHonap(month);
		if (allapot.isPresent()) {
			allapot.get().zar();
		} else {
			haviJelenletiIvAllapotDAO.persist(new HaviJelenletiIvAllapot(month, false));
		}
	}
	
	@Override
	@PreAuthorize(AuthorityConstants.HAS_ROLE_ADMINISTRATOR)
	public void unlockMonth(Date month) {
		Optional<HaviJelenletiIvAllapot> allapot = haviJelenletiIvAllapotDAO.findByHonap(month);
		allapot.get().nyit();
	}
	
	private void szerkesztestVegrehajt(HaviJelenletiIvEditDTO dto) {
		HaviJelenletiAdatokHelper haviJelenletiAdatok = new HaviJelenletiAdatokHelper(jelenletiAdatDAO.findBySzemelyitorzsEsHonap(dto.getTsz(), dto.getHonap()));
		List<JelenletiAdatDTO> jelenletiAdatDTOk = dto.getJelenletiAdatok();
		
		jelenletiAdatDTOk
			.stream()
			.filter(jelenletiAdatDTO -> haviJelenletiAdatok.containsJelenletiAdat(jelenletiAdatDTO.getDatum()))
			.forEach(jelenletiAdatDTO -> haviJelenletiAdatok.getJelenletiAdat(jelenletiAdatDTO.getDatum()).merge(jelenletiAdatDTO));
		
		jelenletiAdatDTOk
			.stream()
			.filter(jelenletiAdatDTO -> !haviJelenletiAdatok.containsJelenletiAdat(jelenletiAdatDTO.getDatum()))
			.map(jelenletiAdatDTO -> SZEMELYITORZS_ES_JELENLETI_ADAT_DTO_TO_JELENLETI_ADAT.apply(szemelyitorzsDAO.findById(dto.getTsz()), jelenletiAdatDTO))
			.forEach(jelenletiAdatDAO::persist);
	}
}
