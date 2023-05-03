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
}
