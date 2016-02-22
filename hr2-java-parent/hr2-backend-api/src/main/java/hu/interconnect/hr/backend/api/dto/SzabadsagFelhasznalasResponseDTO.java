package hu.interconnect.hr.backend.api.dto;

import java.util.Date;

import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;

public class SzabadsagFelhasznalasResponseDTO {
	
	public int tsz;

	public Date kezdet;
	
	public Date veg;
	
	public FelhasznaltSzabadnapJelleg jelleg;
	
	public Integer munkanapokSzama;
	
	public SzabadsagFelhasznalasResponseDTO(int tsz, Date kezdet, Date veg, FelhasznaltSzabadnapJelleg jelleg, Integer munkanapokSzama) {
		this.tsz = tsz;
		this.kezdet = kezdet;
		this.veg = veg;
		this.jelleg = jelleg;
		this.munkanapokSzama = munkanapokSzama;
	}
}
