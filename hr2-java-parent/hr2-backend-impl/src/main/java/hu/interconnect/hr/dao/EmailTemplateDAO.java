package hu.interconnect.hr.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.backend.api.enumeration.EmailTemplateType;
import hu.interconnect.hr.domain.EmailTemplate;

@Repository
public class EmailTemplateDAO extends BaseDAO<EmailTemplate, Integer> {

	public EmailTemplate findByType(EmailTemplateType type) {
		String sql = "SELECT e FROM EmailTemplate e " +
						" WHERE e.type = :type ";

		Query query = em.createQuery(sql).setParameter("type", type);

		return (EmailTemplate) query.getSingleResult();
	}

	public EmailTemplateDAO() {
		super(EmailTemplate.class);
	}
}
