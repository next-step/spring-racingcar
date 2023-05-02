package racingcar.infra;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.RacingCars;
import racingcar.domain.Winners;

@Repository
public class GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void savePlayResult(RacingCars racingCars, Winners winners) {
        StringBuilder query = new StringBuilder();
        query.append("  INSERT INTO PLAY_RESULT (trial_count, name, position, winners) ");
        query.append("         VALUES ( ?, ?, ?, ? )                                   ");

        jdbcTemplate.update(query.toString(), racingCars.getTrialCount(), racingCars.getNames(), racingCars.getPositions(), winners.getNames());
    }
}
