package hu.interconnect.hr.module.reports.personaldata;

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
public class PersonalDataRowCreator {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Map<String, Object>> eloallit(RiportAllomanymod riportAllomanymod) {
		List<Szemelyitorzs> szemelyitorzsek = szemelyitorzsDAO.findByRiportAllomanymod(riportAllomanymod);
		return szemelyitorzsek.stream().map(new PersonalDataRowFromPersonalDataCreator()).collect(Collectors.toList());
	}
}
