package hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat;

import static com.google.common.collect.Iterables.size;
import static hu.interconnect.util.DateUtils.aktualisIdo;
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
import hu.interconnect.hr.domain.EmailTemplate;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.scheduler.AbstractIdozitettFeladat;

@Component
public class OrvosiVizsgalatLejarEmailIdozitettFeladat extends AbstractIdozitettFeladat {

	@Autowired
	private KovetkezoHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsekMeghatarozo kovetkezoHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsekMeghatarozo;
	
	@Autowired
	private EmailTemplateDAO emailTemplateDAO;

	@Scheduled(cron=CRON_KIFEJEZES_HONAP_15_NAPJAN_14_ORAKOR)
	public void leveletKuld() {
		vegrehajtEsEredmenytRogzitEsLeveletKuld();
	}
	
	@Override
	public String getCronKifejezes() {
		return CRON_KIFEJEZES_HONAP_15_NAPJAN_14_ORAKOR;
	}
	
	@Override
	protected String feladatotVegrehajt() {
		EmailTemplate template = emailTemplateDAO.findByType(EmailTemplateType.MEDICAL_EXAMINATION_EXPIRES);
		Date kovetkezoHonap = addMonths(aktualisIdo(), 1);
		
		List<Szemelyitorzs> adottHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsek = kovetkezoHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsekMeghatarozo.getAdottHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsek(kovetkezoHonap);
		List<KuldendoLevel> kuldendoLevelek = adottHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsek
				.stream()
				.map(new SzemelyitorzsbolEsedekesOrvosiVizsgalatLeveletEloallito(template.getContent()))
				.collect(Collectors.toList());

		mailSender.sendMails(kuldendoLevelek);
		
		return String.format("%d címzett", size(kuldendoLevelek));
	}
}
