package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.behavior.MovingStrategy;

public class RacingGame {
	private final List<Car> cars;
	private int round;

	public RacingGame(List<Car> cars) {
		this.cars = cars;
		this.round = 1;
	}

	public static RacingGame convertCarsToRacingGame(List<Car> cars) {
		return new RacingGame(cars);
	}

	public static RacingGame of(List<String> names) {
		List<Car> cars = names.stream()
			.map(name -> new Car(name,0))
			.collect(Collectors.toUnmodifiableList());
		return new RacingGame(cars);
	}

	public void play(int finalRound, MovingStrategy movingStrategy) {
		while (!isLastRound(finalRound)) {
			run(movingStrategy);
		}
	}

	void nextRound() {
		round++;
	}

	private boolean isLastRound(int finalRound) {
		return finalRound == round;
	}

	private void run(MovingStrategy movingStrategy) {
		cars.forEach(car -> moveCar(car, movingStrategy));
		nextRound();
	}

	private void moveCar(Car car, MovingStrategy movingStrategy) {
		car.move(movingStrategy);
	}

	public String getWinners() {
		return getNameOfWinnerCar();
	}

	public String getNameOfWinnerCar() {
		int winnerPosition = getMaxPosition();
		return cars.stream()
			.filter(car -> car.hasSamePosition(winnerPosition))
			.map(Car::getName)
			.collect(Collectors.joining(","));
	}

	private int getMaxPosition() {
		return cars.stream()
			.mapToInt(Car::getPosition)
			.max()
			.orElseThrow(RuntimeException::new);
	}

	public List<Car> getCars() {
		return cars;
	}
}
