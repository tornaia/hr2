package hu.interconnect.hr.module.reports.detailedlistofpeopleplussalaryandqualification;

import hu.interconnect.hr.domain.Kepzettseg;

import java.io.Serializable;
import java.util.Comparator;

public class KepzettsegEvComparator implements Comparator<Kepzettseg>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Kepzettseg o1, Kepzettseg o2) {
		return o1.getEv().compareTo(o2.getEv());
	}

}
