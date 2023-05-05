package racingcar.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    racingCars.sort(Comparator.comparingInt(RacingCar::getPosition).reversed());

    // 우승자 이름 저장할 리스트
    List<String> winners = new ArrayList<>();
    int maxPosition = racingCars.get(0).getPosition();
    winners.add(racingCars.get(0).getName());

    for (int i = 1; i < racingCars.size(); i++) {
      RacingCar car = racingCars.get(i);
      if (car.getPosition() == maxPosition) {
        winners.add(car.getName());
      } else {
        break;
      }
    }

    // 우승자 이름을 쉼표로 구분하여 String 형태로 반환
    return String.join(",", winners);
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