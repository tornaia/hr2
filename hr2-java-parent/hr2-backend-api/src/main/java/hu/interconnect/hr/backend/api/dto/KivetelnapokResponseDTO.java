package hu.interconnect.hr.backend.api.dto;

import java.util.Date;
import java.util.List;

import hu.interconnect.hr.backend.api.enumeration.KivetelnapTipus;

public class KivetelnapokResponseDTO {

	public List<KivetelnapDTO> kivetelnapok;
	
	public KivetelnapokResponseDTO(List<KivetelnapDTO> kivetelnapok) {
		this.kivetelnapok = kivetelnapok;
	}
	
	public static class KivetelnapDTO {
		
		public int id;
		
		public Date datum;

		public KivetelnapTipus tipus;
		
		public KivetelnapDTO(int id, Date datum, KivetelnapTipus tipus) {
			this.id = id;
			this.datum = datum;
			this.tipus = tipus;
		}
	}
}
