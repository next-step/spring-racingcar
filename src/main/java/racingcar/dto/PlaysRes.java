package racingcar.dto;

//import lombok.Getter;
import racingcar.domain.RacingCar;

import java.util.List;

public class PlaysRes {
    List<String> winners;
    List<RacingCar> racingCars;

    public PlaysRes(List<String> winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return this.winners;
    }

    public List<RacingCar> getRacingCars() {
        return this.racingCars;
    }


}
