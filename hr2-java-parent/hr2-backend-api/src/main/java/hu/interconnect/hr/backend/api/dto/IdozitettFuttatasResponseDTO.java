package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

public class IdozitettFuttatasResponseDTO {

	public String azonosito;

	public Date inditas;

	public Date befejezes;

	public String eredmeny;
	
	public IdozitettFuttatasResponseDTO(String azonosito, Date inditas, Date befejezes, String eredmeny) {
		this.azonosito = azonosito;
		this.inditas = inditas;
		this.befejezes = befejezes;
		this.eredmeny = eredmeny;
	}
}