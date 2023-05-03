package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars = new ArrayList<>();

    public Cars(List<String> names) {
        names.forEach(name -> cars.add(new Car(name)));
    }

    public void play(int count){
        for (int i = 0; i < count; i++) {
            this.attempt(new RandomState());
        }
    }

    public void attempt(MoveState moveState) {
        cars.forEach(car -> car.go(moveState));
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getWinners() {
        int maxScore = cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.isSameNumber(maxScore))
                .collect(Collectors.toList());
    }

}
