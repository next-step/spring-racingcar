package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarCollection {

    private final List<Car> cars;

    public CarCollection(String carNames) {
        this(CarCollection.initCars(carNames));
    }

    public CarCollection(List<Car> cars) {
        validateDulicate(cars);
        this.cars = cars;
    }

    public void validateDulicate(List<Car> list) {
        Set<String> collectCarNames = list.stream().map(c -> c.getName()).collect(Collectors.toSet());

        if (collectCarNames.size() != list.size())
            throw new IllegalArgumentException("중복된 차이름이 존재합니다.");

    }

    public static List<Car> initCars(String inputCarNames) {

        String[] names = inputCarNames
                .replace(" ", "") // 공백제거
                .split(","); // 분리

        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            cars.add(new Car(names[i]));
        }

        return cars;
    }

    public List<Car> getCars() {
        return cars;
    }
}
