package racingcar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import racingcar.domain.Car;

public interface SpringDataCarRepository extends JpaRepository<Car, Long>, CarRepository {
    @Override
    Optional<Car> findByName(String name);
}
