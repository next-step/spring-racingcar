package racingcar.data;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.RacingCars;

@Repository
public class RacingRepository {

    private final JdbcTemplate jdbcTemplate;

    public RacingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertGameResult(RacingCars racingCars) {
        if (racingCars.getWinners().isEmpty()) {
            throw new DataIntegrityViolationException("winners 가 비어있습니다.");
        }
        String sql = "insert into PLAY_RESULT (winners) values (?)";
        jdbcTemplate.update(sql, racingCars.getWinners());
    }
}
