package racingcar.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RaceResult {

  private String winners;
  private List<RacingCar> racingCars;
  public RaceResult(List<RacingCar> racingCars) {
    this.winners = getWinner(racingCars);
    this.racingCars = racingCars;
  }

  public String getWinner(List<RacingCar> racingCars) {
    // RacingCar 객체들을 position 속성값으로 내림차순으로 정렬
    int winner = 0;
    List<RacingCar> sortedCars = racingCars.stream()
        .sorted(Comparator.comparingInt(RacingCar::getPosition).reversed())
        .collect(Collectors.toList());

    int maxPosition = sortedCars.get(winner).getPosition();
    List<String> winners = sortedCars.stream()
        .filter(car -> car.getPosition() == maxPosition)
        .map(RacingCar::getName)
        .collect(Collectors.toList());

    String delimiter = ",";
    // 우승자 이름을 쉼표로 구분하여 String 형태로 반환
    return String.join(delimiter, winners);
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

  public String getWinners() {
    return winners;
  }

  public void setRacingCars(List<RacingCar> racingCars) {
    this.racingCars = racingCars;
  }

}