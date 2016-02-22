package hu.interconnect.hr.dao;

import java.util.List;

import javax.persistence.Query;

import hu.interconnect.hr.domain.AtvettEszkoz;

import org.springframework.stereotype.Repository;

@Repository
public class AtvettEszkozDAO extends BaseDAO<AtvettEszkoz, Integer> {

	@SuppressWarnings("unchecked")
	public List<AtvettEszkoz> findAllByTsz(int tsz) {
		String sql = "SELECT ae FROM AtvettEszkoz ae WHERE ae.szemelyitorzs.tsz = :tsz";
		Query query = em.createQuery(sql).setParameter("tsz", tsz);
		return query.getResultList();	}
	
	public AtvettEszkozDAO() {
		super(AtvettEszkoz.class);
	}
}
