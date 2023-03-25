package racingcar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import racingcar.domain.Car;

@SpringBootTest
public class CarRepositoryTest {

    private final CarRepository carRepository;

    @Autowired
    public CarRepositoryTest(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Test
    void repositoryTest() {
        Car car = new Car();
        car.setName("testCar");
        car.setPosition(1);
        carRepository.save(car);

        Car findCar = carRepository.findByName("testCar").orElse(null);
        assertThat(findCar.getName(), is("testCar"));
        assertThat(findCar.getPosition(), is(1));
    }
}
