package hu.interconnect.hr.dao;

import java.util.List;

import javax.persistence.Query;

import hu.interconnect.hr.domain.Csaladtag;

import org.springframework.stereotype.Repository;

@Repository
public class CsaladtagDAO extends BaseDAO<Csaladtag, Integer> {

	@SuppressWarnings("unchecked")
	public List<Csaladtag> findAllByTsz(int tsz) {
		String sql = "SELECT cs FROM Csaladtag cs WHERE cs.szemelyitorzs.tsz = :tsz";
		Query query = em.createQuery(sql).setParameter("tsz", tsz);
		return query.getResultList();	}
	
	public CsaladtagDAO() {
		super(Csaladtag.class);
	}
}
