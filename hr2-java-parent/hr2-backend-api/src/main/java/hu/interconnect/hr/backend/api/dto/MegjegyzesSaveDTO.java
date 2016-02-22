package hu.interconnect.hr.backend.api.dto;

public class MegjegyzesSaveDTO {

	private int tsz;
	
	private String szoveg;

	public int getTsz() {
		return tsz;
	}

	public void setTsz(int tsz) {
		this.tsz = tsz;
	}

	public String getSzoveg() {
		return szoveg;
	}

	public void setSzoveg(String szoveg) {
		this.szoveg = szoveg;
	}
}
