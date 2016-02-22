package hu.interconnect.hr.module.personaldata.timeandattendance;

import static hu.interconnect.util.DateUtils.azonosNap;
import static java.util.Collections.unmodifiableList;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import hu.interconnect.hr.domain.JelenletiAdat;

public class HaviJelenletiAdatokHelper {

	private List<JelenletiAdat> jelenletiAdatok;
	
	public HaviJelenletiAdatokHelper(List<JelenletiAdat> jelenletiAdatok) {
		this.jelenletiAdatok = jelenletiAdatok == null ? Collections.<JelenletiAdat>emptyList() : unmodifiableList(jelenletiAdatok);
	}

	public boolean containsJelenletiAdat(Date nap) {
		Optional<JelenletiAdat> optionalJelenletiAdat = jelenletiAdatok.stream().filter(new JelenletiAdatPredicate(nap)).findFirst();
		return optionalJelenletiAdat.isPresent();
	}

	public JelenletiAdat getJelenletiAdat(Date nap) {
		return jelenletiAdatok.stream().filter(new JelenletiAdatPredicate(nap)).findFirst().get();
	}
	
	private static class JelenletiAdatPredicate implements Predicate<JelenletiAdat> {

		private Date nap;
		
		JelenletiAdatPredicate(Date nap) {
			this.nap = nap;
		}
		
		@Override
		public boolean test(JelenletiAdat input) {
			return azonosNap(nap, input.getDatum());
		}
	}
}
