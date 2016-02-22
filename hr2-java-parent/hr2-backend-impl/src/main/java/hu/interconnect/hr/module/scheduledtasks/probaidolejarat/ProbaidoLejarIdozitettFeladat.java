package hu.interconnect.hr.module.scheduledtasks.probaidolejarat;

import static hu.interconnect.util.DateUtils.aktualisIdo;
import static org.apache.commons.lang.time.DateUtils.addDays;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hu.interconnect.common.KuldendoLevel;
import hu.interconnect.hr.backend.api.enumeration.EmailTemplateType;
import hu.interconnect.hr.dao.EmailTemplateDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.EmailTemplate;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.scheduler.AbstractIdozitettFeladat;

@Component
public class ProbaidoLejarIdozitettFeladat extends AbstractIdozitettFeladat {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private EmailTemplateDAO emailTemplateDAO;
	
	@Scheduled(cron=CRON_KIFEJEZES_NAPONTA_EJFELKOR)
	public void leveletKuld() {
		vegrehajtEsEredmenytRogzitEsLeveletKuld();
	}
	
	@Override
	public String getCronKifejezes() {
		return CRON_KIFEJEZES_NAPONTA_EJFELKOR;
	}
	
	@Override
	protected String feladatotVegrehajt() {
		EmailTemplate template = emailTemplateDAO.findByType(EmailTemplateType.PROBATIONARY_PERIOD_EXPIRES);
		Date probaidoVege = addDays(aktualisIdo(), 14);

		List<Szemelyitorzs> adottNaponVanAProbaidoVegeSzemelyitorzsek = szemelyitorzsDAO.findAktivJogviszonyuSzemelyitorzsekByProbaidoVege(probaidoVege);

		if (adottNaponVanAProbaidoVegeSzemelyitorzsek.isEmpty()) {
			return "0 címzett";
		}
		
		KuldendoLevel kuldendoLevel =  new SzemelyitorzsbolProbaidoVegeLeveletEloallito(template.getContent()).apply(adottNaponVanAProbaidoVegeSzemelyitorzsek);

		mailSender.sendMail(kuldendoLevel);
		
		return String.format("%d címzett", adottNaponVanAProbaidoVegeSzemelyitorzsek.size());
	}
}
