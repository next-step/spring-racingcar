package racingcar.view;

import java.util.List;

import racingcar.domain.Car;
import racingcar.dto.RacingCarResponseDto;

public class ResultView {
	private static final String RACING_END_MESSAGE = "실행 결과";
	private static final String DISPLAY_MOVEMENT = "-";

	private ResultView() {
	}

	public static void printRacingGameResult() {
		System.out.println(RACING_END_MESSAGE);
	}

	public static void printLocationsByCars(RacingCarResponseDto response) {
		printRacingGameResult();
		System.out.println("우승자: " + response.getWinners());
		System.out.println("경기 결과:");

		List<Car> cars = response.getRacingCars();
		for (final Car car : cars) {
			System.out.println(printLocationByCar(car));
		}
		System.out.println();
	}

	public static String printLocationByCar(final Car car) {
		return DISPLAY_MOVEMENT.repeat(Math.max(0, car.getPosition()));
	}
}

