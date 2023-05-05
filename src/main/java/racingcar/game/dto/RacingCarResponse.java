package racingcar.game.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.game.domain.RacingCar;

@RequiredArgsConstructor
@Getter
public class RacingCarResponse {

    private final String name;
    private final int position;

    public static RacingCarResponse from(RacingCar racingCar) {
        return new RacingCarResponse(racingCar.getName(), racingCar.getPosition());
    }
}
