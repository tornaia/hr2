package hu.interconnect.hr.dao;

import hu.interconnect.hr.domain.MunkakoriHistorikusAdat;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MunkakoriHistorikusAdatDAO extends BaseDAO<MunkakoriHistorikusAdat, Integer> {

	@SuppressWarnings("unchecked")
	public List<MunkakoriHistorikusAdat> findAllByTsz(int tsz) {
		String sql = "SELECT mha FROM MunkakoriHistorikusAdat mha WHERE mha.szemelyitorzs.tsz = :tsz";
		Query query = em.createQuery(sql).setParameter("tsz", tsz);
		return query.getResultList();
	}
	
	public MunkakoriHistorikusAdatDAO() {
		super(MunkakoriHistorikusAdat.class);
	}
}
