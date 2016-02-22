package test.builder;

import hu.interconnect.hr.backend.api.dto.AtvettEszkozCreateDTO;

public class AtvettEszkozCreateDTOBuilder extends Builder<AtvettEszkozCreateDTO> {

	private int tsz;
	
	private String megnevezes;
	
	private String megjegyzes;
	
	private String eredetiNev;
	
	private byte[] adat;
	
	public AtvettEszkozCreateDTOBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public AtvettEszkozCreateDTOBuilder megnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
		return this;
	}
	
	public AtvettEszkozCreateDTOBuilder megjegyzes(String megjegyzes) {
		this.megjegyzes = megjegyzes;
		return this;
	}
	
	public AtvettEszkozCreateDTOBuilder eredetiNev(String eredetiNev) {
		this.eredetiNev = eredetiNev;
		return this;
	}
	
	public AtvettEszkozCreateDTOBuilder adat(byte[] adat) {
		this.adat = adat;
		return this;
	}
	
	@Override
	public AtvettEszkozCreateDTO letrehoz() {
		AtvettEszkozCreateDTO dto = new AtvettEszkozCreateDTO();
		dto.setTsz(tsz);
		dto.setMegnevezes(megnevezes);
		dto.setMegjegyzes(megjegyzes);
		dto.setEredetiNev(eredetiNev);
		dto.setAdat(adat);
		return dto;
	}
}
