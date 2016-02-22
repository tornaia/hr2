package test.builder;

import static hu.interconnect.util.DateUtils.parseNap;

import java.util.Date;

import hu.interconnect.hr.domain.OrvosiVizsgalat;

public class OrvosiVizsgalatBuilder extends Builder<OrvosiVizsgalat> {

	private int gyakorisag;
	
	private Date utolsoOrvosiVizsgalatIdopontja;
	
	public OrvosiVizsgalatBuilder gyakorisag(int gyakorisag) {
		this.gyakorisag = gyakorisag;
		return this;
	}
	
	public OrvosiVizsgalatBuilder utolsoOrvosiVizsgalatIdopontja(String datum) {
		this.utolsoOrvosiVizsgalatIdopontja = parseNap(datum);
		return this;
	}
	
	@Override
	public OrvosiVizsgalat letrehoz() {
		return new OrvosiVizsgalat(gyakorisag, utolsoOrvosiVizsgalatIdopontja);
	}
}
