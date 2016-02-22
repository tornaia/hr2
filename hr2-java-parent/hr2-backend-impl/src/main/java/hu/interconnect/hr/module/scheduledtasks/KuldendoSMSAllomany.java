package hu.interconnect.hr.module.scheduledtasks;


public class KuldendoSMSAllomany {

	private String filenev;

	private String tartalom;
	
	public KuldendoSMSAllomany(String filenev, String tartalom) {
		this.filenev = filenev;
		this.tartalom = tartalom;
	}

	public String getFilenev() {
		return filenev;
	}

	public String getTartalom() {
		return tartalom;
	}

	@Override
	public String toString() {
		return new StringBuilder("KuldendoSMSAllomany, filenev: ").append(filenev).append(", tartalom: ").append(tartalom).toString();
	}
	
}
