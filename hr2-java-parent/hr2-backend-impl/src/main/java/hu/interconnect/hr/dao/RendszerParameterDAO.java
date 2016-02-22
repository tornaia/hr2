package hu.interconnect.hr.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus;
import hu.interconnect.hr.domain.RendszerParameter;

@Repository
@SuppressWarnings("unchecked")
public class RendszerParameterDAO extends BaseDAO<RendszerParameter, Integer> {

	public RendszerParameter findByTipus(RendszerParameterTipus tipus) {
		String sql = "SELECT rp FROM RendszerParameter rp WHERE rp.tipus = :tipus ";

		Query query = em.createQuery(sql).setParameter("tipus", tipus);

		return (RendszerParameter) query.getSingleResult();
	}

	public List<RendszerParameter> findOsszesRendszerParameter() {
		String sql = "SELECT rp FROM RendszerParameter rp " +
		 			 " ORDER BY rp.tipus ";

		Query query = em.createQuery(sql);

		return query.getResultList();
	}

	public RendszerParameterDAO() {
		super(RendszerParameter.class);
	}
}
