package racingcar.controller.dto.response;

import racingcar.domain.RacingCarResult;
import racingcar.domain.RacingCars;

import java.util.List;
import java.util.stream.Collectors;

public class PlayRacingCarResponse {

    private String winners;

    private List<RacingCarResult> racingCars;

    private PlayRacingCarResponse(String winner, List<RacingCarResult> racingCarResults) {
        this.winners = winner;
        this.racingCars = racingCarResults;
    }

    public static PlayRacingCarResponse of(String winner, RacingCars racingCars) {
        List<RacingCarResult> racingCarResult = racingCars.getRacingCars()
                .stream()
                .map(RacingCarResult::of)
                .collect(Collectors.toList());

        return new PlayRacingCarResponse(winner, racingCarResult);
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarResult> getRacingCars() {
        return racingCars;
    }
}
