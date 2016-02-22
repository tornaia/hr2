package hu.interconnect.hr.module.personaldata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.interconnect.exception.UzletiHiba;
import hu.interconnect.hr.dao.FelhasznaloDAO;
import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.Szemelyitorzs;

@Component
public class PersonaldataDeleteValidator {

	@Autowired
	private FelhasznaloDAO felhasznaloDAO;

	public void validate(Szemelyitorzs szemelyitorzs) {
		List<Felhasznalo> felhasznalok = felhasznaloDAO.findBySzemelyitorzs(szemelyitorzs);
		if (!felhasznalok.isEmpty()) {
			throw new UzletiHiba("uzletihiba.szemelyitorzs.nem.torolheto.felhasznaloi.fiok.hivatkozik.ra");
		}
	}
}
