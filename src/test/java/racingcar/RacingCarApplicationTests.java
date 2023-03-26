package racingcar;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.PlayResult;
import racingcar.repository.CarRepository;
import racingcar.repository.PlayResultRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
class RacingCarApplicationTests {
	@Autowired
	private Cars cars;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private PlayResultRepository playResultRepository;

	@Transactional
	@Test
	void contextLoads() {
	}

	@Transactional
	@Test
	public void insertCarTest() {
		Car car = new Car("kia");
		carRepository.save(car);
		assertThat(carRepository.count(), is(1L));
	}

	@Transactional
	@Test
	public void insertPlayResultTest() {
		PlayResult playResult = new PlayResult();
		playResultRepository.save(playResult);
		assertThat(playResultRepository.count(), is(1L));
	}

	@Transactional
	@Test
	public void updateCarTest() {
		Car car = new Car("kia");
		car.setPosition(1);
		carRepository.save(car);
		assertThat(carRepository.findAll().get(0).getPosition(), is(1));

		car.setPosition(10);
		carRepository.save(car);
		assertThat(carRepository.findAll().get(0).getPosition(), is(10));
	}

	@Transactional
	@Test
	public void updatePlayResultTest() {
		PlayResult playResult = new PlayResult();
		playResult.setWinners("kia");
		playResultRepository.save(playResult);
		assertThat(playResultRepository.findAll().get(0).getWinners(), is("kia"));

		playResult.setWinners("volvo");
		playResultRepository.save(playResult);
		assertThat(playResultRepository.findAll().get(0).getWinners(), is("volvo"));
	}

	@Transactional
	@Test
	public void deleteCarTest() {
		Car car = new Car("kia");
		carRepository.save(car);
		assertThat(carRepository.count(), is(1L));

		carRepository.delete(car);
		assertThat(carRepository.count(), is(0L));
	}

	@Transactional
	@Test
	public void deletePlayResultTest() {
		PlayResult playResult = new PlayResult();
		playResult.setWinners("kia");
		playResultRepository.save(playResult);
		assertThat(playResultRepository.findAll().get(0).getWinners(), is("kia"));

		playResultRepository.delete(playResult);
		assertThat(playResultRepository.count(), is(0L));
	}

	@Transactional
	@Test
	public void makeCarsAndfindAllTest() {
		String names = "kia, volov, bmw";
		cars.makeCars(null, names);
		cars.save();

		List<Car> findCars = carRepository.findAll();
		assertThat(findCars.size(), is(3));
	}

	@Transactional
	@Test
	public void findAllPlayResultTest() {
		PlayResult playResult1 = new PlayResult();
		playResult1.setWinners("kia");
		playResultRepository.save(playResult1);

		PlayResult playResult2 = new PlayResult();
		playResult2.setWinners("volvo");
		playResultRepository.save(playResult2);

		List<PlayResult> playResults = playResultRepository.findAll();
		assertThat(playResults.size(), is(2));
	}

	@Test
	public void carToStringTest() {
		cars.makeCars("kia, volvo");
		assertThat(cars.toString(), is("[kia, volvo]"));
	}

	@Test
	public void makeCarsTest() {
		cars.makeCars("kia, volvo, bmw");
		assertThat(cars.getCars().size(), is(3));
	}

	@Test
	public void makeCarTest() {
		Car car = new Car("volvo");
		assertThat(car.getName(), is("volvo"));
	}

	@Test
	public void moveTest() {
		Car car = new Car("volvo");
		car.move(true);
		car.move(true);
		assertThat(car.getPosition(), is(2));
	}

	@Test
	public void getWinnerNamesTest() {
		cars.makeCars("kia, volvo");
		cars.getCars().get(0).move(true);
		cars.getCars().get(0).move(true);
		cars.getCars().get(0).move(true);
		cars.getCars().get(1).move(true);
		assertThat(cars.getWinnerNames(), is("kia"));
	}

	@Test
	public void getMaxDistanceTest() {
		cars.makeCars("kia, volvo");
		cars.getCars().get(0).move(true);
		cars.getCars().get(0).move(true);
		cars.getCars().get(0).move(true);
		cars.getCars().get(1).move(true);
		assertThat(cars.getMaxDistance(), is(3));
	}

	@Test
	public void getWinnerCarsTest() {
		cars.makeCars("kia, volvo, bmw");
		cars.getCars().get(0).move(true);
		cars.getCars().get(0).move(true);
		cars.getCars().get(0).move(true);
		cars.getCars().get(1).move(true);
		cars.getCars().get(1).move(true);
		cars.getCars().get(1).move(true);
		assertThat(cars.getWinnerCars().size(), is(2));
	}
}
