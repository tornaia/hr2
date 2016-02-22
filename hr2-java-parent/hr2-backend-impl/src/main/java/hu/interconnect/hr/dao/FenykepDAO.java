package hu.interconnect.hr.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.domain.Fenykep;

@Repository
public class FenykepDAO extends BaseDAO<Fenykep, Integer> {

	@SuppressWarnings("unchecked")
	public List<Fenykep> findBySzemelyitorzs(int tsz) {
		String sql = "SELECT f FROM Fenykep f WHERE f.szemelyitorzs.tsz = :tsz";
		Query query = em.createQuery(sql).setParameter("tsz", tsz);
		return query.getResultList();
	}

	public Optional<Fenykep> findMiniatur(int tsz) {
		String sql = "SELECT f FROM Fenykep f WHERE f.szemelyitorzs.tsz = :tsz AND f.miniatur = true";
		Query query = em.createQuery(sql).setParameter("tsz", tsz);
		return getSingleResult(query);
	}

	public FenykepDAO() {
		super(Fenykep.class);
	}
}
