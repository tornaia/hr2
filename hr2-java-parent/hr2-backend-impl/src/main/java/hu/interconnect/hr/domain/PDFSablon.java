package hu.interconnect.hr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import hu.interconnect.hr.backend.api.enumeration.PDFSablonTipus;

@Entity
@Table(name="PDF_SABLON")
public class PDFSablon extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TIPUS")
	@Enumerated(EnumType.STRING)
	private PDFSablonTipus tipus;

	@Column(name="TARTALOM")
	@Lob
	private String tartalom;

	@Override
	public Integer getId() {
		return id;
	}

	public String getTartalom() {
		return tartalom;
	}

	private PDFSablon() {
	}
}
