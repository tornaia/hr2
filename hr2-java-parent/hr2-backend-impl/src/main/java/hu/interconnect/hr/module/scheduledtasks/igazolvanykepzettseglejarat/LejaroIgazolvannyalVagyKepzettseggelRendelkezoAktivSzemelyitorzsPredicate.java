package hu.interconnect.hr.module.scheduledtasks.igazolvanykepzettseglejarat;

import static hu.interconnect.util.DateUtils.isBetween;

import java.util.Date;
import java.util.Set;
import java.util.function.Predicate;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.domain.Kepzettseg;
import hu.interconnect.hr.domain.SzemelyiAdatok;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.util.DateUtils;

public class LejaroIgazolvannyalVagyKepzettseggelRendelkezoAktivSzemelyitorzsPredicate implements Predicate<Szemelyitorzs> {

	private Date kezdet;
	
	private Date veg;

	public LejaroIgazolvannyalVagyKepzettseggelRendelkezoAktivSzemelyitorzsPredicate(Date kezdet, Date veg) {
		this.kezdet = kezdet;
		this.veg = veg;
	}

	@Override
	public boolean test(Szemelyitorzs szemelyitorzs) {
		if (szemelyitorzs.getJogviszonyAdatok().getAllomanymod() != Allomanymod.AKTIV) {
			return false;
		}

		Set<Kepzettseg> kepzettsegek = szemelyitorzs.getKepzettsegek();
		boolean hasExpiredKepzettseg = kepzettsegek
										.stream()
										.filter(kepzettseg -> DateUtils.isBetween(kepzettseg.getErvenyessegVege(), kezdet, veg))
										.findFirst()
										.isPresent();
		if (hasExpiredKepzettseg) {
			return true;
		}

		SzemelyiAdatok szemelyiAdatok = szemelyitorzs.getSzemelyiAdatok();
		boolean szemelyiIgazolvanySzamLejar = szemelyiAdatok != null && isBetween(szemelyiAdatok.getSzemelyiIgazolvanySzamLejarat(), kezdet, veg);
		boolean utlevelSzamLejar = szemelyiAdatok != null && isBetween(szemelyiAdatok.getUtlevelSzamLejarat(), kezdet, veg);

		return szemelyiIgazolvanySzamLejar || utlevelSzamLejar;
	}
}
