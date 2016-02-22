package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.domain.FEOR;
import hu.interconnect.hr.domain.MunkakoriHistorikusAdat;
import hu.interconnect.hr.domain.Szemelyitorzs;

public class MunkakoriHistorikusAdatBuilder extends Builder<MunkakoriHistorikusAdat> {

	private Szemelyitorzs szemelyitorzs;
	
	private Date datum;

	private FEOR fEOR;

	private int fizetes;

	public MunkakoriHistorikusAdatBuilder szemelyitorzs(Szemelyitorzs szemelyitorzs) {
		this.szemelyitorzs = szemelyitorzs;
		return this;
	}
	
	public MunkakoriHistorikusAdatBuilder datum(String napStr) {
		this.datum = parseNap(napStr);
		return this;
	}
	
	public MunkakoriHistorikusAdatBuilder fEOR(FEOR fEOR) {
		this.fEOR = fEOR;
		return this;
	}
	
	public MunkakoriHistorikusAdatBuilder fizetes(int fizetes) {
		this.fizetes = fizetes;
		return this;
	}
	
	@Override
	public MunkakoriHistorikusAdat letrehoz() {
		return new MunkakoriHistorikusAdat(szemelyitorzs, datum, fEOR, fizetes);
	}
}
