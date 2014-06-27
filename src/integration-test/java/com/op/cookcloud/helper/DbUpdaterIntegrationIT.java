package com.op.cookcloud.helper;

import com.op.cookcloud.exceptions.ServerException;
import com.op.cookcloud.helper.dbupdater.DbUpdater;
import com.op.cookcloud.helper.dbupdater.Executor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext-test.xml", "classpath:/hibernateContext-test.xml"})
@ActiveProfiles("integration")
public class DbUpdaterIntegrationIT extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String DB_VERSION_CLEANUP_SQL = "DELETE from DB_VERSION;";


    @Before
    public void cleanUpDBVersionTable() {
        executeStatement(DB_VERSION_CLEANUP_SQL);
    }

    @Autowired
    private DbUpdater updater;
    @Autowired
    private Executor executor;

    @Test
    public void emptyUpdateScriptDirectoryShouldGenerateAnException() {
        updater.setUpdateScriptsDirectoryPath("dbupdatetest");
        try {
            updater.updateDb();
            fail();
        } catch (ServerException se) {
            assertTrue(se.getMessage().contains("You must have at least one db update script"));
        }
    }

    @Test
    public void directoryShouldContainsFileWithSpecificNamesPattern() {
        updater.setUpdateScriptsDirectoryPath("dbupdatetest/mismatch");
        try {
            updater.updateDb();
            fail();
        } catch (ServerException se) {
            assertTrue(se.getMessage().contains("You must have at least one db update script"));
        }
    }




    @Test
    public void multipleDeltasShouldProcessFine() {
        updater.setUpdateScriptsDirectoryPath("dbupdatetest/success");
        updater.updateDb();
        assertThat(executor.getJdbcTemplate().queryForInt("select count(*) from test_table"), is(3));
        assertThat(executor.getJdbcTemplate().queryForInt("select count(*) from test_table2"), is(0));
        assertThat(executor.getJdbcTemplate().queryForInt("select count(*) from db_version"), is(2));
        updater.updateDb();
        assertThat(executor.getJdbcTemplate().queryForInt("select count(*) from test_table"), is(3));
        assertThat(executor.getJdbcTemplate().queryForInt("select count(*) from test_table2"), is(0));
        assertThat(executor.getJdbcTemplate().queryForInt("select count(*) from db_version"), is(2));
    }

    private void executeStatement(String sql) {
        executor.getJdbcTemplate().execute(sql);
    }
}
