package test.builder;

import static hu.interconnect.util.DateUtils.parseEv;
import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.backend.api.dto.KepzettsegCreateDTO;

public class KepzettsegCreateDTOBuilder extends Builder<KepzettsegCreateDTO> {

	private int tsz;
	
	private String tipus;
	
	private String megnevezes;
	
	private String modFok;
	
	private Date ev;

	private Date ervenyessegVege;

	private String megjegyzes;
	
	public KepzettsegCreateDTOBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public KepzettsegCreateDTOBuilder tipus(String tipus) {
		this.tipus = tipus;
		return this;
	}
	
	public KepzettsegCreateDTOBuilder megnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
		return this;
	}
	
	public KepzettsegCreateDTOBuilder modFok(String modFok) {
		this.modFok = modFok;
		return this;
	}
	
	public KepzettsegCreateDTOBuilder ev(String ev) {
		this.ev = parseEv(ev);
		return this;
	}
	
	public KepzettsegCreateDTOBuilder ervenyessegVeg(String napStr) {
		return ervenyessegVeg(parseNap(napStr));
	}
	
	public KepzettsegCreateDTOBuilder ervenyessegVeg(Date datum) {
		this.ervenyessegVege = datum;
		return this;
	}
	
	public KepzettsegCreateDTOBuilder megjegyzes(String megjegyzes) {
		this.megjegyzes = megjegyzes;
		return this;
	}
	
	@Override
	public KepzettsegCreateDTO letrehoz() {
		KepzettsegCreateDTO dto = new KepzettsegCreateDTO();
		dto.setTsz(tsz);
		dto.setTipus(tipus);
		dto.setMegnevezes(megnevezes);
		dto.setModFok(modFok);
		dto.setEv(ev);
		dto.setErvenyessegVege(ervenyessegVege);
		dto.setMegjegyzes(megjegyzes);
		return dto;
	}
}
