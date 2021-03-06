package hu.interconnect.hr.module.reports.detailedlistofpeopleplussalaryandqualification;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.enumeration.RiportAllomanymod;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class DetailedListOfPeoplePlusSalaryAndQualificationRowCreator {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Map<String, Object>> eloallit(RiportAllomanymod riportAllomanymod) {
		List<Szemelyitorzs> szemelyitorzsek = szemelyitorzsDAO.findByRiportAllomanymodFetchelve(riportAllomanymod);
		
		return szemelyitorzsek.stream()
								.map(new DetailedListOfPeoplePlusSalaryAndQualificationRowFromPersonaldataCreator())
								.flatMap(l -> l.stream())
								.collect(Collectors.toList());
	}
}
