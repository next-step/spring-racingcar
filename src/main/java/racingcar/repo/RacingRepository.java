package racingcar.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultDto;

@Repository
public class RacingRepository {

    private final JdbcTemplate jdbcTemplate;

    public RacingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertGameResult(GameResultDto gameResult) {
        String sql = "insert into PLAY_RESULT (winners) values (?)";
        jdbcTemplate.update(sql, gameResult.getWinners());
    }
}
