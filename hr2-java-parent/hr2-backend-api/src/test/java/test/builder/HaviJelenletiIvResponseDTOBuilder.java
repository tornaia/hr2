package test.builder;

import static hu.interconnect.util.DateUtils.parseHonap;

import java.util.Date;
import java.util.List;

import hu.interconnect.hr.backend.api.dto.HaviJelenletiIvResponseDTO;
import hu.interconnect.hr.backend.api.dto.JelenletiAdatDTO;

public class HaviJelenletiIvResponseDTOBuilder extends Builder<HaviJelenletiIvResponseDTO> {

	private int tsz;
	
	private Date honap;
	
	private List<JelenletiAdatDTO> jelenletiAdatok;
	
	private boolean szerkesztheto;
	
	public HaviJelenletiIvResponseDTOBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public HaviJelenletiIvResponseDTOBuilder honap(String honapStr) {
		this.honap = parseHonap(honapStr);
		return this;
	}
	
	public HaviJelenletiIvResponseDTOBuilder jelenletiAdatok(List<JelenletiAdatDTO> jelenletiAdatok) {
		this.jelenletiAdatok = jelenletiAdatok;
		return this;
	}
	
	public HaviJelenletiIvResponseDTOBuilder szerkesztheto(boolean szerkesztheto) {
		this.szerkesztheto = szerkesztheto;
		return this;
	}
	
	@Override
	public HaviJelenletiIvResponseDTO letrehoz() {
		return new HaviJelenletiIvResponseDTO(tsz, honap, jelenletiAdatok, szerkesztheto);
	}
}
