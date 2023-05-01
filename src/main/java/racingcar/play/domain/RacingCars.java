package racingcar.play.domain;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RacingCars {

    private static final int MAX_RANGE = 10;

    private final List<RacingCar> racingCars;

    public void playRound(int count) {
        Random random = new Random();
        IntStream.range(0, count)
            .forEach(i -> moveRacingCars(random));
    }

    private void moveRacingCars(Random random) {
        racingCars.forEach(racingCar -> racingCar.move(random.nextInt(MAX_RANGE)));
    }

    public String getWinners() {
        int maxPosition = calculateMaxPosition();
        return racingCars.stream()
            .filter(racingCar -> racingCar.getPosition() >= maxPosition)
            .map(RacingCar::getName)
            .collect(Collectors.joining(","));
    }

    private int calculateMaxPosition() {
        return racingCars.stream()
            .mapToInt(RacingCar::getPosition)
            .max()
            .orElseThrow();
    }
}
