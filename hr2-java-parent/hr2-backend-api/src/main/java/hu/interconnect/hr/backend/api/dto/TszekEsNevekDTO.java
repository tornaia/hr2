package hu.interconnect.hr.backend.api.dto;

import java.util.List;

public class TszekEsNevekDTO {

	public List<TszEsNevDTO> tszekEsNevek;
	
	public TszekEsNevekDTO(List<TszEsNevDTO> tszekEsNevek) {
		this.tszekEsNevek = tszekEsNevek;
	}
}
