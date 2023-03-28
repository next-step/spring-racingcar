package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.ToString;
import racingcar.utils.RacingCarUtils;

public class Cars {
    @ToString.Exclude
    private List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public static Cars makeCars(String carNames) {
        PlayResult playResult = new PlayResult();
        return makeCars(playResult, carNames);
    }

    public static Cars makeCars(PlayResult playResult, String carNames) {
        return new Cars(RacingCarUtils.stringToList(carNames).stream()
                .map(name -> new Car(playResult, name))
                .collect(Collectors.toList()));
    }

    public void moveCars(int targetDistance) {
        IntStream.range(0, targetDistance)
                .forEach(it -> {
                    moveCars(cars);
                });
    }

    private void moveCars(List<Car> cars) {
        cars.stream()
                .forEach(Car::move);
    }

    public String getWinnerNames() {
        List<String> winners = getWinnerCars();
        return String.join(", ", winners);
    }

    public int getMaxDistance() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .get();
    }

    public List<String> getWinnerCars() {
        return cars.stream()
                .filter(car -> car.isAtPosition(getMaxDistance()))
                .map(Car::getName)
                .collect(Collectors.toList());
    }

}
