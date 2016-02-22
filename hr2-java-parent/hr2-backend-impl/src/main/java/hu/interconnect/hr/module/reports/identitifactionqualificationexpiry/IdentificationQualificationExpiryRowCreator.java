package hu.interconnect.hr.module.reports.identitifactionqualificationexpiry;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.module.scheduledtasks.igazolvanykepzettseglejarat.LejaroIgazolvannyalVagyKepzettseggelRendelkezoAktivSzemelyitorzsPredicate;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class IdentificationQualificationExpiryRowCreator {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Map<String, Object>> eloallit(Date kezdet, Date veg) {
		List<Szemelyitorzs> osszesSzemelyitorzs = szemelyitorzsDAO.findOsszesFetchelve();
		return osszesSzemelyitorzs.stream()
					.filter(new LejaroIgazolvannyalVagyKepzettseggelRendelkezoAktivSzemelyitorzsPredicate(kezdet, veg))
					.map(new IdentificationQualificationExpiryRowFromQualificationCreator(kezdet, veg))
					.flatMap(l -> l.stream())
					.collect(Collectors.toList());
	}
}
