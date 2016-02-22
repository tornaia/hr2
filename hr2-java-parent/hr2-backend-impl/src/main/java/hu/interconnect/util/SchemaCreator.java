package hu.interconnect.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.google.common.collect.Lists;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.enumeration.RendszerParameterTipus;
import hu.interconnect.hr.dao.RendszerParameterJdbcDAO;

/**
 * Collects and executes the schema init script(s).
 */
public class SchemaCreator {
	
	private static final Log LOG = LogFactory.getLog(SchemaCreator.class);

	private SqlScriptRunner sqlScriptRunner;

	private RendszerParameterJdbcDAO rendszerParameterJdbcDAO;

	private List<DataSource> dataSources;

	private String baseDirectory;

	private String type;

	private ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
	
	@Autowired
	public SchemaCreator(SqlScriptRunner sqlScriptRunner, RendszerParameterJdbcDAO rendszerParameterJdbcDAO, List<DataSource> dataSources) {
		this.sqlScriptRunner = sqlScriptRunner;
		this.rendszerParameterJdbcDAO = rendszerParameterJdbcDAO;
		this.dataSources = dataSources;
	}

	/**
	 * Executes the scripts: first fetches then sorts the {@link #SQL_SUFFIX} files in alphabetical order.
	 */
	public void init() throws IOException {
        logInitStart();

        List<Resource> sqlResources = getScripts();

        for (Resource sqlResource : sqlResources) {
			String currentVersion = rendszerParameterJdbcDAO.get(RendszerParameterTipus.INITSCRIPT.name());
        	int candidateSqlVersion = Integer.valueOf(sqlResource.getFilename().split("\\.")[0]);
        	if (currentVersion == null || Integer.valueOf(currentVersion) < candidateSqlVersion) {
        		sqlScriptRunner.executeScript(sqlResource);
				rendszerParameterJdbcDAO.ment("INITSCRIPT", Integer.toString(candidateSqlVersion));
        	}
        }

        logInitEnd();
	}

    private void logInitStart() {
        try {
            for (DataSource ds : dataSources) {
            	try (Connection connection = ds.getConnection()) {
					DatabaseMetaData databaseMetaData = connection.getMetaData();
					type = databaseMetaData.getDatabaseProductName();
					LOG.info("DB: " + databaseMetaData.getUserName() + " (" + type + ")");
            	}
            }
        } catch (Exception e) {
        	throw new ProgramozasiHiba("Cannot get connection/database username", e);
        }

        LOG.info("=================      Sema init start       ================");
    }

    private static void logInitEnd() {
    	LOG.info("=================      Sema init end       ================");
    }

    private List<Resource> getScripts() throws IOException {
        Resource[] topLevelSQLScripts = resourceResolver.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + baseDirectory + "/*" + SqlScriptRunner.SQL_SUFFIX);
		Resource[] typeLevelSQLScripts = resourceResolver.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + baseDirectory + "/" + type.toLowerCase() + "/*" + SqlScriptRunner.SQL_SUFFIX);
		List<Resource> resources = Lists.newArrayList(topLevelSQLScripts);
		resources.addAll(Arrays.asList(typeLevelSQLScripts));
        Collections.sort(resources, new Comparator<Resource>() {
			@Override
			public int compare(Resource o1, Resource o2) {
				String n1 = o1.getFilename().split("\\.")[0];
				String n2 = o2.getFilename().split("\\.")[0];
				return Integer.valueOf(n1).compareTo(Integer.valueOf(n2));
			}
		});
        return resources;
    }

	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}
}