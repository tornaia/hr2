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

import hu.interconnect.hr.backend.api.dto.KepzettsegEditDTO;

@Entity
@Table(name="KEPZETTSEG")
public class Kepzettseg extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="SZEMELYITORZS")
	private Szemelyitorzs szemelyitorzs;
	
	@Column(name="TIPUS")
	private String tipus;
	
	@Column(name="MEGNEVEZES")
	private String megnevezes;
	
	@Column(name="MOD_FOK")
	private String modFok;
	
	@Column(name = "EV")
	@Temporal(TemporalType.DATE)
	private Date ev;
	
	@Column(name = "ERVENYESSEG_VEGE")
	@Temporal(TemporalType.DATE)
	private Date ervenyessegVege;
	
	@Column(name="MEGJEGYZES")
	private String megjegyzes;
	
	public Kepzettseg(Szemelyitorzs szemelyitorzs, String tipus, String megnevezes, String modFok, Date ev, Date ervenyessegVege, String megjegyzes) {
		this.szemelyitorzs = szemelyitorzs;
		this.tipus = tipus;
		this.megnevezes = megnevezes;
		this.modFok = modFok;
		this.ev = ev;
		this.ervenyessegVege = ervenyessegVege;
		this.megjegyzes = megjegyzes;
	}
	
	public void merge(KepzettsegEditDTO dto) {
		checkArgument(equalsAndNotNull(id, dto.getId()));
		
		this.tipus = dto.getTipus();
		this.megnevezes = dto.getMegnevezes();
		this.modFok = dto.getModFok();
		this.ev = dto.getEv();
		this.ervenyessegVege = dto.getErvenyessegVege();
		this.megjegyzes = dto.getMegjegyzes();
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	public Szemelyitorzs getSzemelyitorzs() {
		return szemelyitorzs;
	}

	public String getTipus() {
		return tipus;
	}

	public String getMegnevezes() {
		return megnevezes;
	}

	public String getModFok() {
		return modFok;
	}

	public Date getEv() {
		return ev;
	}

	public Date getErvenyessegVege() {
		return ervenyessegVege;
	}

	public String getMegjegyzes() {
		return megjegyzes;
	}

	@SuppressWarnings("unused")
	private Kepzettseg() {
	}
}
