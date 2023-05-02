package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RacingCarGame {

    private final RacingCars racingCars;

    private final Integer count;

    private RacingCarGame(RacingCars racingCars, Integer count) {
        this.racingCars = racingCars;
        this.count = count;
    }

    public static RacingCarGame of(RacingCars racingCars, Integer count) {
        return new RacingCarGame(racingCars, count);
    }

    public void play() {
        for (int i = 0; i < count; i++) {
            playRound(racingCars);
        }
    }

    public String winners() {
        List<String> winners = new ArrayList<>();

        for (RacingCar racingCar : racingCars.getRacingCars()) {
            if (racingCar.getPosition() == racingCars.getMaxPosition()) {
                winners.add(racingCar.getName());
            }
        }

        return String.join(", ", winners);
    }

    private void playRound(RacingCars racingCars) {
        Random random = new Random();
        for (RacingCar racingCar : racingCars.getRacingCars()) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }
}
