package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hu.interconnect.hr.backend.api.dto.RendszerParameterekEditDTO.RendszerParameterSaveDTO;
import hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus;

@Entity
@Table(name="RENDSZER_PARAMETER")
public class RendszerParameter extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TIPUS")
	@Enumerated(EnumType.STRING)
	private RendszerParameterTipus tipus;

	@Column(name="ERTEK")
	private String ertek;

	public RendszerParameter(RendszerParameterTipus tipus, String ertek) {
		this.tipus = tipus;
		this.ertek = ertek;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void merge(RendszerParameterSaveDTO dto) {
		checkArgument(equalsAndNotNull(tipus, dto.getTipus()));

		ertek = dto.getErtek();
	}

	public RendszerParameterTipus getTipus() {
		return tipus;
	}

	public String getErtek() {
		return ertek;
	}

	@SuppressWarnings("unused")
	private RendszerParameter() {
	}
}
