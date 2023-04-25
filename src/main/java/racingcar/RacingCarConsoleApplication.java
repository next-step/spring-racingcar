package racingcar;

import racingcar.domain.RacingCar;
import racingcar.dto.RacingStartDto;
import racingcar.service.RacingCarService;

import static racingcar.view.InputView.getCarNames;
import static racingcar.view.InputView.getTrial;
import static racingcar.view.ResultView.showWinner;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingCarService racingCarService = new RacingCarService();
        String inputString = getCarNames();
        int trial = getTrial();

        RacingStartDto racingStartDto = new RacingStartDto(inputString, trial);
        RacingCar racingCar = racingCarService.playRacingGame(racingStartDto, 1);

        System.out.println(showWinner(racingCar));
    }
}