package hu.interconnect.hr.backend.api.dto;

public class FenykepCreateDTO {

	private int tsz;
	
	private String eredetiNev;
	
	private boolean miniatur;
	
	private byte[] adat;
	
	public int getTsz() {
		return tsz;
	}

	public void setTsz(int tsz) {
		this.tsz = tsz;
	}

	public String getEredetiNev() {
		return eredetiNev;
	}

	public void setEredetiNev(String eredetiNev) {
		this.eredetiNev = eredetiNev;
	}

	public boolean isMiniatur() {
		return miniatur;
	}

	public void setMiniatur(boolean miniatur) {
		this.miniatur = miniatur;
	}

	public byte[] getAdat() {
		return adat;
	}

	public void setAdat(byte[] adat) {
		this.adat = adat;
	}
}
