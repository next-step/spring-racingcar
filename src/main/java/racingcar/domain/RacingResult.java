package racingcar.domain;

import java.util.List;

public class RacingResult {
    private String winners;
    private final List<RacingCar> racingCars;
    public int count;

    public RacingResult(String winners, int count, List<RacingCar> racingCars) {
        this.winners = winners;
        this.count = count;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
