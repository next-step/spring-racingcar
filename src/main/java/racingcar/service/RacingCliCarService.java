package racingcar.service;

import racingcar.domain.Cars;
import racingcar.domain.PlayResult;
import racingcar.view.RacingResultView;

public class RacingCliCarService {
    public void startRacing(String names, int targetDistance) {
        PlayResult playResult = new PlayResult();
        Cars cars = new Cars(null);
        cars.makeCars(playResult, names);
        cars.moveCars(targetDistance);
        RacingResultView.printResult(cars.getWinnerNames(), cars.getCars());
    }
}
