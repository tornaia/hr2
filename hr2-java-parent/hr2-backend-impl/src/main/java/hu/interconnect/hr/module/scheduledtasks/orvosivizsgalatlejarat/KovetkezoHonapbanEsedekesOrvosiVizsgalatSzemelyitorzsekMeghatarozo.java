package hu.interconnect.hr.module.scheduledtasks.orvosivizsgalatlejarat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Szemelyitorzs;

@Component
public class KovetkezoHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsekMeghatarozo {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	public List<Szemelyitorzs> getAdottHonapbanEsedekesOrvosiVizsgalatSzemelyitorzsek(Date honap) {
		List<Szemelyitorzs> osszesSzemelyitorzs = szemelyitorzsDAO.findOsszesFetchelve();
		return osszesSzemelyitorzs.stream().filter(new AdottHonapbanEsedekesOrvosiVizsgalatEsAktivSzemelyitorzsPredicate(honap)).collect(Collectors.toList());
	}
}
