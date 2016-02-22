package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatCreateDTO;

public class MunkakoriHistorikusAdatCreateDTOBuilder extends Builder<MunkakoriHistorikusAdatCreateDTO> {

	private int tsz;
	
	private Date datum;

	private Integer fEOR;

	private int fizetes;

	public MunkakoriHistorikusAdatCreateDTOBuilder tsz(int tsz) {
		this.tsz = tsz;
		return this;
	}
	
	public MunkakoriHistorikusAdatCreateDTOBuilder datum(String napStr) {
		this.datum = parseNap(napStr);
		return this;
	}
	
	public MunkakoriHistorikusAdatCreateDTOBuilder fEOR(Integer fEOR) {
		this.fEOR = fEOR;
		return this;
	}
	
	public MunkakoriHistorikusAdatCreateDTOBuilder fizetes(int fizetes) {
		this.fizetes = fizetes;
		return this;
	}
	
	@Override
	public MunkakoriHistorikusAdatCreateDTO letrehoz() {
		MunkakoriHistorikusAdatCreateDTO dto = new MunkakoriHistorikusAdatCreateDTO();
		dto.setTsz(tsz);
		dto.setDatum(datum);
		dto.setfEOR(fEOR);
		dto.setFizetes(fizetes);
		return dto;
	}
}
