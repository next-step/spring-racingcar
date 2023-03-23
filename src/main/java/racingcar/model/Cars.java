package racingcar.model;


import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private List<Car> cars;

    private Cars(final List<Car> cars) {

        this.cars = cars;
    }

    public static Cars from(final String input) {
        String[] carNames = input.split(",");

        List<Car> values = Arrays.stream(carNames)
                .map(it -> new Car(Name.of(it)))
                .collect(Collectors.toUnmodifiableList());

        return new Cars(values);
    }

    public List<Car> getCars() {

        return cars;
    }

    public void run() {
        cars.stream().forEach(Car::move);
    }

    public List<Car> getWinners() {

        return cars.stream()
                .filter(it -> it.getPosition() == getBestPosition())
                .collect(Collectors.toList());
    }

    private int getBestPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max().orElse(0);
    }
}
