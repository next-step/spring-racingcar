package racingcar.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RacingCarRepository {
  private final JdbcTemplate jdbcTemplate;

  public void insertRacingResult(String winners, int trialCnt) {
    String sql = "insert into RACING_GAME(WINNERS, TRIAL_COUNT,RACING_DATE) values(?, ?,NOW())";
    jdbcTemplate.update(sql, winners, trialCnt);
  }

}
