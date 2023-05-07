package racingcar.business.domain;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RacingCars {

    private List<RacingCar> cars;

    public RacingCars(String racingCarNames) {
        if (racingCarNames == null) {
            racingCarNames = "";
        }
        this.cars = Stream.of(racingCarNames.split(",")).map(RacingCar::new)
                .collect(Collectors.toList());
    }

    public List<RacingCar> getCars() {
        return cars;
    }

    public String getWinnersNames() {
        Optional<Integer> maxPosition = cars.stream().map(RacingCar::getPosition).max(Integer::compareTo);
        return cars.stream().filter(racingCar -> racingCar.getPosition() == maxPosition.orElseThrow())
                .map(RacingCar::getName).collect(Collectors.joining(","));
    }

    public int startCarRacingGame(int count) {
        return IntStream.rangeClosed(1, count).reduce(0, (acc, n) -> {
            this.playRound(cars);
            return acc + 1;
        });
    }

    private void playRound(List<RacingCar> racingCars) {
        Random random = new Random();
        for (RacingCar racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }
}
