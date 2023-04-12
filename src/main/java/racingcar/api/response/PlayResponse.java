package racingcar.api.response;

import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.List;

public class PlayResponse {
    private String winners;
    private List<Car> racingCars;

    public PlayResponse(String winners, Cars racingCars) {
        this.winners = winners;
        this.racingCars = racingCars.getCars();
    }

    public static PlayResponse extract(Cars participationCars, Cars racingWinners) {
        String winnersForPrint = String.join(", ", racingWinners.names());
        return new PlayResponse(winnersForPrint, participationCars);
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }

    public void setRacingCars(List<Car> racingCars) {
        this.racingCars = racingCars;
    }

    @Override
    public String toString() {
        return "winners='" + winners + '\'' +
                ", racingCars=" + racingCars;
    }
}
