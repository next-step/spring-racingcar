package racingcar.fixture;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.util.List;

public class DatabaseCleanup extends AbstractTestExecutionListener {

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        JdbcTemplate jdbcTemplate = testContext.getApplicationContext().getBean(JdbcTemplate.class);
        setReferential(jdbcTemplate, false);
        List<String> tables = getTables(jdbcTemplate);
        truncateTables(jdbcTemplate, tables);
        setReferential(jdbcTemplate, true);
    }

    private void setReferential(JdbcTemplate jdbcTemplate, boolean useFlag) {
        final String sql = "SET REFERENTIAL_INTEGRITY " + useFlag;
        jdbcTemplate.execute(sql);
    }

    private List<String> getTables(JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT TABLE_NAME AS q FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    private void truncateTables(JdbcTemplate jdbcTemplate, List<String> tables) {
        final String sql = "TRUNCATE TABLE ";

        tables.forEach(v -> {
            String query = sql + v;
            jdbcTemplate.execute(query);
        });
    }

}
