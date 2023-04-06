package racingcar.domain;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class RacingGame {
    private final List<Car> participationCars;
    private final Random random;
    private int trialCount;

    public RacingGame(List<Car> participationCars, Random random) {
        this.participationCars = participationCars;
        this.random = random;
        this.trialCount = 0;
    }

    public void move() {
        participationCars.forEach(c -> c.move(random));
    }

    public void startGame(int moveCount) {
        for (int i = 0; i < moveCount; i++) {
            this.trialCount++;
            this.move();
        }
    }

    public List<Car> getParticipationCars() {
        return this.participationCars;
    }

    public List<Car> getRacingWinners() {
        Optional<Car> max = participationCars.stream().max(Car::compareTo);
        Car winner = max.orElseThrow();
        return participationCars.stream().filter(c -> c.isEqualLocation(winner)).collect(Collectors.toList());
    }

    public boolean isWinner(Car participationCar) {
        return getRacingWinners().stream().anyMatch(car -> car == participationCar);
    }

    public int getTrialCount() {
        return this.trialCount;
    }
}
