package hu.interconnect.hr.module.reports.entransandleavers;

import static com.google.common.collect.Lists.newArrayList;

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
public class EntrantsAndLeaversRowCreator {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@PreAuthorize(AuthorityConstants.HAS_ROLE_BETEKINTO)
	public List<Map<String, Object>> eloallit(Date kezdet, Date veg) {
		List<Szemelyitorzs> szemelyitorzsek = szemelyitorzsDAO.findOsszes();
		List<Szemelyitorzs> listazandok = newArrayList();
		for (Szemelyitorzs szemelyitorzs : szemelyitorzsek) {
			Date jogviszonyKezdete = szemelyitorzs.getJogviszonyAdatok().getJogviszonyKezdete();
			Date jogviszonyVege = szemelyitorzs.getJogviszonyAdatok().getJogviszonyVege();
			if (jogviszonyKezdete != null && !jogviszonyKezdete.before(kezdet) && !jogviszonyKezdete.after(veg) || jogviszonyVege != null && !jogviszonyVege.before(kezdet) && !jogviszonyVege.after(veg)) {
				listazandok.add(szemelyitorzs);
			}
		}
		
		return listazandok.stream().map(new EntrantsAndLeaversRowFromPersonaldataCreator()).collect(Collectors.toList());
	}
}
