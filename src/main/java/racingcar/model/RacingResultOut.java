package racingcar.model;

import racingcar.RacingCar;

import java.util.List;

public class RacingResultOut {

    private String winners;
    private List<RacingCar> racingCars;

    public void RacingResultOut(String winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

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

}
