package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public Cars(String... namesArray) {
        this.cars = Arrays.stream(namesArray).map(Car::new).collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public List<String> names() {
        return this.cars.stream().map(Car::getName).collect(Collectors.toList());
    }

    public boolean isMatch(Car car) {
        return this.cars.stream().anyMatch(c -> c == car);
    }

    private Cars samePositionCars(Car car) {
        List<Car> carList = this.cars.stream()
                .filter(c -> c.isEqualLocation(car))
                .collect(Collectors.toList());

        return new Cars(carList);
    }

    public Cars maxCars() {
        Car max = this.cars.stream().max(Car::compareTo).orElseThrow();
        return samePositionCars(max);
    }

    public void move(CarMoveDeterminer carMoveDeterminer) {
        this.cars.forEach(c -> c.move(carMoveDeterminer));
    }
}
