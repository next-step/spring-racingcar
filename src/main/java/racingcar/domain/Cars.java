package racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getCarNames() {
        List<String> carNames = new ArrayList<>();
        cars.forEach(car ->
                carNames.add(car.getName()));
        return String.join(",", carNames);
    }
}
