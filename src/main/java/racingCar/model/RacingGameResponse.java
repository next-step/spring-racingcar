package racingCar.model;


import java.util.List;
import racingCar.domain.RacingCar;

public class RacingGameResponse {
    String winners;

    List<RacingCar> racingCars;

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public void setRacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public RacingGameResponse(String winners, List<RacingCar> racingCars ) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

}
