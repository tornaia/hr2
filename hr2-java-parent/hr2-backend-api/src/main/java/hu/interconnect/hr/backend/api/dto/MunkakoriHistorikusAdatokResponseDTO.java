package hu.interconnect.hr.backend.api.dto;

import java.util.Date;
import java.util.List;

public class MunkakoriHistorikusAdatokResponseDTO {

	public List<MunkakoriHistorikusAdatDTO> munkakoriHistorikusAdatok;
	
	public MunkakoriHistorikusAdatokResponseDTO(List<MunkakoriHistorikusAdatDTO> munkakoriHistorikusAdatok) {
		this.munkakoriHistorikusAdatok = munkakoriHistorikusAdatok;
	}
	
	public static class MunkakoriHistorikusAdatDTO {
		
		public int id;
		
		public Date datum;

		public Integer fEOR;

		public int fizetes;
		
		public MunkakoriHistorikusAdatDTO(int id, Date datum, Integer fEOR, int fizetes) {
			this.id = id;
			this.datum = datum;
			this.fEOR = fEOR;
			this.fizetes = fizetes;
		}
	}
}
