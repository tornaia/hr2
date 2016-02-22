package hu.interconnect.hr.module.reports.monthlypayrolldata;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.dao.JelenletiAdatDAO;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class MonthlyPayrollDataRowCreator {
	
	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Autowired
	private JelenletiAdatDAO jelenletiAdatDAO;
	
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Map<String, Object>> eloallit(Date honap) {
		List<Szemelyitorzs> szemelyitorzsek = szemelyitorzsDAO.findByAllomanymod(Allomanymod.AKTIV);
		return szemelyitorzsek.stream().map(new MonthlyPayrollDataRowFromPersonaldataCreator(jelenletiAdatDAO, honap)).collect(Collectors.toList());
	}
}
