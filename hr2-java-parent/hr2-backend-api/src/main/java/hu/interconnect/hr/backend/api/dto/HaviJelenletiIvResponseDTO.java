package hu.interconnect.hr.backend.api.dto;

import java.util.Date;
import java.util.List;

public class HaviJelenletiIvResponseDTO {

	public Date honap;
	
	public int tsz;
	
	public List<JelenletiAdatDTO> jelenletiAdatok;
	
	public boolean honapSzerkesztheto;

	public HaviJelenletiIvResponseDTO(int tsz, Date honap, List<JelenletiAdatDTO> jelenletiAdatok, boolean honapSzerkesztheto) {
		this.tsz = tsz;
		this.honap = honap;
		this.jelenletiAdatok = jelenletiAdatok;
		this.honapSzerkesztheto = honapSzerkesztheto;
	}
}
