package hu.interconnect.hr.module.scheduledtasks.jogviszonyvege;

import static hu.interconnect.util.DateUtils.aktualisIdo;
import static org.apache.commons.lang.time.DateUtils.addMonths;

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
public class JogviszonyVegeIdozitettFeladat extends AbstractIdozitettFeladat {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private EmailTemplateDAO emailTemplateDAO;
	
	@Scheduled(cron=CRON_KIFEJEZES_HONAP_ELSO_NAPJAN_EJFELKOR)
	public void leveletKuld() {
		vegrehajtEsEredmenytRogzitEsLeveletKuld();
	}
	
	@Override
	public String getCronKifejezes() {
		return CRON_KIFEJEZES_HONAP_ELSO_NAPJAN_EJFELKOR;
	}
	
	@Override
	protected String feladatotVegrehajt() {
		EmailTemplate template = emailTemplateDAO.findByType(EmailTemplateType.EMPLOYMENT_RELATION_EXPIRES);
		Date jogviszonyVege = addMonths(aktualisIdo(), 1);

		List<Szemelyitorzs> adottHonapbanVanAJohviszonyVegeSzemelyitorzsek = szemelyitorzsDAO.findAktivJogviszonyuSzemelyitorzsekByJogviszonyVege(jogviszonyVege);

		if (!adottHonapbanVanAJohviszonyVegeSzemelyitorzsek.isEmpty()) {
			KuldendoLevel kuldendoLevel = new SzemelyitorzsbolJogviszonyVegeLeveletEloallito(template.getContent()).apply(adottHonapbanVanAJohviszonyVegeSzemelyitorzsek);
			mailSender.sendMail(kuldendoLevel);
		}
		
		return String.format("%d személy", adottHonapbanVanAJohviszonyVegeSzemelyitorzsek.size());
	}
}
