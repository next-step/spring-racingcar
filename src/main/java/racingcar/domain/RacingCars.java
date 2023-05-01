package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingCars {

    private final List<RacingCar> racingCars;
    private final int trialCount;

    public RacingCars(List<RacingCar> racingCars, int trialCount) {
        this.racingCars = racingCars;
        this.trialCount = trialCount;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public void move() {
        IntStream.range(0, this.trialCount)
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

    public static RacingCars from(String names, int trialCount) {
        return new RacingCars(Arrays.stream(names.split(","))
                .map(String::trim)
                .map(RacingCar::new)
                .collect(Collectors.toList()), trialCount);
    }
}
