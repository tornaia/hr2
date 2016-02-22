package hu.interconnect.common;

import java.util.List;

public class KuldendoLevel {

	private List<String> cimzettek;
	
	private String targy;
	
	private String tartalom;

	public KuldendoLevel(List<String> cimzettek, String targy, String tartalom) {
		this.cimzettek = cimzettek;
		this.targy = targy;
		this.tartalom = tartalom;
	}

	public String[] getCimzettek() {
		return cimzettek.toArray(new String[0]);
	}

	public String getTargy() {
		return targy;
	}

	public String getTartalom() {
		return tartalom;
	}
	
}
