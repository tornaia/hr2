package hu.interconnect.hr.dao;

import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.domain.EvesSzabadsagAdat;

@Repository
public class EvesSzabadsagAdatDAO extends BaseDAO<EvesSzabadsagAdat, Integer> {
	
	public Optional<EvesSzabadsagAdat> findBySzemelyitorzsEsEv(int tsz, int ev) {
		String sql = "SELECT a FROM EvesSzabadsagAdat a " +
					 " left join fetch a.szemelyitorzs szt " +
					 " left join fetch szt.munkakoriBesorolas.munkakoriHistorikusAdatok " +
					 " left join fetch szt.kepzettsegek " +
					 " left join fetch szt.atvettEszkozok " +
					 " left join fetch szt.csalad.csaladtagok " +
					 " WHERE a.szemelyitorzs.tsz = :tsz " +
					 " AND a.ev = :ev";
		Query query = em.createQuery(sql).setParameter("tsz", tsz).setParameter("ev", ev);
		
		return getSingleResult(query);
	}

	EvesSzabadsagAdatDAO() {
		super(EvesSzabadsagAdat.class);
	}
}
