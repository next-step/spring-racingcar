package racingcar.domain;

import java.util.List;
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

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

}
