package racingcar.model;

import java.util.List;
import java.util.stream.Collectors;

public class RacingResponse {

    private String winners;

    private List<CarResponse> racingCars;

    public RacingResponse(String winners, List<CarResponse> carResponses) {
        this.winners = winners;
        this.racingCars = carResponses;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
