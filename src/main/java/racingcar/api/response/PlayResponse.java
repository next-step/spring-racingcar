package racingcar.api.response;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class PlayResponse {
    private String winners;
    private List<Car> racingCars;

    public PlayResponse(String winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }

    public static PlayResponse extract(List<Car> participationCars, List<Car> racingWinners) {
        String winnersForPrint = racingWinners.stream().map(Car::toString).collect(Collectors.joining(", "));
        return new PlayResponse(winnersForPrint, participationCars);
    }

}
