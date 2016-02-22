package hu.interconnect.hr.dao;

import static hu.interconnect.hr.backend.api.enumeration.Allomanymod.AKTIV;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import hu.interconnect.hr.backend.api.dto.TszEsNevDTO;
import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.backend.api.enumeration.RiportAllomanymod;
import hu.interconnect.hr.domain.Allampolgarsag;
import hu.interconnect.hr.domain.FEOR;
import hu.interconnect.hr.domain.FoglalkozasiViszony;
import hu.interconnect.hr.domain.FoglalkoztatasJellege;
import hu.interconnect.hr.domain.Koltseghely;
import hu.interconnect.hr.domain.Munkakor;
import hu.interconnect.hr.domain.Szemelyitorzs;
import hu.interconnect.hr.domain.SzervezetiEgyseg;

@Repository
@SuppressWarnings("unchecked")
public class SzemelyitorzsDAO extends BaseDAO<Szemelyitorzs, Integer> {

	public Szemelyitorzs findByIdFetchelve(int tsz) {
		String sql = "SELECT szt FROM Szemelyitorzs szt" +
					 " left join fetch szt.munkakoriBesorolas.munkakoriHistorikusAdatok " +
					 " left join fetch szt.kepzettsegek " +
					 " left join fetch szt.atvettEszkozok " +
					 " left join fetch szt.csalad.csaladtagok " +
					 " WHERE szt.tsz = :tsz";

		Query query = em.createQuery(sql).setParameter("tsz", tsz);

		return (Szemelyitorzs) query.getSingleResult();
	}

	public List<Integer> findOsszesSzemelyitorzsTsz() {
		String sql = "SELECT szt.tsz FROM Szemelyitorzs szt";

		Query query = em.createQuery(sql);

		return query.getResultList();
	}

