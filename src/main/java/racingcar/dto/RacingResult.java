package racingcar.dto;

import racingcar.domain.Car;

import java.io.Serializable;
import java.util.List;

public class RacingResult implements Serializable {

    private final Integer count;
    private final List<String> winners;
    private final List<Car> racingCars;

    public RacingResult(Integer count, List<String> winners, List<Car> racingCars) {
        this.count = count;
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public Integer getCount() {
        return count;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}