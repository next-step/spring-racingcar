package racingcar.domain;

import racingcar.domain.dto.PlayResultDto;
import racingcar.domain.strategy.MovingStrategy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cars {

    private final List<Car> cars;

    public Cars(String[] carNames) {
        this.cars = carList(carNames);
    }

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    private List<Car> carList(String[] carNames) {
        return Stream.of(carNames)
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void move(MovingStrategy movingStrategy) {
        cars.forEach(car -> car.move(movingStrategy));
    }

    public List<PlayResultDto> getPlayResults() {
        return cars.stream().map(Car::getPlayResult).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars1 = (Cars) o;
        return Objects.equals(cars, cars1.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }
}
