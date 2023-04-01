package racingcar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.RequiredArgsConstructor;
import racingcar.domain.Car;
import racingcar.domain.Cars;

@RequiredArgsConstructor
public class CarsRepository {
    private final JpaRepository<Car, Long> carRepository;

    @Transactional
    public void save(Cars cars) {
        cars.getCars().forEach(car -> carRepository.save(car));
    }
}
