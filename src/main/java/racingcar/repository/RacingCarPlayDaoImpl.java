package racingcar.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dtos.response.PlayFinalPositionAndGame;
import racingcar.dtos.response.PlayResultWinnersAndGame;
import racingcar.dtos.response.GameOfPlayFinalPositionAndGame;
import racingcar.dtos.response.TotalNumberOfGame;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Qualifier("web")
public class RacingCarPlayDaoImpl implements RacingCarDao {
    private JdbcTemplate jdbcTemplate;

    public RacingCarPlayDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertWinner(String winner, Integer trialCount, Long game) {
        String SQL = "insert into PLAY_RESULT(winner, trial_count, game) values (?, ?, ?)";
        jdbcTemplate.update(SQL, winner, trialCount, game);
    }

    public void insertPlayPositionAndGame(String name, Integer position, Long game) {
        String SQL = "insert into PLAY_FINAL_POSITION_GAME(name, position, game) values (?, ?, ?)";
        jdbcTemplate.update(SQL, name, position, game);
    }

    public Optional<Long> selectLatestGame() {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "SELECT * FROM PLAY_FINAL_POSITION_GAME ORDER BY created_at desc limit 1", (resultSet, rowNum) -> {
                        GameOfPlayFinalPositionAndGame gameOfPlayFinalPositionAndGame = new GameOfPlayFinalPositionAndGame();
                        gameOfPlayFinalPositionAndGame.setGame(resultSet.getLong("game"));
                        return gameOfPlayFinalPositionAndGame.getGame();
                    }
            ));
        } catch (IncorrectResultSizeDataAccessException error) {
            return null;
        }
    }

    public Map<Long, List<PlayResultWinnersAndGame>> getWinnersAndGames() {
        return jdbcTemplate.query(
                        "SELECT * FROM PLAY_RESULT", (resultSet, rowNum) -> {
                            PlayResultWinnersAndGame playResultWinnersAndGame = new PlayResultWinnersAndGame();
                            playResultWinnersAndGame.setGame(resultSet.getLong("game"));
                            playResultWinnersAndGame.setWinners(resultSet.getString("winner"));
                            return playResultWinnersAndGame;
                        }
                ).stream().collect(Collectors.groupingBy(PlayResultWinnersAndGame::getGame));
    }

    public Map<Long, List<PlayFinalPositionAndGame>> getAllPlayFinalPositionAndGame() {
        return jdbcTemplate.query(
                        "SELECT * FROM PLAY_FINAL_POSITION_GAME", (result, rowNum) -> {
                            PlayFinalPositionAndGame playFinalPositionAndGame = new PlayFinalPositionAndGame();
                            playFinalPositionAndGame.setName(result.getString("name"));
                            playFinalPositionAndGame.setPosition(result.getInt("position"));
                            playFinalPositionAndGame.setGame(result.getLong("game"));
                            return playFinalPositionAndGame;
                        }
                ).stream().collect(Collectors.groupingBy(PlayFinalPositionAndGame::getGame));
    }

    public List<Long> getTotalNumberOfGame() {
        return jdbcTemplate.query(
                "SELECT game FROM PLAY_RESULT", (result, rowNum) -> {
                    TotalNumberOfGame tnog = new TotalNumberOfGame();
                    tnog.setGames(result.getLong("game"));
                    return tnog;
                }
        ).stream().map(TotalNumberOfGame::getGames).distinct().collect(Collectors.toList());
    }
}
