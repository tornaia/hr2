package hu.interconnect.hr.dao;

import hu.interconnect.hr.domain.Kepzettseg;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class KepzettsegDAO extends BaseDAO<Kepzettseg, Integer> {

	public List<Kepzettseg> findAllByTsz(int tsz) {
		String sql = "SELECT k FROM Kepzettseg k WHERE k.szemelyitorzs.tsz = :tsz";
		Query query = em.createQuery(sql).setParameter("tsz", tsz);
		return query.getResultList();
	}
	
	public KepzettsegDAO() {
		super(Kepzettseg.class);
	}
}
