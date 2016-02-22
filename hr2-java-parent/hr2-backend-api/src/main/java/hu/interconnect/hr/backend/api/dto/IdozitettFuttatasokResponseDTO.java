package hu.interconnect.hr.backend.api.dto;

import java.util.Date;
import java.util.List;

public class IdozitettFuttatasokResponseDTO {

	public List<IdozitettFuttatasDTO> idozitettFuttatasok;
	
	public IdozitettFuttatasokResponseDTO(List<IdozitettFuttatasDTO> idozitettFuttatasok) {
		this.idozitettFuttatasok = idozitettFuttatasok;
	}

	public static class IdozitettFuttatasDTO {

		public int id;
		
		public String azonosito;

		public Date inditas;

		public Date befejezes;

		public String eredmeny;
		
		public IdozitettFuttatasDTO(int id, String azonosito, Date inditas, Date befejezes, String eredmeny) {
			this.id = id;
			this.azonosito = azonosito;
			this.inditas = inditas;
			this.befejezes = befejezes;
			this.eredmeny = eredmeny;
		}
	}
}
