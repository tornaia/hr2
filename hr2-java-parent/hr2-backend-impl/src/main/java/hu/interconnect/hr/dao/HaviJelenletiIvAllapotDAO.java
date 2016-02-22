package hu.interconnect.hr.dao;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.domain.HaviJelenletiIvAllapot;

@Repository
public class HaviJelenletiIvAllapotDAO extends BaseDAO<HaviJelenletiIvAllapot, Integer> {

	public Optional<HaviJelenletiIvAllapot> findByHonap(Date month) {
		String sql = "SELECT a FROM HaviJelenletiIvAllapot a WHERE " +
					 " (YEAR(a.honap) = YEAR(:month) AND MONTH(a.honap) = MONTH(:month)) ";
		
		
		Query query = em.createQuery(sql).setParameter("month", month);
		return getSingleResult(query);
	}
	
	public HaviJelenletiIvAllapotDAO() {
		super(HaviJelenletiIvAllapot.class);
	}
}