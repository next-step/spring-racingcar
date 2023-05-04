package racingcar.domain;

import java.util.List;

public class RaceResult {
  private String winners;
  private List<RacingCar> racingCars;

  public String getWinners() {
    return winners;
  }

  public RaceResult(String winners, List<RacingCar> racingCars) {
    this.winners = winners;
    this.racingCars = racingCars;
  }

  public void setWinners(String winners) {
    this.winners = winners;
  }

  public List<RacingCar> getRacingCars() {
    return racingCars;
  }

  public void setRacingCars(List<RacingCar> racingCars) {
    this.racingCars = racingCars;
  }
}