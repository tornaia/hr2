package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.Date;
import java.util.Objects;

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

import hu.interconnect.hr.backend.api.dto.JelenletiAdatDTO;
import hu.interconnect.hr.backend.api.enumeration.JelenletiAdatTipus;

@Entity
@Table(name="JELENLETI_ADAT")
public class JelenletiAdat {

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
	
	@Column(name="TIPUS")
	@Enumerated(EnumType.STRING)
	private JelenletiAdatTipus tipus;
	
	@Column(name = "KEZDET")
	@Temporal(TemporalType.TIME)
	private Date kezdet;
	
	@Column(name = "VEG")
	@Temporal(TemporalType.TIME)
	private Date veg;
	
	@Column(name = "LEDOLGOZOTT")
	@Temporal(TemporalType.TIME)
	private Date ledolgozott;
	
	@Column(name = "TO50")
	@Temporal(TemporalType.TIME)
	private Date to50;
	
	@Column(name = "TO100")
	@Temporal(TemporalType.TIME)
	private Date to100;
	
	@Column(name = "M30")
	@Temporal(TemporalType.TIME)
	private Date m30;
	
	public JelenletiAdat(Szemelyitorzs szemelyitorzs, Date datum, JelenletiAdatTipus tipus, Date kezdet, Date veg, Date ledolgozott, Date to50, Date to100, Date m30) {
		checkArgument(nonNull(szemelyitorzs));
		checkArgument(nonNull(datum));
		checkArgument(nonNull(tipus));
		checkArgument(equalsAndNotNull(tipus, JelenletiAdatTipus.MUNKA) && nonNull(kezdet) && nonNull(veg) && nonNull(ledolgozott) || nonNull(tipus) && !Objects.equals(tipus, JelenletiAdatTipus.MUNKA) && isNull(kezdet) && isNull(veg) && isNull(ledolgozott) && isNull(to50) && isNull(to100) && isNull(m30));
		
		this.szemelyitorzs = szemelyitorzs;
		this.datum = datum;
		this.tipus = tipus;
		this.kezdet = kezdet;
		this.veg = veg;
		this.ledolgozott = ledolgozott;
		this.to50 = to50;
		this.to100 = to100;
		this.m30 = m30;
	}

	public void merge(JelenletiAdatDTO dto) {
		checkArgument(nonNull(dto));
		checkArgument(nonNull(dto.getTipus()));
		checkArgument(equalsAndNotNull(datum, dto.getDatum()));
		checkArgument(equalsAndNotNull(tipus, JelenletiAdatTipus.MUNKA) && nonNull(kezdet) && nonNull(veg == null) && nonNull(ledolgozott == null) || nonNull(tipus) && !Objects.equals(tipus, JelenletiAdatTipus.MUNKA) && isNull(kezdet) && isNull(veg) && isNull(ledolgozott) && isNull(to50) && isNull(to100) && isNull(m30));

		this.tipus = dto.getTipus();
		this.kezdet = dto.getKezdet();
		this.veg = dto.getVeg();
		this.ledolgozott = dto.getLedolgozott();
		this.to50 = dto.getTo50();
		this.to100 = dto.getTo100();
		this.m30 = dto.getM30();
	}
	
	public Integer getId() {
		return id;
	}

	public Szemelyitorzs getSzemelyitorzs() {
		return szemelyitorzs;
	}

	public Date getDatum() {
		return datum;
	}

	public JelenletiAdatTipus getTipus() {
		return tipus;
	}

	public Date getKezdet() {
		return kezdet;
	}

	public Date getVeg() {
		return veg;
	}

	public Date getLedolgozott() {
		return ledolgozott;
	}

	public Date getTo50() {
		return to50;
	}

	public Date getTo100() {
		return to100;
	}

	public Date getM30() {
		return m30;
	}

	@SuppressWarnings("unused")
	private JelenletiAdat() {
	}
}
