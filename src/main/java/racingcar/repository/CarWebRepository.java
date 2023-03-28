package racingcar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import racingcar.domain.Car;

public interface CarWebRepository extends JpaRepository<Car, Long> {
    Car save(Car car);

    Optional<Car> findById(Long id);

    List<Car> findAll();
}
