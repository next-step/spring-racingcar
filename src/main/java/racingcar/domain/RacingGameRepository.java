package racingcar.domain;

import racingcar.application.dto.GameResponse;

import java.util.List;

public interface RacingGameRepository {

    void save(GameResult gameResult);

    List<GameResponse> findAll();

}
