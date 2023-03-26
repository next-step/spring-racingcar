package racingcar.domain;

import java.util.List;

public class PlayResult {

    private final List<String> winners;
    private final List<RacingCar> racingCars;

    public PlayResult(List<String> winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }
}