	public List<Integer> findOsszesAktivSzemelyitorzsTsz() {
		String sql = "SELECT szt.tsz FROM Szemelyitorzs szt " +
					 " WHERE szt.jogviszonyAdatok.allomanymod = :aktiv";

		Query query = em.createQuery(sql).setParameter("aktiv", Allomanymod.AKTIV);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findByAllampolgarsag(Allampolgarsag allampolgarsag) {
		String sql = "SELECT szt FROM Szemelyitorzs szt WHERE szt.szemelyiAdatok.allampolgarsag = :allampolgarsag";

		Query query = em.createQuery(sql).setParameter("allampolgarsag", allampolgarsag);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findByFEOR(FEOR fEOR) {
		String sql = "SELECT szt FROM Szemelyitorzs szt WHERE szt.munkakoriBesorolas.fEOR = :fEOR";

		Query query = em.createQuery(sql).setParameter("fEOR", fEOR);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findByFoglalkozasiViszony(FoglalkozasiViszony foglalkozasiViszony) {
		String sql = "SELECT szt FROM Szemelyitorzs szt WHERE szt.munkakoriBesorolas.foglalkozasiViszony = :foglalkozasiViszony";

		Query query = em.createQuery(sql).setParameter("foglalkozasiViszony", foglalkozasiViszony);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findByFoglalkoztatasJellege(FoglalkoztatasJellege foglalkoztatasJellege) {
		String sql = "SELECT szt FROM Szemelyitorzs szt WHERE szt.munkakoriBesorolas.foglalkoztatasJellege = :foglalkoztatasJellege";

		Query query = em.createQuery(sql).setParameter("foglalkoztatasJellege", foglalkoztatasJellege);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findByKoltseghely(Koltseghely koltseghely) {
		String sql = "SELECT szt FROM Szemelyitorzs szt WHERE szt.munkakoriBesorolas.koltseghely = :koltseghely";

		Query query = em.createQuery(sql).setParameter("koltseghely", koltseghely);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findByMunkakor(Munkakor munkakor) {
		String sql = "SELECT szt FROM Szemelyitorzs szt WHERE szt.munkakoriBesorolas.munkakor = :munkakor";

		Query query = em.createQuery(sql).setParameter("munkakor", munkakor);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findBySzervezetiEgyseg(SzervezetiEgyseg szervezetiEgyseg) {
		String sql = "SELECT szt FROM Szemelyitorzs szt WHERE szt.munkakoriBesorolas.szervezetiEgyseg = :szervezetiEgyseg";

		Query query = em.createQuery(sql).setParameter("szervezetiEgyseg", szervezetiEgyseg);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findByAllomanymod(Allomanymod allomanymod) {
		String sql = "SELECT szt FROM Szemelyitorzs szt" +
					 " WHERE szt.jogviszonyAdatok.allomanymod = :allomanymod " +
					 " ORDER BY szt.tsz";

		Query query = em.createQuery(sql).setParameter("allomanymod", allomanymod);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findByAllomanymodFetchelve(Allomanymod allomanymod) {
		String sql = "SELECT DISTINCT szt FROM Szemelyitorzs szt " +
					 " left join fetch szt.munkakoriBesorolas.munkakoriHistorikusAdatok " +
					 " left join fetch szt.kepzettsegek " +
					 " left join fetch szt.atvettEszkozok " +
					 " left join fetch szt.csalad.csaladtagok " +
					 " WHERE szt.jogviszonyAdatok.allomanymod = :allomanymod";

		Query query = em.createQuery(sql).setParameter("allomanymod", allomanymod);

		return query.getResultList();
	}

	public List<TszEsNevDTO> getOsszesSzemelyitorzsTszEsNevListaban() {
		String sql = "SELECT new " + TszEsNevDTO.class.getCanonicalName() + "(szt.tsz, szt.szemelyiAdatok.vezeteknev, szt.szemelyiAdatok.keresztnev) " +
					 " FROM Szemelyitorzs szt " +
					 " ORDER BY szt.szemelyiAdatok.vezeteknev ASC, szt.szemelyiAdatok.keresztnev ASC";

		Query query = em.createQuery(sql);

		return query.getResultList();
	}

	public List<TszEsNevDTO> getOsszesAktivSzemelyitorzsTszEsNevListaban() {
		String sql = "SELECT new " + TszEsNevDTO.class.getCanonicalName() + "(szt.tsz, szt.szemelyiAdatok.vezeteknev, szt.szemelyiAdatok.keresztnev) " +
					 " FROM Szemelyitorzs szt " +
					 " WHERE szt.jogviszonyAdatok.allomanymod = :aktiv " +
					 " ORDER BY szt.szemelyiAdatok.vezeteknev ASC, szt.szemelyiAdatok.keresztnev ASC";

		Query query = em.createQuery(sql).setParameter("aktiv", Allomanymod.AKTIV);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findOsszes() {
		String sql = "SELECT szt FROM Szemelyitorzs szt ORDER BY szt.tsz";

		Query query = em.createQuery(sql);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findOsszesFetchelve() {
		String sql = "SELECT DISTINCT szt FROM Szemelyitorzs szt" +
					 " left join fetch szt.munkakoriBesorolas.munkakoriHistorikusAdatok " +
					 " left join fetch szt.kepzettsegek " +
					 " left join fetch szt.atvettEszkozok " +
					 " left join fetch szt.csalad.csaladtagok " +
					 " ORDER BY szt.tsz";

		Query query = em.createQuery(sql);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findByRiportAllomanymod(RiportAllomanymod riportAllomanymod) {
		if (riportAllomanymod == RiportAllomanymod.OSSZES) {
			return findOsszes();
		} else {
			return findByAllomanymod(riportAllomanymod.getAllomanymod());
		}
	}

	public List<Szemelyitorzs> findByRiportAllomanymodFetchelve(RiportAllomanymod riportAllomanymod) {
		if (riportAllomanymod == RiportAllomanymod.OSSZES) {
			return findOsszesFetchelve();
		} else {
			return findByAllomanymodFetchelve(riportAllomanymod.getAllomanymod());
		}
	}

	public List<Szemelyitorzs> findAktivJogviszonyuSzemelyitorzsekByProbaidoVege(Date nap) {
		String sql = "SELECT szt FROM Szemelyitorzs szt " +
		 			 " WHERE szt.jogviszonyAdatok.allomanymod = :aktiv" +
		 			 " AND szt.jogviszonyAdatok.probaidoVege = :probaidoVege";

		Query query = em.createQuery(sql).setParameter("aktiv", AKTIV).setParameter("probaidoVege", nap);

		return query.getResultList();
	}

	public List<Szemelyitorzs> findAktivJogviszonyuSzemelyitorzsekByJogviszonyVege(Date month) {
		String sql = "SELECT szt FROM Szemelyitorzs szt WHERE " +
					 " szt.jogviszonyAdatok.allomanymod = :aktiv AND " +
					 " (YEAR(szt.jogviszonyAdatok.jogviszonyVege) = YEAR(:month) AND MONTH(szt.jogviszonyAdatok.jogviszonyVege) = MONTH(:month)) ";

		return em.createQuery(sql).setParameter("aktiv", AKTIV).setParameter("month", month).getResultList();
	}

	public SzemelyitorzsDAO() {
		super(Szemelyitorzs.class);
	}
}
