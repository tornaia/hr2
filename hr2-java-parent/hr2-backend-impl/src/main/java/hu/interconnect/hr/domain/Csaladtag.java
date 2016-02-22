package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.interconnect.hr.backend.api.dto.CsaladtagEditDTO;

@Entity
@Table(name="CSALADTAG")
public class Csaladtag extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="SZEMELYITORZS")
	private Szemelyitorzs szemelyitorzs;
	
	@Column(name="NEV")
	private String nev;
	
	@Column(name = "SZULETESI_DATUM")
	@Temporal(TemporalType.DATE)
	private Date szuletesiDatum;
	
	@Column(name="TAJ")
	private String taj;
	
	@Column(name="MEGJEGYZES")
	private String megjegyzes;
	
	public Csaladtag(Szemelyitorzs szemelyitorzs,String nev, Date szuletesiDatum, String taj, String megjegyzes) {
		this.szemelyitorzs = szemelyitorzs;
		this.nev = nev;
		this.szuletesiDatum = szuletesiDatum;
		this.taj = taj;
		this.megjegyzes = megjegyzes;
	}
	
	public void merge(CsaladtagEditDTO dto) {
		checkArgument(equalsAndNotNull(id, dto.getId()));

		this.nev = dto.getNev();
		this.szuletesiDatum = dto.getSzuletesiDatum();
		this.taj = dto.getTaj();
		this.megjegyzes = dto.getMegjegyzes();
	}

	@Override
	public Integer getId() {
		return id;
	}

	public Szemelyitorzs getSzemelyitorzs() {
		return szemelyitorzs;
	}

	public String getNev() {
		return nev;
	}

	public Date getSzuletesiDatum() {
		return szuletesiDatum;
	}

	public String getTaj() {
		return taj;
	}

	public String getMegjegyzes() {
		return megjegyzes;
	}

	@SuppressWarnings("unused")
	private Csaladtag() {
	}
}
