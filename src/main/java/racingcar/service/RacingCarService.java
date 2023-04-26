package racingcar.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.behavior.MovingStrategy;
import racingcar.behavior.RandomMovingStrategy;
import racingcar.domain.Car;
import racingcar.dto.RacingCarResponseDto;

@Service
public class RacingCarService {
    private List<Car> cars;
    private MovingStrategy movingStrategy;
    private int finalRound;
    private int round;

    public RacingCarResponseDto game(String nameOfCars, int round) {
        List<Car> initCars = createCars(nameOfCars);
        this.finalRound = round;
        this.cars = initCars;
        this.movingStrategy = new RandomMovingStrategy();
        while (!isLastRound()) {
            progressRound();
        }
        String namesOfWinnerCars = getNameOfWinnerCar();
        return new RacingCarResponseDto(namesOfWinnerCars, cars);
    }

    private List<Car> createCars(String nameOfCars) {
        String[] nameOfCarsArr = nameOfCars.replaceAll("\"", "").split(",");
        return Arrays.stream(nameOfCarsArr)
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void progressRound() {
        run();
    }

    void nextRound() {
        round = round + 1;
    }

    public boolean isLastRound() {
        return finalRound == round;
    }

    int getMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElseThrow(RuntimeException::new);
    }

    public String getNameOfWinnerCar() {
        int winnerPosition = getMaxPosition();
        return cars.stream()
                .filter(car -> car.hasSamePosition(winnerPosition))
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    public void run() {
        cars.forEach(this::moveCar);
        nextRound();
    }

    private void moveCar(Car car) {
        car.move(movingStrategy);
    }
}
