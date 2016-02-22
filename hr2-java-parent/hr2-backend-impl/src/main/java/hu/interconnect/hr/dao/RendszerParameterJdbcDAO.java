package hu.interconnect.hr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * JDBC implementacio a rendszer parameter tablahoz.
 */
public class RendszerParameterJdbcDAO extends JdbcDaoSupport {

	/**
	 * Menti az adott kulccsal az adott erteket a tablaba.
	 */
	public void ment(String kulcs, String ertek) {
		if (exists(kulcs)) {
            getJdbcTemplate().update("update RENDSZER_PARAMETER set ERTEK = ? where TIPUS = ?", new Object[] { ertek, kulcs });

		} else {
            getJdbcTemplate().update("insert into RENDSZER_PARAMETER (TIPUS, ERTEK) values (?, ?)", new Object[] { kulcs, ertek });
		}
	}

	/**
	 * Az adott kulcshoz visszaadja az erteket a dbbol, vagy nullt.
	 */
	public String get(String kulcs) {
		try {
			return getJdbcTemplate().queryForObject("select ERTEK from RENDSZER_PARAMETER where TIPUS = ?", new Object[] { kulcs }, String.class);
		} catch (DataAccessException e) {
			// nincs meg ilyen kulccsal ertek vagy nincs ilyen tabla
			return null;
		}
	}
	
	private boolean exists(String kulcs) {
        List<Map<String, Object>> ret = getJdbcTemplate().queryForList("select ERTEK from RENDSZER_PARAMETER where TIPUS = ?", new Object[] { kulcs });
        return ret.size() > 0;
	}
}
