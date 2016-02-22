package hu.interconnect.hr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import hu.interconnect.hr.backend.api.enumeration.EmailTemplateType;

@Entity
@Table(name="EMAIL_TEMPLATE")
public class EmailTemplate extends AbstractEntity {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private EmailTemplateType type;

	@Column(name="CONTENT")
	@Lob
	private String content;

	@Override
	public Integer getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}

	private EmailTemplate() {
	}
}
