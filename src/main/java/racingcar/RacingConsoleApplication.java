package racingcar;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomCarMoveDeterminer;
import racingcar.view.InputView;
import racingcar.view.ResultView;

public class RacingConsoleApplication {

    public static void main(String[] args) {
        Cars cars = InputView.getCarNames();
        int count = InputView.getTryNo();

        RacingGame racingGame = new RacingGame(cars, new RandomCarMoveDeterminer());
        racingGame.startGame(count);

        ResultView.printCarsLocation(racingGame);
    }
}
