package racingcar.model;

import java.util.List;

import racingcar.domain.Car;

public class RacingResponse {
    private String winners;

    private List<Car> racingCars;

    public RacingResponse(String winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
