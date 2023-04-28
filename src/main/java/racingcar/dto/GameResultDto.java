package racingcar.dto;

import racingcar.RacingCar;

import java.util.List;

public class GameResultDto {
    private String winners;
    private List<RacingCar> racingCars;

    public GameResultDto(String winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }
}
