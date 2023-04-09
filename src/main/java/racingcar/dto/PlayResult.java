package racingcar.dto;
import racingcar.domain.Car;
import java.util.List;

public class PlayResult {

    private String winners;
    private List<Car> racingCars;

    public PlayResult(String winners, List<Car> racingCars) {
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
