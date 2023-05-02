package racingcar.application.dto;

import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.Winners;

import java.util.List;

public class GameResponse {

    private final String winners;
    private final List<RacingCar> racingCars;

    public GameResponse(String winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public static GameResponse of(Winners winners, RacingCars racingCars) {
        return new GameResponse(winners.getNames(), racingCars.getRacingCars());
    }
}
