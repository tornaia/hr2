package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

public class MunkakoriHistorikusAdatCreateDTO {

	private int tsz;

	private Date datum;

	private Integer fEOR;

	private int fizetes;

	public int getTsz() {
		return tsz;
	}

	public void setTsz(int tsz) {
		this.tsz = tsz;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Integer getfEOR() {
		return fEOR;
	}

	public void setfEOR(Integer fEOR) {
		this.fEOR = fEOR;
	}

	public int getFizetes() {
		return fizetes;
	}

	public void setFizetes(int fizetes) {
		this.fizetes = fizetes;
	}
}
