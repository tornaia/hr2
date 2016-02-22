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

import hu.interconnect.hr.backend.api.dto.MunkakoriHistorikusAdatEditDTO;

@Entity
@Table(name = "MUNKAKORI_HISTORIKUS_ADAT")
public class MunkakoriHistorikusAdat extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "SZEMELYITORZS")
	private Szemelyitorzs szemelyitorzs;

	@Column(name = "DATUM")
	@Temporal(TemporalType.DATE)
	private Date datum;

	@ManyToOne
	@JoinColumn(name = "FEOR")
	private FEOR fEOR;

	@Column(name = "FIZETES")
	private int fizetes;

	public MunkakoriHistorikusAdat(Szemelyitorzs szemelyitorzs, Date datum, FEOR fEOR, int fizetes) {
		this.szemelyitorzs = szemelyitorzs;
		this.datum = datum;
		this.fEOR = fEOR;
		this.fizetes = fizetes;
	}

	public void merge(MunkakoriHistorikusAdatEditDTO dto) {
		checkArgument(equalsAndNotNull(id, dto.getId()));
		
		this.datum = dto.getDatum();
		this.fEOR = new FEOR(dto.getfEOR());
		this.fizetes = dto.getFizetes();
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

	public FEOR getfEOR() {
		return fEOR;
	}

	public int getFizetes() {
		return fizetes;
	}

	@SuppressWarnings("unused")
	private MunkakoriHistorikusAdat() {
	}
}
