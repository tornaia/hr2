package hu.interconnect.hr.backend.api.dto;

public class TszEsNevDTO {

	public int tsz;
	
	public String vezeteknev;
	
	public String keresztnev;

	public TszEsNevDTO(int tsz, String vezeteknev, String keresztnev) {
		this.tsz = tsz;
		this.vezeteknev = vezeteknev;
		this.keresztnev = keresztnev;
	}
}