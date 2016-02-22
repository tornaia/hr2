package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.interconnect.hr.backend.api.enumeration.KivetelnapTipus;

@Entity
@Table(name = "KIVETELNAP")
public class Kivetelnap extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;

	@Column(name = "DATUM")
	@Temporal(TemporalType.DATE)
	private Date datum;

	@Column(name="TIPUS")
	@Enumerated(EnumType.STRING)
	private KivetelnapTipus tipus;

	public Kivetelnap(Date datum, KivetelnapTipus tipus) {
		checkArgument(nonNull(datum));
		checkArgument(nonNull(tipus));
		
		this.datum = datum;
		this.tipus = tipus;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public Date getDatum() {
		return datum;
	}

	public KivetelnapTipus getTipus() {
		return tipus;
	}

	@SuppressWarnings("unused")
	private Kivetelnap() {
	}
}