package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import hu.interconnect.hr.backend.api.dto.AtvettEszkozEditDTO;

@Entity
@Table(name="ATVETT_ESZKOZ")
public class AtvettEszkoz extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="SZEMELYITORZS")
	private Szemelyitorzs szemelyitorzs;
	
	@Column(name="MEGNEVEZES")
	private String megnevezes;
	
	@Column(name="MEGJEGYZES")
	private String megjegyzes;
	
	@Column(name="EREDETI_NEV")
	public String eredetiNev;
	
	@Transient
	private byte[] adat;
	
	public AtvettEszkoz(Szemelyitorzs szemelyitorzs, String megnevezes, String megjegyzes, String eredetiNev,byte[] adat) {
		this.szemelyitorzs = szemelyitorzs;
		this.megnevezes = megnevezes;
		this.megjegyzes = megjegyzes;
		this.eredetiNev = eredetiNev;
		this.adat = adat;
	}

	public void merge(AtvettEszkozEditDTO dto) {
		checkArgument(equalsAndNotNull(id, dto.getId()));
		
		megnevezes = dto.getMegnevezes();
		megjegyzes = dto.getMegjegyzes();
		eredetiNev = dto.getEredetiNev();
	}

	@Override
	public Integer getId() {
		return id;
	}

	public Szemelyitorzs getSzemelyitorzs() {
		return szemelyitorzs;
	}

	public String getMegnevezes() {
		return megnevezes;
	}

	public String getMegjegyzes() {
		return megjegyzes;
	}

	public String getEredetiNev() {
		return eredetiNev;
	}
	
	public byte[] getAdat() {
		return adat;
	}
	
	@SuppressWarnings("unused")
	private AtvettEszkoz() {
	}
}
