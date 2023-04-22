package racingcar.database;

import racingcar.racing.Driver;
import racingcar.racing.RacingCar;

import java.util.ArrayList;
import java.util.List;

public class RaceHistory {
    private int trialCount;
    private List<Driver> winners;

    public RaceHistory(int trialCount, List<String> winners) {
        this.trialCount = trialCount;
        this.winners = new ArrayList<>();
        winners.stream().forEach(winner -> this.winners.add(new Driver(winner)));
    }
}
