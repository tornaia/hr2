package test.integration;

import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;
import static hu.interconnect.util.StringUtils.NEW_LINE;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.util.SchemaCreator;
import hu.interconnect.util.SqlScriptRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-backend.xml", "classpath*:spring-security.xml", "classpath*:applicationContext-quartz.xml", "classpath*:applicationContext-backend-test.xml" })
public abstract class AbstractBackendIntTest extends AbstractJUnit4SpringContextTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Autowired
	private SqlScriptRunner sqlScriptRunner;

	@Autowired
	private SchemaCreator schemaCreator;

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private FakeMailSender fakeMailSender;
	
	@Before
	public void sematUresbeAllit() throws IOException, SQLException {
		ByteArrayResource resetScript = getResetScript(dataSource);
		sqlScriptRunner.executeScript(resetScript);
		schemaCreator.init();
		fakeMailSender.clear();
	}

    private static ByteArrayResource getResetScript(DataSource dataSource) throws SQLException {
    	String dbProductName;
    	String username;
    	try (Connection connection = dataSource.getConnection()) {
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			dbProductName = databaseMetaData.getDatabaseProductName();
			username = databaseMetaData.getUserName();
		}

    	String sep = NEW_LINE + "/" + NEW_LINE;
    	if (equalsAndNotNull(dbProductName, "H2")) {
    		String h2Reset = "DROP ALL OBJECTS DELETE FILES" + sep;
    		h2Reset += "CREATE SCHEMA IF NOT EXISTS " + username + sep;
    		h2Reset += "SET SCHEMA " + username + sep;
    		return new ByteArrayResource(h2Reset.getBytes());
		} else if (equalsAndNotNull(dbProductName, "MySQL")) {
			String schemaName = username.split("@")[0];
			String mysqlReset = "DROP DATABASE " + schemaName + sep;
			mysqlReset += "CREATE DATABASE " + schemaName + sep;
			mysqlReset += "USE " + schemaName + sep;
    		return new ByteArrayResource(mysqlReset.getBytes());
		} else {
			throw new ProgramozasiHiba("Nem lekezelt dbProduct: " + dbProductName);
		}
	}

	protected final void adminBejelentkezik() {
		felhasznaloBejelentkezik("admin", "admin");
	}

	protected void felhasznaloBejelentkezik(String username, String password) {
		felhasznaloKijelentkezik();
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Before
	@After
	public void felhasznaloKijelentkezik() {
		SecurityContextHolder.clearContext();
	}
}