package racingcar.repository;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import racingcar.domain.Car;

@Transactional
public class CarConsoleCustomRepositoryImpl implements CarConsoleCustomRepository<Car> {
    private final EntityManager em;

    public CarConsoleCustomRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Car save(Car car) {
        return null;
    }
}
