package racingcar.repository;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dtos.response.PlayFinalTravelDistance;
import racingcar.dtos.response.PlayResultWinnersAndGame;
import racingcar.dtos.response.PlayFinalTravelDistanceGame;
import racingcar.dtos.response.TotalNumberOfGame;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PlayDao {
    private JdbcTemplate jdbcTemplate;

    public PlayDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertWinner(String winner, Integer count, Long game) {
        String SQL = "insert into PLAY_RESULT(winner, trial_count, game) values (?, ?, ?)";
        jdbcTemplate.update(SQL, winner, count, game);
    }

    public void insertPlayTravelDistance(String name, Integer position, Long game) {
        String SQL = "insert into PLAY_FINAL_TRAVEL_DISTANCE(name, position, game) values (?, ?, ?)";
        jdbcTemplate.update(SQL, name, position, game);
    }

    public Long selectLatestGame() {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM PLAY_FINAL_TRAVEL_DISTANCE ORDER BY created_at desc limit 1", (resultSet, rowNum) -> {
                        PlayFinalTravelDistanceGame playFinalTravelDistanceGame = new PlayFinalTravelDistanceGame();
                        playFinalTravelDistanceGame.setGame(resultSet.getLong("game"));
                        return playFinalTravelDistanceGame.getGame();
                    }
            );
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

    public Map<Long, List<PlayFinalTravelDistance>> getAllPlayFinalTravelDistance() {
        return jdbcTemplate.query(
                        "SELECT * FROM PLAY_FINAL_TRAVEL_DISTANCE", (result, rowNum) -> {
                            PlayFinalTravelDistance playFinalTravelDistance = new PlayFinalTravelDistance();
                            playFinalTravelDistance.setName(result.getString("name"));
                            playFinalTravelDistance.setPosition(result.getInt("position"));
                            playFinalTravelDistance.setGame(result.getLong("game"));
                            return playFinalTravelDistance;
                        }
                ).stream().collect(Collectors.groupingBy(PlayFinalTravelDistance::getGame));
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
