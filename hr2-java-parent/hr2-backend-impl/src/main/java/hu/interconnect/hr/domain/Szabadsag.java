package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.util.DateUtils.isInInclusive;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.interconnect.hr.backend.api.dto.SzabadsagFelhasznalasCreateDTO;
import hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg;

@Entity
@Table(name="SZABADSAG")
public class Szabadsag extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="SZEMELYITORZS")
	private Szemelyitorzs szemelyitorzs;
	
	@Column(name = "DATUM")
	@Temporal(TemporalType.DATE)
	private Date datum;
	
	@Column(name="JELLEG")
	@Enumerated(EnumType.STRING)
	private FelhasznaltSzabadnapJelleg jelleg;
	
	public Szabadsag(Szemelyitorzs szemelyitorzs, Date datum, FelhasznaltSzabadnapJelleg jelleg) {
		this.szemelyitorzs = szemelyitorzs;
		this.datum = datum;
		this.jelleg = jelleg;
	}
	
	public void merge(SzabadsagFelhasznalasCreateDTO dto) {
		checkArgument(equalsAndNotNull(szemelyitorzs.getTsz(), dto.getTsz()));
		checkArgument(isInInclusive(datum, dto.getKezdet(), dto.getVeg()));
		
		this.jelleg = dto.getJelleg();
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	public Szemelyitorzs getSzemelyitorzs() {
		return szemelyitorzs;
	}

	public Date getDatum() {
		return datum;
	}

	public FelhasznaltSzabadnapJelleg getJelleg() {
		return jelleg;
	}

	@SuppressWarnings("unused")
	private Szabadsag() {
	}
}
