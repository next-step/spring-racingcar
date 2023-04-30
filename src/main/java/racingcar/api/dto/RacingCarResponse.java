package racingcar.api.dto;

import racingcar.domain.entity.PlayResult;
import racingcar.domain.entity.RacingCar;

import java.util.List;

public class RacingCarResponse {

    private String winners;

    private List<RacingCar> racingCars;

    public String getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public RacingCarResponse(String winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingCarResponse of(PlayResult playResult) {
        return new RacingCarResponse(playResult.getWinners(), playResult.getRacingCars());
    }
}
