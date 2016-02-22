package test.builder;

import static hu.interconnect.util.DateUtils.parseHonap;

import java.util.Date;
import java.util.List;

import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvEditDTO;
import hu.interconnect.hr.backend.api.dto.JelenletiAdatDTO;

public class HaviJelenletiIvEditDTOBuilder extends Builder<HaviJelenletiIvEditDTO> {

	private int tsz;
	
	private Date honap;
	
	private List<JelenletiAdatDTO> jelenletiAdatok;
	
	public HaviJelenletiIvEditDTOBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public HaviJelenletiIvEditDTOBuilder honap(Date honap) {
		this.honap = honap;
		return this;
	}
	
	public HaviJelenletiIvEditDTOBuilder honap(String honapStr) {
		return honap(parseHonap(honapStr));
	}
	
	public HaviJelenletiIvEditDTOBuilder jelenletiAdatok(List<JelenletiAdatDTO> jelenletiAdatok) {
		this.jelenletiAdatok = jelenletiAdatok;
		return this;
	}
	
	@Override
	public HaviJelenletiIvEditDTO letrehoz() {
		HaviJelenletiIvEditDTO dto = new HaviJelenletiIvEditDTO();
		dto.setTsz(tsz);
		dto.setHonap(honap);
		dto.setJelenletiAdatok(jelenletiAdatok);
		return dto;
	}
}
