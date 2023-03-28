package racingcar.repository;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import racingcar.domain.Car;

@Transactional
public class CarsConsoleCustomRepositoryImpl implements CarsConsoleCustomRepository<Car> {
    private final EntityManager em;

    public CarsConsoleCustomRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Car save(Car car) {
        return null;
    }
}
