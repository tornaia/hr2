package hu.interconnect.hr.dao;

import hu.interconnect.hr.domain.IdozitettFuttatas;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class IdozitettFuttatasDAO extends BaseDAO<IdozitettFuttatas, Integer> {
	
	public IdozitettFuttatas findUtolsoByAzonosito(String azonosito) {
		String sql = "SELECT if FROM IdozitettFuttatas if " + 
					 " WHERE if.azonosito = :azonosito " +
					 " ORDER BY if.id DESC";

		Query query = em.createQuery(sql).setParameter("azonosito", azonosito).setMaxResults(1);
		
		List<IdozitettFuttatas> resultList = query.getResultList();
		
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	public List<IdozitettFuttatas> findByAzonosito(String azonosito) {
		String sql = "SELECT if FROM IdozitettFuttatas if " + 
		 			 " WHERE if.azonosito = :azonosito " +
		 			 " ORDER BY if.id DESC";

		Query query = em.createQuery(sql).setParameter("azonosito", azonosito);

		List<IdozitettFuttatas> resultList = query.getResultList();

		return resultList;
	}
	
	public IdozitettFuttatasDAO() {
		super(IdozitettFuttatas.class);
	}
}
