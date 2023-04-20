package racingcar;

import com.google.gson.Gson;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingResultResponse;
import racingcar.dto.RacingStartDto;
import racingcar.service.RacingCarService;

import static racingcar.view.InputView.getCarNames;
import static racingcar.view.InputView.getTrial;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        Gson gson = new Gson();
        RacingCarService racingCarService = new RacingCarService();
        String inputString = getCarNames();
        int trial = getTrial();

        RacingStartDto racingStartDto = new RacingStartDto(inputString, trial);
        RacingCar racingCar = racingCarService.playRacingGame(racingStartDto, 1);

        String result = gson.toJson(
                new RacingResultResponse(
                        racingCar.getWinner().getCarNames(), racingCar.getCars()
                )
        );

        System.out.println(result);
    }
}