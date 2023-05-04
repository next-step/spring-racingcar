package racingcar.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RaceResult {

  private String winners;
  private List<RacingCar> racingCars;

  private LocalDateTime racingDate;

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
  public static String getWinnersString(List<RacingCar> racingCars) {
    int maxPosition = racingCars.stream()
        .mapToInt(RacingCar::getPosition)
        .max()
        .orElseThrow();
    List<String> winners = racingCars.stream()
        .filter(car -> car.getPosition() == maxPosition)
        .map(RacingCar::getName)
        .collect(Collectors.toList());
    return String.join(",", winners);
  }
}