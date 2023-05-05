package racingcar.dto;

import lombok.Getter;
import racingcar.domain.PlayResult;

import java.util.List;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@Getter
public class RacingPlaysResponse {
    private final String winners;
    private final List<RacingCarResponse> racingCars;

    public RacingPlaysResponse(String winners, List<RacingCarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingPlaysResponse of(PlayResult playResult){
        return new RacingPlaysResponse(playResult.getWinners(), playResult.getRacingCars());
    }
}
