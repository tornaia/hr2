package hu.interconnect.hr.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.domain.Kivetelnap;

@Repository
@SuppressWarnings("unchecked")
public class KivetelnapDAO extends BaseDAO<Kivetelnap, Integer> {

	public List<Kivetelnap> findAllByDateDesc() {
		String sql = "SELECT k FROM Kivetelnap k";
		Query query = em.createQuery(sql);
		return query.getResultList();
	}

	public Optional<Kivetelnap> findByNap(Date datum) {
		String sql = "SELECT k FROM Kivetelnap k WHERE k.datum = :datum";

		Query query = em.createQuery(sql).setParameter("datum", datum);
		return getSingleResult(query);
	}

	public List<Kivetelnap> findByMonth(Date month) {
		String sql = "SELECT k FROM Kivetelnap k WHERE " +
					" (YEAR(k.datum) = YEAR(:month) AND MONTH(k.datum) = MONTH(:month)) ";

		return em.createQuery(sql).setParameter("month", month).getResultList();
	}
	
	public KivetelnapDAO() {
		super(Kivetelnap.class);
	}
}