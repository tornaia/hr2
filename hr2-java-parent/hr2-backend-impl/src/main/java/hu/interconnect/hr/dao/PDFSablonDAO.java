package hu.interconnect.hr.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.backend.api.enumeration.PDFSablonTipus;
import hu.interconnect.hr.domain.PDFSablon;

@Repository
public class PDFSablonDAO extends BaseDAO<PDFSablon, Integer> {

	public PDFSablon findByTipus(PDFSablonTipus tipus) {
		String sql = "SELECT p FROM PDFSablon p " +
						" WHERE p.tipus = :tipus ";

		Query query = em.createQuery(sql).setParameter("tipus", tipus);

		return (PDFSablon) query.getSingleResult();
	}

	public PDFSablonDAO() {
		super(PDFSablon.class);
	}
}
