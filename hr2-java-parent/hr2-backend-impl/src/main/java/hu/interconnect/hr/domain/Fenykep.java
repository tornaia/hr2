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

import hu.interconnect.hr.backend.api.dto.FenykepekSaveDTO.FenykepSaveDTO;

@Entity
@Table(name="FENYKEP")
public class Fenykep extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="SZEMELYITORZS")  
	private Szemelyitorzs szemelyitorzs;
	
	@Column(name="EREDETI_NEV")
	private String eredetiNev;
	
	@Column(name="MINIATUR")
	private boolean miniatur;
	
	public Fenykep(Szemelyitorzs szemelyitorzs, String eredetiNev, boolean miniatur) {
		this.szemelyitorzs = szemelyitorzs;
		this.eredetiNev = eredetiNev;
		this.miniatur = miniatur;
	}
	
	public void merge(FenykepSaveDTO dto) {
		checkArgument(equalsAndNotNull(id, dto.getId()));
		
		this.miniatur = dto.isMiniatur();
	}

	@Override
	public Integer getId() {
		return id;
	}

	public Szemelyitorzs getSzemelyitorzs() {
		return szemelyitorzs;
	}

	public String getEredetiNev() {
		return eredetiNev;
	}

	public boolean isMiniatur() {
		return miniatur;
	}

	@SuppressWarnings("unused")
	private Fenykep() {
	}
}
