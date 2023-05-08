package racingcar.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarRoundResult;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoundHistoryRepository {

  private final JdbcTemplate jdbcTemplate;

  public void save(int gameId, List<RacingCarRoundResult> roundResults) {
    String query = "INSERT INTO ROUND_HISTORY(GAME_ID, ROUND, NAME, POSITION) VALUES(?, ?, ?, ?)";

    jdbcTemplate.batchUpdate(
        query,
        roundResults,
        roundResults.size(),
        (preparedStatement, roundResult) -> {
          preparedStatement.setInt(1, gameId);
          preparedStatement.setInt(2, roundResult.getRound());
          preparedStatement.setString(3, roundResult.getCarName());
          preparedStatement.setInt(4, roundResult.getPosition());
        });
  }
}
