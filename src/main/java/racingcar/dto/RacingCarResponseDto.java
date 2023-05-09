package racingcar.dto;

import java.util.List;

import racingcar.domain.Car;

public class RacingCarResponseDto {
	private final String winners;
	List<Car> racingCars;

	public RacingCarResponseDto(String winners, List<Car> racingCars) {
		this.winners = winners;
		this.racingCars = racingCars;
	}

	public static RacingCarResponseDto of(GameResult gameResult, List<GameHistory> histories) {
		String winners = gameResult.getWinners();
		List<Car> cars = histories.stream()
			.map(history -> new Car(history.getName(), history.getPosition()))
			.collect(Collectors.toList());
		return new RacingCarResponseDto(winners, cars);
	}

	public static RacingCarResponseDto from(String winners, RacingGame racingGame) {
		List<Car> cars = racingGame.getCars();
		return new RacingCarResponseDto(winners, cars);
	}

	public String getWinners() {
		return winners;
	}

	public List<Car> getRacingCars() {
		return racingCars;
	}
}
