package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCars {
    private final List<RacingCar> racingCars;

    public RacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public  RacingCars(String names) {
        this.racingCars = convertNamesToRacingCars(names);
    }

    private List<RacingCar> convertNamesToRacingCars(String names) {
        return Arrays.stream(this.convertNamesToArray(names))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());
    }

    private String[] convertNamesToArray(String names) {
        return names.split(",");
    }

}
