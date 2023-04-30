package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayDao {
    private JdbcTemplate jdbcTemplate;

    public PlayDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertWinner(String winner, Integer count) {
        String SQL = "insert into PLAY_RESULT(winner, trial_count) values (?, ?)";
        jdbcTemplate.update(SQL, new Object[]{winner, count});
    }

    public void insertPlayTravelDistance(String name, Integer position) {
        String SQL = "insert into PLAY_FINAL_TRAVEL_DISTANCE(name, position) values (?, ?)";
        jdbcTemplate.update(SQL, new Object[]{name, position});
    }
}
