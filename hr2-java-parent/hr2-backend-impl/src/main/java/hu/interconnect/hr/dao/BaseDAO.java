package hu.interconnect.hr.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for JPA DAO objects.
 * 
 * BaseDAO, a kulcs alapjan is generikus.
 * 
 * @param <T>
 *            Az az entitas amelyikre a DAO operaciok ertelmezettek.
 * @param <K>
 *            Az entitas kulcsanak tipusa.
 */
@Transactional
public class BaseDAO<T, K> {

	private static final String QUERY_CACHE_HINT = "org.hibernate.cacheable";

	@PersistenceContext
	protected EntityManager em;

	protected final Class<T> entitasTipus;

	protected BaseDAO(Class<T> entitasTipus) {
		this.entitasTipus = entitasTipus;
	}

	/**
	 * Saves the entity.
	 * 
	 * @see EntityManager#persist(Object)
	 */
	public <N extends T> void persist(N object) {
		em.persist(object);
	}

	/**
	 * Finds the object identified by its id.
	 * 
	 * @see EntityManager#find(Class, Object)
	 */
	public T findById(K id) {
		return em.find(entitasTipus, id);
	}

	/**
	 * Finds all.
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return em.createQuery("select e from " + entitasTipus.getSimpleName() + " e").getResultList();
	}

	/**
	 * Creates a query.
	 * 
	 * @see EntityManager#createQuery(String)
	 * 
	 */
	public Query createQuery(String qlString) {
		return em.createQuery(qlString);
	}

	public Query createCacheableQuery(String qlString) {
		return em.createQuery(qlString).setHint(QUERY_CACHE_HINT, true);
	}

	/**
	 * Removes object by id
	 * 
	 * @see EntityManager#find(Class, Object)
	 * @see EntityManager#remove(Object)
	 * 
	 * @return true if the object was found and removed, false otherwise
	 */
	public boolean delete(K id) {
		Object o = findById(id);
		if (o == null) {
			return false;
		}
		em.remove(o);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected static <R> Optional<R> getSingleResult(Query query) {
	    List<R> resultList = query.setMaxResults(2).getResultList();
	    R singleResult = DataAccessUtils.singleResult(resultList);
	    return Optional.ofNullable(singleResult);
	}
}