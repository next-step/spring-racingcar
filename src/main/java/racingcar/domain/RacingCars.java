package racingcar.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingCars {

    private final List<RacingCar> racingCars;

    public RacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public void move(int count) {
        IntStream.range(0, count)
                .forEach(index -> movable());
    }

    private void movable() {
        final Random random = new Random();

        this.racingCars.stream()
                .forEach(racingCar -> racingCar.move(random.nextInt(10)));
    }

    public String getNames() {
        return this.racingCars.stream()
                .map(RacingCar::getName)
                .collect(Collectors.joining(","));
    }

    public String getPositions() {
        return this.racingCars.stream()
                .map(RacingCar::getPosition)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public List<String> getWinner() {
        final int maxPosition = getMaxPosition();

        return this.racingCars.stream()
                .filter(racingCar -> racingCar.isEqualsPosition(maxPosition))
                .map(RacingCar::getName)
                .collect(Collectors.toList());
    }

    private int getMaxPosition() {
        return this.racingCars.stream()
                .mapToInt(RacingCar::getPosition)
                .max()
                .getAsInt();
    }

    public static RacingCars from(String names) {
        return new RacingCars(Arrays.stream(names.split(","))
                .map(String::trim)
                .map(RacingCar::new)
                .collect(Collectors.toList()));
    }
}
