package test.integration.db;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hu.interconnect.hr.backend.api.enumeration.Allomanymod;
import hu.interconnect.hr.dao.SzemelyitorzsDAO;
import hu.interconnect.hr.domain.Szemelyitorzs;
import test.builder.JogviszonyAdatokBuilder;
import test.builder.MunkakoriBesorolasBuilder;
import test.builder.OrvosiVizsgalatBuilder;
import test.builder.SzabadsagnyilvantartasBuilder;
import test.builder.SzemelyiAdatokBuilder;
import test.builder.SzemelyitorzsBuilder;
import test.integration.AbstractBackendIntTest;

public class NullIsNotEmptyStringIntTest extends AbstractBackendIntTest {

	@Autowired
	private SzemelyitorzsDAO szemelyitorzsDAO;
	
	@Test
	public void verifyThatNormalSaveWorks() {
		Szemelyitorzs okSzemelyitorzs = new SzemelyitorzsBuilder()
												.tsz(1)
												.szemelyiAdatok(new SzemelyiAdatokBuilder()
														.vezeteknev("vezeteknev")
														.keresztnev("keresztnev")
														.letrehoz())
												.jogviszonyAdatok(new JogviszonyAdatokBuilder()
														.allomanymod(Allomanymod.AKTIV)
														.letrehoz())
												.munkakoriBesorolas(new MunkakoriBesorolasBuilder()
														.uzemanyagElszamolas(false)
														.letrehoz())
												.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
														.gyakorisag(12)
														.letrehoz())
												.szabadsagNyilvantartas(new SzabadsagnyilvantartasBuilder()
														.megvaltottSzabadsag(0)
														.letrehoz())
												.letrehoz();
						
		szemelyitorzsDAO.persist(okSzemelyitorzs);
	}
	
	// h2 treats varchar2 NULLs as empty strings if MODE=MySQL
	// h2 treats varchar2 NULLs as NULLs if MODE=Oracle
	// in short:
	// 1. if MODE is set to MySQL then the test fails
	// 2. if the database handles NULLs as empty string then the test fails
	@Test
	public void nullIsNotHandledAsEmptyString() {
		Szemelyitorzs nullVezeteknevSzemelyitorzs = new SzemelyitorzsBuilder()
												.tsz(1)
												.szemelyiAdatok(new SzemelyiAdatokBuilder()
														.vezeteknev(null)
														.keresztnev("keresztnev")
														.letrehoz())
												.jogviszonyAdatok(new JogviszonyAdatokBuilder()
														.allomanymod(Allomanymod.AKTIV)
														.letrehoz())
												.munkakoriBesorolas(new MunkakoriBesorolasBuilder()
														.uzemanyagElszamolas(false)
														.letrehoz())
												.orvosiVizsgalat(new OrvosiVizsgalatBuilder()
														.gyakorisag(12)
														.letrehoz())
												.szabadsagNyilvantartas(new SzabadsagnyilvantartasBuilder()
														.megvaltottSzabadsag(0)
														.letrehoz())
												.letrehoz();
		try {
			szemelyitorzsDAO.persist(nullVezeteknevSzemelyitorzs);
			fail();
		} catch (Exception e) {
			assertTrue(SQLException.class.isAssignableFrom(ExceptionUtils.getRootCause(e).getClass()));
		}
	}
}
