package hu.interconnect.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Executes an sql script.
 */
public class SqlScriptRunner extends JdbcDaoSupport {

	public static final String SQL_SUFFIX = ".sql";
	public static final char COMMAND_SEPARATOR = '/';

	private static final String COMMAND_SEPARATOR_PATTERN = "\\n" + COMMAND_SEPARATOR;

	private static final Log LOG = LogFactory.getLog(SchemaCreator.class);

	/**
	 * Executes an sql script from an intput stream. The commands must end with
	 * {@link #COMMAND_SEPARATOR}, which must be on a new line.
	 *
	 * @param sqlResource which contains the sql commands to execute.
	 */
	public void executeScript(Resource sqlResource) throws IOException {
		LOG.info("Executing SQL file: " + sqlResource.toString());

		String content = IOUtils.toString(sqlResource.getInputStream()).trim();
		for (String query : content.split(COMMAND_SEPARATOR_PATTERN)) {
			LOG.info("Executing SQL query:\n" + query);
			getJdbcTemplate().execute(query);
		}
	}
}
