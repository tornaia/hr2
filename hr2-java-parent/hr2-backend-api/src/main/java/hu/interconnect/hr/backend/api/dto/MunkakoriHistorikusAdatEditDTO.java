package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

public class MunkakoriHistorikusAdatEditDTO {

	private int id;

	private Date datum;

	private Integer fEOR;

	private int fizetes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
