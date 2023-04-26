package racingcar.dto;

import java.util.List;

import racingcar.domain.Car;

public class RacingCarResponseDto {
    List<Car> racingCars;
    private final String winners;

    public RacingCarResponseDto(String winners, List<Car> racingCars) {
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
