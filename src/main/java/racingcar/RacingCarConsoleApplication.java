package racingcar;

import racingcar.application.Stadium;
import racingcar.domain.CarCollection;
import racingcar.dto.RacingCarResultDto;
import racingcar.view.CarRacingConsoleView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {

        CarRacingConsoleView view = new CarRacingConsoleView();

        String carNames = view.inputCarNames();
        int round = view.inputRound();

        // 경기를 만든다.
        Stadium stadium = new Stadium(new CarCollection(CarCollection.initCars(carNames)), round);

        view.beforeRacing();
        RacingCarResultDto resultDto = stadium.playRacingCar();

        // 끝난 경기에서 승자를 출력한다.
        view.showRacingResult(resultDto);
    }

}
