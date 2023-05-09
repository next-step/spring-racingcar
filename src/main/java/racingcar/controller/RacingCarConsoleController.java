package racingcar.controller;

import static racingcar.service.RacingCarService.makeRacingCars;
import static racingcar.service.RacingCarService.playRound;

import java.util.List;
import racingcar.domain.RaceResult;
import racingcar.domain.RacingCar;

public class RacingCarConsoleController {

  public RaceResult racing(String names, int count) {

    List<RacingCar> racingCars = makeRacingCars(names);

    for (int i = 0; i < count; i++) {
      playRound(racingCars);
    }
    return new RaceResult(racingCars);
  }

}
