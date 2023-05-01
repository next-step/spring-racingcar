package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PlayResultDao implements PlayResultRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(String winners) {
        String sql = "INSERT INTO PLAY_RESULT (WINNERS) VALUES (?)";
        jdbcTemplate.update(sql, winners);
    }
}
