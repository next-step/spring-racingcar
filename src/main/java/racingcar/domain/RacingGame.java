package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.behavior.MovingStrategy;

public class RacingGame {
	private final List<Car> cars;
	private final MovingStrategy movingStrategy;
	private int round;

	public RacingGame(List<Car> cars, MovingStrategy movingStrategy) {
		this.cars = cars;
		this.movingStrategy = movingStrategy;
		this.round = 1;
	}

	public static RacingGame of(List<Car> cars, MovingStrategy movingStrategy) {
		return new RacingGame(cars, movingStrategy);
	}

	public void play(int finalRound) {
		while (!isLastRound(finalRound)) {
			run();
		}
	}

	void nextRound() {
		round++;
	}

	private boolean isLastRound(int finalRound) {
		return finalRound == round;
	}

	private void run() {
		cars.forEach(this::moveCar);
		nextRound();
	}

	private void moveCar(Car car) {
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
}
