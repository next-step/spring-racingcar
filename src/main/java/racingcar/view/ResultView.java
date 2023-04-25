package racingcar.view;

import com.google.gson.Gson;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingResultResponse;

public class ResultView {

    public static String showWinner(RacingCar racingCar) {
        Gson gson = new Gson();
        return gson.toJson(
                new RacingResultResponse(
                        racingCar.getWinnerNames(), racingCar.getCars()
                )
        );
    }
}
