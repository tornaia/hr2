package hu.interconnect.hr.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import hu.interconnect.hr.backend.api.dto.ErtekkeszletElemEditDTO;
import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;

@Entity
@Table(name="ERTEKKESZLET_ELEM")
@Inheritance
@DiscriminatorColumn(name="TIPUS")
public abstract class ErtekkeszletElem extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;

	@Column(name="TIPUS", insertable=false, updatable=false)
	@Enumerated(EnumType.STRING)
	private ErtekkeszletElemTipus tipus;

	@Column(name="MEGNEVEZES_MAGYAR")
	private String megnevezesMagyar;

	@Column(name="MEGNEVEZES_ANGOL")
	private String megnevezesAngol;
	
	ErtekkeszletElem(int id) {
		this.id = id;
	}

	public ErtekkeszletElem(ErtekkeszletElemTipus tipus, String megnevezesMagyar, String megnevezesAngol) {
		this.tipus = tipus;
		this.megnevezesMagyar = megnevezesMagyar;
		this.megnevezesAngol = megnevezesAngol;
	}

	public void merge(ErtekkeszletElemEditDTO ertekkeszletElem) {
		checkArgument(equalsAndNotNull(id, ertekkeszletElem.getId()));
		
		megnevezesMagyar = ertekkeszletElem.getMegnevezesMagyar();
		megnevezesAngol = ertekkeszletElem.getMegnevezesAngol();
	}

	@Override
	public Integer getId() {
		return id;
	}

	public ErtekkeszletElemTipus getTipus() {
		return tipus;
	}

	public String getMegnevezesMagyar() {
		return megnevezesMagyar;
	}

	public String getMegnevezesAngol() {
		return megnevezesAngol;
	}

	protected ErtekkeszletElem() {
	}
}