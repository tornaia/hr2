package hu.interconnect.hr.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import hu.interconnect.hr.domain.Felhasznalo;
import hu.interconnect.hr.domain.Szemelyitorzs;

@Repository
@SuppressWarnings("unchecked")
public class FelhasznaloDAO extends BaseDAO<Felhasznalo, Integer> implements UserDetailsService {
	
	private static final Log LOG = LogFactory.getLog(FelhasznaloDAO.class);

	@Override
	public Felhasznalo loadUserByUsername(String username) {
		Optional<Felhasznalo> felhasznalo = findByNev(username);

		if (!felhasznalo.isPresent()) {
			LOG.info("Nincsen felhasznalo a kovetkezo nevvel:" + username);
			throw new UsernameNotFoundException(username);
		}

		return felhasznalo.get();
	}
	
	public List<Felhasznalo> findOsszesFelhasznaloFetchelve() {
		String sql = "SELECT DISTINCT f FROM Felhasznalo f " +
		 			 " left join fetch f.szemelyitorzs szt " +
					 " left join fetch szt.munkakoriBesorolas.munkakoriHistorikusAdatok " +
					 " left join fetch szt.kepzettsegek " +
					 " left join fetch szt.atvettEszkozok " +
					 " left join fetch szt.csalad.csaladtagok " +
					 " ORDER BY f.nev ";

		Query query = em.createQuery(sql);

		return query.getResultList();
	}

	public List<Felhasznalo> findBySzemelyitorzs(Szemelyitorzs szemelyitorzs) {
		String sql = "SELECT f FROM Felhasznalo f " +
					 " WHERE f.szemelyitorzs = :szemelyitorzs ";

		Query query = em.createQuery(sql).setParameter("szemelyitorzs", szemelyitorzs);

		return query.getResultList();
	}

	public List<Felhasznalo> findOsszesAktiv() {
		String sql = "SELECT f FROM Felhasznalo f " +
					 " WHERE f.enabled = true ";

		Query query = em.createQuery(sql);

		return query.getResultList();
	}

	public Optional<Felhasznalo> findByNev(String nev) {
		String sql = "SELECT f FROM Felhasznalo f " +
					" WHERE f.nev = :nev ";

		Query query = em.createQuery(sql).setParameter("nev", nev);

		return getSingleResult(query);
	}
	
	public FelhasznaloDAO() {
		super(Felhasznalo.class);
	}
}