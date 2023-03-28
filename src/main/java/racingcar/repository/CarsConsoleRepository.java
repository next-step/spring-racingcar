package racingcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import racingcar.domain.Car;

public interface CarsConsoleRepository extends JpaRepository<Car, Long>, CarsConsoleCustomRepository<Car> {

}
