package racingcar.domain;

import racingcar.application.dto.GameRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RacingCars {

    private final List<RacingCar> racingCars;

    public RacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public void move() {
        final Random random = new Random();

        this.racingCars.stream()
                .forEach(racingCar -> racingCar.move(random.nextInt(10)));
    }

    public String getWinner(String delimiter) {
        final int maxPosition = getMaxPosition();

        return this.racingCars.stream()
                .filter(racingCar -> racingCar.isEqualsPosition(maxPosition))
                .map(RacingCar::getName)
                .collect(Collectors.joining(delimiter));
    }

    private int getMaxPosition() {
        return this.racingCars.stream()
                .mapToInt(RacingCar::getPosition)
                .max()
                .getAsInt();
    }

    public static RacingCars of(GameRequest gameRequest, String delimiter) {
        return new RacingCars(Arrays.stream(gameRequest.getNames().split(delimiter))
                .map(String::trim)
                .map(RacingCar::new)
                .collect(Collectors.toList()));
    }
}
