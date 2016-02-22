package hu.interconnect.hr.module.reports.medicalexaminationexpiry;

import static com.google.common.collect.Lists.newArrayList;
import static hu.interconnect.util.DateUtils.getCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class MedicalExaminationExpiryRowCreator {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Map<String, Object>> eloallit(Date kezdet, Date veg) {
		List<Szemelyitorzs> szemelyitorzsek = szemelyitorzsDAO.findOsszes();
		List<Szemelyitorzs> listazandok = newArrayList();
		for (Szemelyitorzs szemelyitorzs : szemelyitorzsek) {
			Date utolsoOrvosiVizsgalatIdopontja = szemelyitorzs.getOrvosiVizsgalat().getUtolsoOrvosiVizsgalatIdopontja();
			int gyakorisag = szemelyitorzs.getOrvosiVizsgalat().getGyakorisag();
			if (utolsoOrvosiVizsgalatIdopontja == null) {
				continue;
			}
			Calendar utolsoOrvosiVizsgalatIdopontjaCal = getCalendar(utolsoOrvosiVizsgalatIdopontja);
			utolsoOrvosiVizsgalatIdopontjaCal.add(Calendar.MONTH, gyakorisag);
			
			if (!utolsoOrvosiVizsgalatIdopontjaCal.getTime().before(kezdet) && !utolsoOrvosiVizsgalatIdopontjaCal.getTime().after(veg)) {
				listazandok.add(szemelyitorzs);
			}
		}
		
		return listazandok.stream().map(new MedicalExaminationExpiryRowFromPersonaldataCreator()).collect(Collectors.toList());
	}
}
