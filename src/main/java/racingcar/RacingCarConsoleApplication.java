package racingcar;

import java.util.List;

import racingcar.domain.Cars;
import racingcar.domain.RandomState;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

	public static void main(String[] args) {
		InputView inputView = new InputView();
		List<String> carNames = inputView.inputCarNames();
		int numberOfAttempts = inputView.inputNumberOfAttempts();

		OutputView outputView = new OutputView();
		outputView.printResultMessage();

		Cars cars = new Cars(carNames);
		for (int i = 0; i < numberOfAttempts; i++) {
			cars.attempt(new RandomState());
			outputView.printStatusFromScores(cars.getCars());
		}
		outputView.printWinners(cars.getWinners());
	}
}
