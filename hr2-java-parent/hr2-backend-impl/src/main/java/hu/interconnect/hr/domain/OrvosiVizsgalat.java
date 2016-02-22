package hu.interconnect.hr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class OrvosiVizsgalat {

	@Column(name="GYAKORISAG")
	private int gyakorisag;

	@Column(name = "UTOLSO_ORVOSI_VIZSGALAT_IDOPONTJA")
	@Temporal(TemporalType.DATE)
	private Date utolsoOrvosiVizsgalatIdopontja;
	
	public OrvosiVizsgalat(int gyakorisag, Date utolsoOrvosiVizsgalatIdopontja) {
		this.gyakorisag = gyakorisag;
		this.utolsoOrvosiVizsgalatIdopontja = utolsoOrvosiVizsgalatIdopontja;
	}

	public void merge(OrvosiVizsgalat orvosiVizsgalat) {
		this.gyakorisag = orvosiVizsgalat.gyakorisag;
		this.utolsoOrvosiVizsgalatIdopontja = orvosiVizsgalat.utolsoOrvosiVizsgalatIdopontja;
	}
	
	public int getGyakorisag() {
		return gyakorisag;
	}

	public Date getUtolsoOrvosiVizsgalatIdopontja() {
		return utolsoOrvosiVizsgalatIdopontja;
	}

	@SuppressWarnings("unused")
	private OrvosiVizsgalat() {
	}
}
