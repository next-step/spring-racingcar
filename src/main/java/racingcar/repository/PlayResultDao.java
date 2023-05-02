package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PlayResultDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Integer save(String winners, Integer count) {
        String sql = "INSERT INTO PLAY_RESULT (WINNERS, TRIAL_COUNT) VALUES (?, ?)";
        return jdbcTemplate.update(sql, winners, count);
    }
}
