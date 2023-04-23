package racingcar.database;

import racingcar.racing.Driver;
import racingcar.racing.RacingCar;

import java.util.List;
import java.util.stream.Collectors;

public class RaceResult {

    private int round;

    private final int count;
    private final List<Driver> winners;
    private final List<RacingCar> racingCars;

    public RaceResult(int count, List<Driver> winners, List<RacingCar> racingCars) {
        this.count = count;
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public int getCount() {
        return this.count;
    }

    public String getWinners() {
        return this.winners.stream().map(Driver::getName)
                .collect(Collectors.joining(", "));
    }

    public List<RacingCar> getRacingCars() {
        return this.racingCars;
    }

    public int getRound() { return this.round; }
    public void setRound(int round) { this.round = round; }
}
