package hu.interconnect.hr.dao;

import hu.interconnect.hr.domain.JelenletiAdat;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class JelenletiAdatDAO extends BaseDAO<JelenletiAdat, Integer> {

	public List<JelenletiAdat> findBySzemelyitorzsEsHonap(int tsz, Date month) {
		String sql = "SELECT j FROM JelenletiAdat j WHERE " +
					 " j.szemelyitorzs.tsz = :tsz AND " +
					 " (YEAR(j.datum) = YEAR(:month) AND MONTH(j.datum) = MONTH(:month)) ";
		
		return em.createQuery(sql).setParameter("tsz", tsz).setParameter("month", month).getResultList();
	}

	public JelenletiAdatDAO() {
		super(JelenletiAdat.class);
	}
}