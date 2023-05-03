package racingcar.domain.repository;

import racingcar.domain.RacingCar;

import java.util.List;

public interface RacingCarRepository {
    int insert(RacingCar racingCar);

    List<RacingCar> findByPlayResultId(List<Integer> playResultIds);
}
