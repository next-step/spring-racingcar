package racingcar.dto;

import racingcar.Car;

import java.util.List;

public class ResultRacingDTO {
    private final String winners;
    private final List<Car> racingCars;

    public ResultRacingDTO(String winners, List<Car> racingCars) {
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
