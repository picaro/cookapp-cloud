package com.op.cookcloud.helper.dbupdater;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map.Entry;
import java.util.SortedMap;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class Executor {

    protected final Log logger = LogFactory.getLog(getClass());
    
    private static final String RETRIVE_DB_VERSION_SQL = "SELECT max(MAJOR), max(MINOR) from DB_VERSION where MAJOR=(SELECT max(MAJOR) from DB_VERSION)";
	private static final String RECORD_NEW_VERSION_TO_DB = "INSERT into DB_VERSION(MAJOR, MINOR) values (:major, :minor);";
	private static final String HSQL_DRIVER_OLD = "org.hsqldb.jdbcDriver";
	private static final String HSQLDB_DRIVER_NEW = "org.hsqldb.jdbc.JDBCDriver";

	@Value("${jdbc.driver}")
	private String driverClassName;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
    @Autowired
	private ScriptReader scriptReader;

	public void executeUpdateScriptFiles(final SortedMap<Version, File> updateScriptFilesWithVersions) {
		if (!updateScriptFilesWithVersions.isEmpty()) {
			Version currentDbVersion = getCurrentDbVersion();
			for (Entry<Version, File> entry : updateScriptFilesWithVersions.entrySet()) {
				executeUpdateScriptFileIfNecessary(currentDbVersion, entry);
			}
		} else {

	    }
	}

	private void executeUpdateScriptFileIfNecessary(Version currentDbVersion, Entry<Version, File> entry) {
		Version updateScriptVersion = entry.getKey();
		if (currentDbVersion.isLessThan(updateScriptVersion)) {
			File updateScriptFile = entry.getValue();
			executeUpdateScriptFile(updateScriptFile, updateScriptVersion);
		}
	}

	private Version getCurrentDbVersion() {
		Version result = new Version();
		try {
			return readVersionFromDB();
		} catch (DataAccessException exception) {
			logger.warn("Can't retrive current db state from db", exception);
			result.setMajor(-1);
			result.setMinor(0);
		}
		return result;
	}

	private Version readVersionFromDB() {
		Version result;
		result = jdbcTemplate.query(RETRIVE_DB_VERSION_SQL, new ResultSetExtractor<Version>() {
			@Override
			public Version extractData(ResultSet rs) throws SQLException, DataAccessException {
				Version result = new Version();
				rs.next();
				result.setMajor(rs.getInt(1));
				result.setMinor(rs.getInt(2));
				return result;
			}
		});
		return result;
	}

	private void executeUpdateScriptFile(final File updateScriptFile, final Version version) {
		try {
			final String sql = scriptReader.readDbUpdateScriptFromFile(updateScriptFile);
			final String updateScriptFileName = updateScriptFile.getName();

			jdbcTemplate.execute(new StatementCallback<Object>() {
				@Override
				public Object doInStatement(Statement stmt) throws SQLException, DataAccessException {
					return executeUpdateScript(version, sql, updateScriptFileName, stmt);
				}

			});

		} catch (IOException e) {
            e.printStackTrace();
		} catch (DataAccessException dae) {
            dae.printStackTrace();
		}
	}

	private Object executeUpdateScript(final Version version, final String sql, final String updateScriptFileName, Statement stmt)
			throws SQLException {
		Connection connection = stmt.getConnection();
		boolean previousAutocommitState = connection.getAutoCommit();
		connection.setAutoCommit(false);
		try {
			logger.info("running script version: "+version);
            executeUpdateScriptAndUpdateDBVersion(version, sql, stmt);
			connection.commit();
			logger.info("Delta succesfully executed " + updateScriptFileName);
		} catch (SQLException e) {
			connection.rollback();
			logger.error("Exception occures when during delta processing " + updateScriptFileName, e);
			throw e;
		} finally {
			connection.setAutoCommit(previousAutocommitState);
		}
		return null;
	}

	private void executeUpdateScriptAndUpdateDBVersion(final Version version, final String sql, Statement stmt) throws SQLException {
		if (1==2 && isDatabaseSupportMultilineStatements(driverClassName)) {
			stmt.execute(sql);
		} else {
			logger.info("Database driver " + driverClassName + " doesn't support multi SQL statements");
			String[] statements = sql.split(";" + System.getProperty("line.separator"));
			for (String statement : statements) {
				if (!statement.trim().isEmpty()) {
                    //logger.info("Statement: "+statement);
                    stmt.execute(statement.trim());
				}
			}
		}
		stmt.getConnection().commit();
		updateDbVersion(version);
	}

	private static boolean isDatabaseSupportMultilineStatements(String driverClassName) {
		return !(HSQL_DRIVER_OLD.equals(driverClassName) || HSQLDB_DRIVER_NEW.equals(driverClassName));
	}

	private void updateDbVersion(final Version version) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("major", version.getMajor());
		params.addValue("minor", version.getMinor());
		template.update(RECORD_NEW_VERSION_TO_DB, params);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
