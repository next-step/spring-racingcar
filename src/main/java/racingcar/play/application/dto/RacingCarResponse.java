package racingcar.play.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.play.domain.RacingCar;

@RequiredArgsConstructor
@Getter
public class RacingCarResponse {

    private final String name;
    private final int position;

    public static RacingCarResponse from(RacingCar racingCar) {
        return new RacingCarResponse(racingCar.getName(), racingCar.getPosition());
    }
}
