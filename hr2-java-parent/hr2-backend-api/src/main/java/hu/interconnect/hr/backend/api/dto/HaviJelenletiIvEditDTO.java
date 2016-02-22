package hu.interconnect.hr.backend.api.dto;

import java.util.Date;
import java.util.List;

public class HaviJelenletiIvEditDTO {

	private int tsz;

	private Date honap;

	private List<JelenletiAdatDTO> jelenletiAdatok;
	
	public int getTsz() {
		return tsz;
	}
	
	public void setTsz(int tsz) {
		this.tsz = tsz;
	}

	public Date getHonap() {
		return honap;
	}

	public void setHonap(Date honap) {
		this.honap = honap;
	}

	public List<JelenletiAdatDTO> getJelenletiAdatok() {
		return jelenletiAdatok;
	}

	public void setJelenletiAdatok(List<JelenletiAdatDTO> jelenletiAdatok) {
		this.jelenletiAdatok = jelenletiAdatok;
	}
}
