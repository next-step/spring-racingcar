package racingcar.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacingCars {

    private List<RacingCar> racingCars;

    public RacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public static RacingCars of(List<String> names) {
        List<RacingCar> cars = names.stream()
                .map(RacingCar::new)
                .collect(Collectors.toList());

        return new RacingCars(cars);
    }

    public void move(MoveStrategy moveStrategy) {
        racingCars.forEach(car -> car.move(moveStrategy.move()));
    }

    public List<String> getMaxDistanceCarName() {
        List<RacingCar> cars = racingCars.stream()
                .collect(Collectors.groupingBy(RacingCar::getPosition))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new RuntimeException("우승자가 없습니다."));

        return cars.stream()
                .map(RacingCar::getName)
                .collect(Collectors.toList());
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

}
