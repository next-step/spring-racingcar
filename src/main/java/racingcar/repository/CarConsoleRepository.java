package racingcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import racingcar.domain.Car;

public interface CarConsoleRepository extends JpaRepository<Car, Long>, CarConsoleCustomRepository<Car> {

}
