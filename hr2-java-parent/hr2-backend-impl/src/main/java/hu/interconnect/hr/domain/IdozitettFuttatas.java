package hu.interconnect.hr.domain;

import static hu.interconnect.util.DateUtils.aktualisIdo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "IDOZITETT_FUTTATAS")
public class IdozitettFuttatas extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "AZONOSITO")
	private String azonosito;

	@Column(name = "INDITAS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inditas;

	@Column(name = "BEFEJEZES")
	@Temporal(TemporalType.TIMESTAMP)
	private Date befejezes;

	@Column(name = "EREDMENY")
	@Lob
	private String eredmeny;

	public IdozitettFuttatas(String azonosito, Date inditas) {
		this.azonosito = azonosito;
		this.inditas = inditas;
	}
	
	public void futastBefejez(String eredmeny) {
		this.befejezes = aktualisIdo();
		this.eredmeny = eredmeny;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getAzonosito() {
		return azonosito;
	}

	public Date getInditas() {
		return inditas;
	}

	public Date getBefejezes() {
		return befejezes;
	}

	public String getEredmeny() {
		return eredmeny;
	}

	@SuppressWarnings("unused")
	private IdozitettFuttatas() {
	}
}
