package racingcar.reponse;

import lombok.Getter;
import racingcar.domain.RacingCar;

import java.util.List;

@Getter
public class RacingResultResponse {
    private final String winners;
    private final List<RacingCar> racingCars;

    public RacingResultResponse(String winners, List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }
 }
