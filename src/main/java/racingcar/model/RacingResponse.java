package racingcar.model;

import java.util.List;

public class RacingResponse {

    private String winners;

    private List<Car> racingCars;

    public RacingResponse(String winners, List<Car> cars) {
        this.winners = winners;
        this.racingCars = cars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
