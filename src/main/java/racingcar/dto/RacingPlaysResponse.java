package racingcar.dto;

import racingcar.domain.PlayResult;
import racingcar.domain.RacingCar;
import lombok.Getter;

import java.util.List;

/**
 * @author a1101466 on 2023/05/02
 * @project jwp-racingcar
 * @description
 */
@Getter
public class RacingPlaysResponse {
    private String winners;
    private List<RacingCar> racingCars;

    public RacingPlaysResponse(String winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingPlaysResponse of(PlayResult playResult){
        return new RacingPlaysResponse(playResult.getWinners(), playResult.getRacingCars());
    }
}
