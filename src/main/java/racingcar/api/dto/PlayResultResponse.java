package racingcar.api.dto;

import racingcar.domain.entity.PlayResult;
import racingcar.domain.entity.RacingCar;

import java.util.List;

public class PlayResultResponse {

    private String winners;

    private List<RacingCar> racingCars;

    public String getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }

    public PlayResultResponse(String winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlayResultResponse of(PlayResult playResult) {
        return new PlayResultResponse(playResult.getWinners(), playResult.getRacingCars());
    }
}
