package hu.interconnect.hr.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.backend.api.enumeration.ErtekkeszletElemTipus;
import hu.interconnect.hr.domain.ErtekkeszletElem;

@Repository
public class ErtekkeszletElemDAO extends BaseDAO<ErtekkeszletElem, Integer> {

	@SuppressWarnings("unchecked")
	public List<ErtekkeszletElem> getOsszesAdottTipusu(ErtekkeszletElemTipus tipus) {
		String sql = "SELECT elem FROM ErtekkeszletElem elem ";
		if (tipus != null) {
			sql += " WHERE elem.tipus = :tipus ";
		}
		sql += " ORDER BY tipus ASC, megnevezesMagyar ASC ";
		
		Query query = em.createQuery(sql);
		if (tipus != null) {
			query.setParameter("tipus", tipus);
		}
		
		return query.getResultList();
	}
	
	public ErtekkeszletElemDAO() {
		super(ErtekkeszletElem.class);
	}
}