package hu.interconnect.hr.backend.api.dto;

import java.util.Date;
import java.util.List;

public class KepzettsegekResponseDTO {

	public List<KepzettsegDTO> kepzettsegek;
	
	public KepzettsegekResponseDTO(List<KepzettsegDTO> kepzettsegek) {
		this.kepzettsegek = kepzettsegek;
	}
	
	public static class KepzettsegDTO {
		
		public int id;
		
		public String tipus;
		
		public String megnevezes;
		
		public String modFok;
		
		public Date ev;
		
		public Date ervenyessegVege;
		
		public String megjegyzes;
		
		public KepzettsegDTO(int id, String tipus, String megnevezes, String modFok, Date ev, Date ervenyessegVege, String megyjegyzes) {
			this.id = id;
			this.tipus = tipus;
			this.megnevezes = megnevezes;
			this.modFok = modFok;
			this.ev = ev;
			this.ervenyessegVege = ervenyessegVege;
			this.megjegyzes = megyjegyzes;
		}
	}
}