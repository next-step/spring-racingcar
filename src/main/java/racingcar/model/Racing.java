package racingcar.model;

import racingcar.model.Car;
import racingcar.model.CarResponse;
import racingcar.model.Cars;

import java.util.*;
import java.util.stream.Collectors;

public class Racing {

    private Cars cars;

    private int count;

    private int tryCount;

    public Racing (String input, int count) {

        this.cars = Cars.from(input);
        this.count = count;
        this.tryCount = 0;
    }

    public void startRacing() {
        cars.run();
        tryCount++;
    }

    public boolean isEnd() {
        if (tryCount < count) {
            return false;
        }

        return true;
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

    public List<Car> getWinners() {
        return cars.getWinners();
    }

    public String getWinnerNames() {
        return getWinners().stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }

    public List<CarResponse> getCarResponses() {
        return getCars().stream()
                .map(it -> new CarResponse(it.getName(), it.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
