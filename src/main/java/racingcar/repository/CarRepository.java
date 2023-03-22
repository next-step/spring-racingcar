package racingcar.repository;

import java.util.List;
import java.util.Optional;

import racingcar.domain.Car;

public interface CarRepository {
    // Car move(Car car);

    Car save(Car car);

    Optional<Car> findById(Long id);

    Optional<Car> findByName(String name);

    List<Car> findAll();
}
