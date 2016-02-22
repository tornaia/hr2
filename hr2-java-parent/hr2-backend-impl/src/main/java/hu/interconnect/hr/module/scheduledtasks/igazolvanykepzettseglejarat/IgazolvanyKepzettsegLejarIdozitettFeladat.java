package hu.interconnect.hr.module.scheduledtasks.igazolvanykepzettseglejarat;

import static com.google.common.collect.Iterables.size;
import static hu.interconnect.util.DateUtils.aktualisIdo;
import static hu.interconnect.util.DateUtils.getHoElsoNapja;
import static org.apache.commons.lang.time.DateUtils.addMonths;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
public class IgazolvanyKepzettsegLejarIdozitettFeladat extends AbstractIdozitettFeladat {

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
		EmailTemplate template = emailTemplateDAO.findByType(EmailTemplateType.IDENTIFICATION_QUALIFICATION_EXPIRES);
		Date harmadikHoElsoNapja = getHoElsoNapja(addMonths(aktualisIdo(), 3));
		Date negyedikHoElsoNapja = addMonths(harmadikHoElsoNapja, 1);
	
		List<Szemelyitorzs> osszesSzemelyitorzs = szemelyitorzsDAO.findOsszesFetchelve();
		List<KuldendoLevel> kuldendoLevelek = osszesSzemelyitorzs.stream()
													.filter(new LejaroIgazolvannyalVagyKepzettseggelRendelkezoAktivSzemelyitorzsPredicate(harmadikHoElsoNapja, negyedikHoElsoNapja))
													.map(new SzemelyitorzsbolIgazolvanyKepzettsegLejarLeveletEloallito(template.getContent(), harmadikHoElsoNapja, negyedikHoElsoNapja))
													.collect(Collectors.toList());

		mailSender.sendMails(kuldendoLevelek);
		
		return String.format("%d címzett", size(kuldendoLevelek));
	}
}
