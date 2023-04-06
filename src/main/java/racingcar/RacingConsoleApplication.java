package racingcar;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.ResultView;

import java.util.List;
import java.util.Random;

public class RacingConsoleApplication {
    public static void main(String[] args) {
        List<Car> cars = InputView.getCarNames();
        int count = InputView.getTryNo();


        RacingGame racingGame = new RacingGame(cars, new Random());
        for (int i = 0; i < count; i++) {
            racingGame.move();
            ResultView.printCarsLocation(racingGame);
        }

        ResultView.printRacingWinners(racingGame.getRacingWinners());
    }
}
