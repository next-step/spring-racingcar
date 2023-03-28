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
import racingcar.repository.CarsConsoleRepository;
import racingcar.repository.CarsRepository;
import racingcar.repository.CarWebRepository;
import racingcar.repository.PlayResultCliRepository;
import racingcar.repository.PlayResultRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
class RacingCarApplicationTests {
	@Autowired
	private CarWebRepository carWebRepository;

	@Autowired
	private PlayResultRepository playResultRepository;

	@Autowired
	private CarsConsoleRepository carsConsoleRepository;

	@Autowired
	private PlayResultCliRepository playResultCliRepository;

	private CarsRepository carsRepository;

	@Transactional
	@Test
	public void carCliRepositoryTest() {
		Cars cars = Cars.makeCars("kia, bmw");
		carsRepository = new CarsRepository(carsConsoleRepository);
		carsRepository.save(cars);
	}

	@Transactional
	@Test
	public void playResultCliRepositoryTest() {
		PlayResult playResult = new PlayResult();
		playResultCliRepository.save(playResult);
	}

	@Transactional
	@Test
	public void makeCarsAndfindAllTest() {
		String names = "kia, volov, bmw";
		Cars cars = Cars.makeCars(null, names);
		carsRepository = new CarsRepository(carWebRepository);
		carsRepository.save(cars);

		List<Car> findCars = carWebRepository.findAll();
		assertThat(findCars.size(), is(3));
	}

	@Transactional
	@Test
	public void insertCarTest() {
		Car car = new Car("kia");
		carWebRepository.save(car);
		assertThat(carWebRepository.count(), is(1L));
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
		carWebRepository.save(car);
		assertThat(carWebRepository.findAll().get(0).getPosition(), is(0));

		car.move(true);
		carWebRepository.save(car);
		assertThat(carWebRepository.findAll().get(0).getPosition(), is(1));
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
		carWebRepository.save(car);
		assertThat(carWebRepository.count(), is(1L));

		carWebRepository.delete(car);
		assertThat(carWebRepository.count(), is(0L));
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

}
