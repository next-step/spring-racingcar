package racingcar.dto;

import java.util.List;
import racingcar.model.RacingCar;

public class PlayResultOut {

    private String winners;
    private List<RacingCar> racingCars;

    public PlayResultOut(String winners, List<RacingCar> racingCars) {
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
