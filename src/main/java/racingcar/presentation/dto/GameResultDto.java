package racingcar.presentation.dto;

import racingcar.business.domain.RacingCar;
import racingcar.business.domain.RacingCars;

import java.util.List;

public class GameResultDto {
    private String winners = "";
    private List<RacingCar> racingCars;

    public GameResultDto(RacingCars racingCars) {
        this.winners = racingCars.getWinnersNames();
        this.racingCars = racingCars.getCars();
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }
}
