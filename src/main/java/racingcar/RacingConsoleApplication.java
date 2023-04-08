package racingcar;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomCarMoveEntropy;
import racingcar.view.InputView;
import racingcar.view.ResultView;

import java.util.List;

public class RacingConsoleApplication {

    public static void main(String[] args) {
        List<Car> cars = InputView.getCarNames();
        int count = InputView.getTryNo();

        RacingGame racingGame = new RacingGame(cars, new RandomCarMoveEntropy());
        racingGame.startGame(count);

        ResultView.printCarsLocation(racingGame);
    }
}
