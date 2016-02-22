package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.interconnect.hr.backend.api.dto.MegjegyzesSaveDTO;

@Entity
@Table(name="MEGJEGYZES")
public class Megjegyzes extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@OneToOne(optional=false)
	@JoinColumn(name="SZEMELYITORZS")
	private Szemelyitorzs szemelyitorzs;
	
	@Column(name="SZOVEG")
	private String szoveg;
	
	public Megjegyzes(Szemelyitorzs szemelyitorzs, String szoveg) {
		this.szemelyitorzs = szemelyitorzs;
		this.szoveg = szoveg;
	}
	
	public void merge(MegjegyzesSaveDTO dto) {
		checkArgument(equalsAndNotNull(szemelyitorzs.getTsz(), dto.getTsz()));
		
		szoveg = dto.getSzoveg();
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	public Szemelyitorzs getSzemelyitorzs() {
		return szemelyitorzs;
	}

	public String getSzoveg() {
		return szoveg;
	}

	@SuppressWarnings("unused")
	private Megjegyzes() {
	}
}
