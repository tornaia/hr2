package hu.interconnect.hr.module.exceptiondays;

import static hu.interconnect.util.DateUtils.azonosNap;
import static hu.interconnect.util.DateUtils.isHetvege;
import static java.util.Collections.unmodifiableList;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import hu.interconnect.hr.backend.api.enumeration.KivetelnapTipus;
import hu.interconnect.hr.domain.Kivetelnap;

public class KivetelnapokHelper {

	private List<Kivetelnap> kivetelnapok;

	public KivetelnapokHelper(List<Kivetelnap> kivetelnapok) {
		Objects.nonNull(kivetelnapok);
		this.kivetelnapok = unmodifiableList(kivetelnapok);
	}

	public boolean isPihenonap(Date vizsgaltNap) {
		Optional<Kivetelnap> optionalKivetelnap = kivetelnapok.stream().filter(new KivetelnapPredicate(vizsgaltNap)).findFirst();
		if (optionalKivetelnap.isPresent()) {
			Kivetelnap kivetelnap = optionalKivetelnap.get();
			return kivetelnap.getTipus() == KivetelnapTipus.PIHENONAP;
		} else {
			return isHetvege(vizsgaltNap);
		}
	}

	public boolean isMunkanap(Date vizsgaltNap) {
		return !isPihenonap(vizsgaltNap);
	}

	private static class KivetelnapPredicate implements Predicate<Kivetelnap> {

		private Date nap;

		KivetelnapPredicate(Date nap) {
			this.nap = nap;
		}

		@Override
		public boolean test(Kivetelnap input) {
			return azonosNap(nap, input.getDatum());
		}
	}
}
