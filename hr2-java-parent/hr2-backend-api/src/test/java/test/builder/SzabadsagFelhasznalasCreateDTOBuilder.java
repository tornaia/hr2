package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;

public class SzabadsagFelhasznalasCreateDTOBuilder extends Builder<SzabadsagFelhasznalasCreateDTO> {

	private int tsz;
	
	private Date kezdet;
	
	private Date veg;
	
	private FelhasznaltSzabadnapJelleg jelleg;

	public SzabadsagFelhasznalasCreateDTOBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public SzabadsagFelhasznalasCreateDTOBuilder kezdet(String kezdetStr) {
		this.kezdet = parseNap(kezdetStr);
		return this;
	}
	
	public SzabadsagFelhasznalasCreateDTOBuilder veg(String vegStr) {
		this.veg = parseNap(vegStr);
		return this;
	}
	
	public SzabadsagFelhasznalasCreateDTOBuilder jelleg(FelhasznaltSzabadnapJelleg jelleg) {
		this.jelleg = jelleg;
		return this;
	}
	
	@Override
	public SzabadsagFelhasznalasCreateDTO letrehoz() {
		SzabadsagFelhasznalasCreateDTO dto = new SzabadsagFelhasznalasCreateDTO();
		dto.setTsz(tsz);
		dto.setKezdet(kezdet);
		dto.setVeg(veg);
		dto.setJelleg(jelleg);
		return dto;
	}
}
