package racingcar.repository;

import racingcar.dtos.response.PlayFinalPositionAndGame;
import racingcar.dtos.response.PlayResultWinnersAndGame;

import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

public class RacingCarPlayConsoleDaoImpl implements RacingCarDao{
    @Override
    public void insertWinner(String winner, Integer trialCount, Long game) {

    }

    @Override
    public void insertPlayPositionAndGame(String name, Integer position, Long game) {

    }

    @Override
    public OptionalLong selectLatestGame() {
        return OptionalLong.empty();
    }

    @Override
    public Map<Long, List<PlayResultWinnersAndGame>> getWinnersAndGames() {
        return null;
    }

    @Override
    public Map<Long, List<PlayFinalPositionAndGame>> getAllPlayFinalPositionAndGame() {
        return null;
    }

    @Override
    public List<Long> getTotalNumberOfGame() {
        return null;
    }
}
