package hu.interconnect.hr.module.reports.detailedlistofpeopleplussalaryandqualification;

import hu.interconnect.hr.domain.MunkakoriHistorikusAdat;

import java.io.Serializable;
import java.util.Comparator;

public class MunkakoriHistorikusAdatDatumComparator implements Comparator<MunkakoriHistorikusAdat>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(MunkakoriHistorikusAdat o1, MunkakoriHistorikusAdat o2) {
		return o1.getDatum().compareTo(o2.getDatum());
	}

}
