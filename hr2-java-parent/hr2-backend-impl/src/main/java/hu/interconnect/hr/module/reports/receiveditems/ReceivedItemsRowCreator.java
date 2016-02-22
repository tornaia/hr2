package hu.interconnect.hr.module.reports.receiveditems;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.AtvettEszkoz;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.security.AuthorityConstants;

@Component
public class ReceivedItemsRowCreator {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Map<String, Object>> eloallit(int tsz) {
		Szemelyitorzs szemelyitorzs = szemelyitorzsDAO.findByIdFetchelve(tsz);
		Set<AtvettEszkoz> atvettEszkozok = szemelyitorzs.getAtvettEszkozok();
		return atvettEszkozok
				.stream()
				.map(new ReceivedItemsRowFromReceivedItemCreator())
				.collect(Collectors.toList());
	}
}
