package hu.interconnect.hr.module.personaldata.vacations;

import java.io.Serializable;
import java.util.Comparator;

import hu.interconnect.hr.domain.Szabadsag;

public class SzabadsagDatumComparator implements Comparator<Szabadsag>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Szabadsag o1, Szabadsag o2) {
		return o1.getDatum().compareTo(o2.getDatum());
	}

}
