package racingcar.model;

import java.util.List;

public class RacingResponse {
    private String winners;

    private List<Car> cars;

    public RacingResponse(String winners, List<Car> cars) {
        this.winners = winners;
        this.cars = cars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getCars() {
        return cars;
    }
}
