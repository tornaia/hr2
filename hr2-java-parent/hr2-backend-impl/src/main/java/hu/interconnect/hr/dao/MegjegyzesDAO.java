package hu.interconnect.hr.dao;

import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.domain.Megjegyzes;

@Repository
public class MegjegyzesDAO extends BaseDAO<Megjegyzes, Integer> {

	public Optional<Megjegyzes> findBySzemelyitorzs(int tsz) {
		String sql = "SELECT m FROM Megjegyzes m " +
					 " left join fetch m.szemelyitorzs szt " +
					 " left join fetch szt.munkakoriBesorolas.munkakoriHistorikusAdatok " +
					 " left join fetch szt.kepzettsegek " +
					 " left join fetch szt.atvettEszkozok " +
					 " left join fetch szt.csalad.csaladtagok " +
					 " WHERE m.szemelyitorzs.tsz = :tsz";
		Query query = em.createQuery(sql).setParameter("tsz", tsz);
		return getSingleResult(query);
	}

	public MegjegyzesDAO() {
		super(Megjegyzes.class);
	}
}
