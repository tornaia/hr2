package test.builder;

import hu.interconnect.hr.backend.api.dto.EvesSzabadsagAdatGetDTO;

public class EvesSzabadsagAdatGetDTOBuilder extends Builder<EvesSzabadsagAdatGetDTO>  {

	private int tsz;
	
	private int ev;
	
	public EvesSzabadsagAdatGetDTOBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public EvesSzabadsagAdatGetDTOBuilder ev(int ev) {
		this.ev = ev;
		return this;
	}
	
	@Override
	public EvesSzabadsagAdatGetDTO letrehoz() {
		EvesSzabadsagAdatGetDTO evesSzabadsagAdatQueryDTO = new EvesSzabadsagAdatGetDTO();
		evesSzabadsagAdatQueryDTO.setTsz(tsz);
		evesSzabadsagAdatQueryDTO.setEv(ev);
		return evesSzabadsagAdatQueryDTO;
	}
}
