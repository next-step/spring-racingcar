package racingcar.repository;

import racingcar.dtos.response.PlayFinalPositionAndGame;
import racingcar.dtos.response.PlayResultWinnersAndGame;

import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

public interface RacingCarDao {
    void insertWinner(String winner, Integer trialCount, Long game);
    void insertPlayPositionAndGame(String name, Integer position, Long game);

    OptionalLong selectLatestGame();

    Map<Long, List<PlayResultWinnersAndGame>> getWinnersAndGames();

    Map<Long, List<PlayFinalPositionAndGame>> getAllPlayFinalPositionAndGame();

    List<Long> getTotalNumberOfGame();
}
