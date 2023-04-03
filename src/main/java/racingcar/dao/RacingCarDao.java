package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.RacingCar;

@Repository("racingCarDao")
public class RacingCarDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertPlayResult(String winners, int trialCnt) {
        String sql = "insert into PLAY_RESULT(WINNERS, TRIAL_CNT) values(?, ?)";
        jdbcTemplate.update(sql, winners, trialCnt);
    }

    public void insertPlayHistory(RacingCar racingCar) {
        String sql = "insert into PLAY_HISTORY(PLAYER, POSITION) values(?, ?);";
        jdbcTemplate.update(sql, racingCar.getName(), racingCar.getPosition());
    }
}
