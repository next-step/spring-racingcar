package racingcar.domain;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RacingCars {
  private final List<RacingCar> values;

  public RacingCars(Names names) {
    this.values = generate(names);
  }

  private List<RacingCar> generate(Names names) {
    return names.getValues().stream().map(RacingCar::new).collect(Collectors.toList());
  }

  public String getWinners() {
    Integer maximumPosition = getPositions().getMaximumPosition();
    return getNamesBy(maximumPosition);
  }

  private Positions getPositions() {
    List<Integer> positions =
        this.values.stream().map(RacingCar::getPosition).collect(Collectors.toList());

    return new Positions(positions);
  }

  private String getNamesBy(Integer maximumPosition) {
    List<String> winners =
        this.values.stream()
            .filter(car -> car.getPosition() == maximumPosition)
            .map(RacingCar::getName)
            .collect(Collectors.toList());

    return String.join(",", winners.toArray(String[]::new));
  }

  public void playEachRound(MovementPolicy movementPolicy) {
    this.values.forEach(car -> car.move(movementPolicy.action()));
  }
}
