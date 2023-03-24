package racingcar.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RacingCarResult {

    private String winners;
    private List<Car> cars;

    public RacingCarResult() {
    }

    public RacingCarResult(String winners, List<Car> cars) {
        this.winners = winners;
        this.cars = cars;
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
