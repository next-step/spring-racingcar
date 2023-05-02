package racingcar.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarRoundResult;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RacingCarRepository {

  private final JdbcTemplate jdbcTemplate;

  public Integer saveGameHistory(String names, int count) {
    String query = "INSERT INTO GAME_HISTORY(TRIAL_COUNt, CAR_NAMES) VALUES(?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(
        connection -> {
          PreparedStatement preparedStatement =
              connection.prepareStatement(query, new String[] {"id"});
          preparedStatement.setInt(1, count);
          preparedStatement.setString(2, names.replaceAll(" ", ""));

          return preparedStatement;
        },
        keyHolder);

    return keyHolder.getKey().intValue();
  }

  public void saveRoundHistory(List<RacingCarRoundResult> roundResults) {
    String query = "INSERT INTO ROUND_HISTORY(GAME_ID, ROUND, NAME, POSITION) VALUES(?, ?, ?, ?)";

    jdbcTemplate.batchUpdate(
        query,
        roundResults,
        roundResults.size(),
        (preparedStatement, roundResult) -> {
          preparedStatement.setInt(1, roundResult.getGameId());
          preparedStatement.setInt(2, roundResult.getRound());
          preparedStatement.setString(3, roundResult.getCarName());
          preparedStatement.setInt(4, roundResult.getPosition());
        });
  }

  public void updateWinners(Integer gameId, String winners) {
    String query = "UPDATE GAME_HISTORY SET WINNERS = ? where ID = ?";

    jdbcTemplate.update(
        connection -> {
          PreparedStatement preparedStatement = connection.prepareStatement(query);

          preparedStatement.setString(1, winners);
          preparedStatement.setInt(2, gameId);

          return preparedStatement;
        });
  }
}
