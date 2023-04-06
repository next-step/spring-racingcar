package racingcar.dto;

import racingcar.domain.Car;

import java.io.Serializable;
import java.util.List;

public class PlayResult implements Serializable {

    private Integer count;
    private String winners;
    private List<Car> racingCars;

    public PlayResult(Integer count, String winners, List<Car> racingCars) {
        this.count = count;
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public Integer getCount() {
        return count;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}