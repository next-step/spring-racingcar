package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import racingcar.domain.Car;
import racingcar.domain.Cars;

@Getter
public class PlaysResponseDto {
	private String winners;
	private List<Car> racingCars;

	public PlaysResponseDto(Cars cars) {
		this.winners = cars.getWinners().stream()
			.map(Car::getName)
			.collect(Collectors.joining(","));
		this.racingCars = cars.getCars();
	}
}
