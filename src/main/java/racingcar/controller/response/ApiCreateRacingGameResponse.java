package racingcar.controller.response;

import java.util.List;

public class ApiCreateRacingGameResponse {

    String winners;
    List<ApiCreateRacingPlayerResponse> racingCars;


    public String getWinners() {
        return winners;
    }

    public List<ApiCreateRacingPlayerResponse> getRacingCars() {
        return racingCars;
    }

    public ApiCreateRacingGameResponse(String winners, List<ApiCreateRacingPlayerResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

}
