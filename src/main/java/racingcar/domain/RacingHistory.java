package racingcar.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class RacingHistory {
    private long id;
    private int trialCount, position, time;
    private String name, winners, date;
    public RacingHistory(long id, List<RacingCar> racingCars) {
        this.id = id;
    }
}
