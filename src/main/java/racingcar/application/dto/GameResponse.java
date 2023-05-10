package racingcar.application.dto;

import racingcar.domain.RacingCar;

import java.util.List;
import java.util.stream.Collectors;

public class GameResponse {

    private final String winners;
    private final List<RacingInfo> racingCars;

    public GameResponse(String winners, List<RacingInfo> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingInfo> getRacingCars() {
        return racingCars;
    }

    public static GameResponse of(String winners, List<RacingCar> racingCars) {
        return new GameResponse(winners, racingCars.stream()
                .map(RacingInfo::from)
                .collect(Collectors.toList()));
    }
}
