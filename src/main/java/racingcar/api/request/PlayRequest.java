package racingcar.api.request;

import racingcar.domain.Car;

import java.util.ArrayList;
import java.util.List;

public class PlayRequest {
    public PlayRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    private String names;
    private int count;

    public List<Car> makeCars() {
        List<Car> cars = new ArrayList<>();
        for (String name : this.names.split(",")) {
            cars.add(new Car(name.trim()));
        }
        return cars;
    }

    public int getCount() {
        return count;
    }
}
