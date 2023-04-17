package racingcar.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.RacingCar;

@Repository
public class InsertDao {
    private final JdbcTemplate jdbcTemplate;

    public InsertDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertRacingHistory(RacingCar racingCar, int trial) {
        String sql = "insert into racing_history (round, trial_count, name, position) values (?, ?, ?, ?)";
        racingCar.getCars().getCars().forEach(
                it -> jdbcTemplate.update(sql, racingCar.getRound(), trial, it.getName(), it.getPosition())
        );
    }

    public void insertWinnerHistory(RacingCar racingCar) {
        String sql = "insert into winner_history(round, winners) values (?, ?)";
        jdbcTemplate.update(sql, racingCar.getRound(), racingCar.getWinner().getCarNames());
    }
}
