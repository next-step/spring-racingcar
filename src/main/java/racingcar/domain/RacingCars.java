package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class RacingCars {
    private final List<RacingCar> racingCars;

    private RacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public static RacingCars of(List<String> playerNames) {
        return new RacingCars(playerNames.stream().map(RacingCar::new).collect(Collectors.toList()));
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }
}
