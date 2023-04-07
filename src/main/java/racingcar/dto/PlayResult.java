package racingcar.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import racingcar.domain.Car;

import java.io.Serializable;
import java.util.List;

public class PlayResult implements Serializable {

    private String winners;
    private List<Car> racingCars;

    public PlayResult(Integer count, String winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }

}
