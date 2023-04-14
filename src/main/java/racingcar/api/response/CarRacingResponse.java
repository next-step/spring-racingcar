package racingcar.api.response;

import java.util.List;

public class CarRacingResponse {

    private String winners;

    private final List<Car> racingCars;

    public CarRacingResponse(String winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return this.winners;
    }

    public List<Car> getRacingCars() {
        return this.racingCars;
    }
}
