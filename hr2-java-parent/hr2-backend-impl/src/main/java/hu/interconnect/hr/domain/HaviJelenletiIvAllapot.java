package hu.interconnect.hr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HAVI_JELENLETI_IV_ALLAPOT")
public class HaviJelenletiIvAllapot extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;

	@Column(name = "HONAP")
	@Temporal(TemporalType.DATE)
	private Date honap;
	
	@Column(name="SZERKESZTHETO")
	private boolean szerkesztheto;

	public HaviJelenletiIvAllapot(Date honap, boolean szerkesztheto) {
		this.honap = honap;
		this.szerkesztheto = szerkesztheto;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void zar() {
		szerkesztheto = false;
	}

	public void nyit() {
		szerkesztheto = true;
	}

	public boolean isSzerkesztheto() {
		return szerkesztheto;
	}

	@SuppressWarnings("unused")
	private HaviJelenletiIvAllapot() {
	}
}
