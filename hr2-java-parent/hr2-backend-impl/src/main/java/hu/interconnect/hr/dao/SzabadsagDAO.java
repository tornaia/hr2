package hu.interconnect.hr.dao;

import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.SZABADSAG;
import static hu.interconnect.hr.backend.api.enumeration.FelhasznaltSzabadnapJelleg.TANULMANYI_SZABADSAG;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import hu.interconnect.hr.domain.Szabadsag;
import hu.interconnect.hr.domain.Szemelyitorzs;

@Repository
@SuppressWarnings("unchecked")
public class SzabadsagDAO extends BaseDAO<Szabadsag, Integer> {
	
	private static final Log LOG = LogFactory.getLog(SzabadsagDAO.class);
	
	public List<Szabadsag> findBySzemelyitorzsEsEv(int tsz, int ev) {
		String sql = "SELECT sz FROM Szabadsag sz " + 
					 " left join fetch sz.szemelyitorzs szt " +
					 " left join fetch szt.munkakoriBesorolas.munkakoriHistorikusAdatok " +
					 " left join fetch szt.kepzettsegek " +
					 " left join fetch szt.atvettEszkozok " +
					 " left join fetch szt.csalad.csaladtagok " +
					 " WHERE sz.szemelyitorzs.tsz = :tsz " +
					 " AND YEAR(sz.datum) = :ev ";
		
		Query query = em.createQuery(sql).setParameter("tsz", tsz).setParameter("ev", ev);
		
		return query.getResultList();
	}
	
	public void removeBySzemelyitorzsEsIdoszak(int tsz, Date kezdet, Date veg) {
		String sql = "DELETE FROM Szabadsag sz WHERE sz.szemelyitorzs.tsz = :tsz AND " +
					 " (:kezdet <= sz.datum AND " +
					 " :veg >= sz.datum)";
		
		Query query = em.createQuery(sql)
						.setParameter("tsz", tsz)
						.setParameter("kezdet", kezdet)
						.setParameter("veg", veg);
		
		int deletedEntityCount = query.executeUpdate();
		
		LOG.trace(String.format("Sikeresen toroltem %d sort", deletedEntityCount));
	}
	
	public Optional<Szabadsag> findBySzemelyitorzsEsNap(int tsz, Date nap) {
		String sql = "SELECT sz FROM Szabadsag sz " + 
					 " left join fetch sz.szemelyitorzs szt " +
					 " left join fetch szt.munkakoriBesorolas.munkakoriHistorikusAdatok " +
					 " left join fetch szt.kepzettsegek " +
					 " left join fetch szt.atvettEszkozok " +
					 " left join fetch szt.csalad.csaladtagok " +
					 " WHERE sz.szemelyitorzs.tsz = :tsz " +
					 " AND sz.datum = :nap";
		
		Query query = em.createQuery(sql).setParameter("tsz", tsz).setParameter("nap", nap);
		return getSingleResult(query);
	}

	public void removeBySzemelyitorzsEsNap(Szemelyitorzs szemelyitorzs, Date nap) {
		String sql = "DELETE FROM Szabadsag sz " +
					 " WHERE sz.szemelyitorzs = :szemelyitorzs " +
					 " AND sz.datum = :nap";
		
		Query query = em.createQuery(sql).setParameter("szemelyitorzs", szemelyitorzs).setParameter("nap", nap);
		
		int deletedEntityCount = query.executeUpdate();
		
		LOG.trace(String.format("Sikeresen toroltem %d sort", deletedEntityCount));
	}
	
	public void torolByNap(Date nap) {
		String sql = "DELETE FROM Szabadsag sz " +
					 " WHERE sz.datum = :nap";
			
		Query query = em.createQuery(sql).setParameter("nap", nap);
		
		int deletedEntityCount = query.executeUpdate();
		
		LOG.trace(String.format("Sikeresen toroltem %d sort", deletedEntityCount));
	}
	
	public List<Szabadsag> findBySzemelyitorzsEsAdottHaviIlletveAzelottiDeCsakAdottEviSzabadsagok(Szemelyitorzs szemelyitorzs, Date month) {
		String sql = "SELECT sz FROM Szabadsag sz " + 
					 " WHERE sz.szemelyitorzs = :szemelyitorzs AND " +
					 " (YEAR(sz.datum) = YEAR(:month) AND " +
					 " MONTH(sz.datum) <= MONTH(:month))";
					 
		Query query = em.createQuery(sql).setParameter("szemelyitorzs", szemelyitorzs).setParameter("month", month);
		return query.getResultList();
	}

	public List<Szabadsag> findBySzemelyitorzsEsIdopontElottiDeAdottEviCsakSzabadsagEsTanulmanyiSzabadsagok(Szemelyitorzs szemelyitorzs, Date month) {
		String sql = "SELECT sz FROM Szabadsag sz WHERE " + 
					 " sz.szemelyitorzs = :szemelyitorzs AND " +
					 " (YEAR(sz.datum) = YEAR(:month) AND MONTH(sz.datum) <= MONTH(:month)) AND " +
					 " (sz.jelleg = :szabadsag OR sz.jelleg = :tanulmanyiSzabadsag)";

		Query query = em.createQuery(sql).setParameter("szemelyitorzs", szemelyitorzs).setParameter("month", month).setParameter("szabadsag", SZABADSAG).setParameter("tanulmanyiSzabadsag", TANULMANYI_SZABADSAG);
		return query.getResultList();
	}
	
	public SzabadsagDAO() {
		super(Szabadsag.class);
	}
}
