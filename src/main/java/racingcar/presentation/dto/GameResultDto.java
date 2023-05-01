package racingcar.presentation.dto;

import racingcar.RacingCar;
import racingcar.RacingCars;

import java.util.ArrayList;
import java.util.List;

public class GameResultDto {
    private String winners = "";
    private List<RacingCar> racingCars = new ArrayList<>();

    public GameResultDto(RacingCars racingCars) {
        this.winners = racingCars.getWinners();
        this.racingCars = racingCars.getCars();
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }
}
