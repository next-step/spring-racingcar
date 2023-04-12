package racingcar;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomCarMoveEntropy;
import racingcar.view.InputView;
import racingcar.view.ResultView;

public class RacingConsoleApplication {

    public static void main(String[] args) {
        Cars cars = InputView.getCarNames();
        int count = InputView.getTryNo();

        RacingGame racingGame = new RacingGame(cars, new RandomCarMoveEntropy());
        racingGame.startGame(count);

        ResultView.printCarsLocation(racingGame);
    }
}
