package racingcar.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
@RequiredArgsConstructor
public class GameHistoryRepository {

  private final JdbcTemplate jdbcTemplate;

  public Integer save(String names, int count) {
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
