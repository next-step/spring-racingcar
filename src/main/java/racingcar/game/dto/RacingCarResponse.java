package racingcar.game.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.game.domain.PlayerHistory;
import racingcar.game.domain.RacingCar;

@RequiredArgsConstructor
@Getter
public class RacingCarResponse {

    private final String name;
    private final int position;

    public static RacingCarResponse from(RacingCar racingCar) {
        return new RacingCarResponse(racingCar.getName(), racingCar.getPosition());
    }

    public static RacingCarResponse from(PlayerHistory playerHistory) {
        return new RacingCarResponse(playerHistory.getName(), playerHistory.getPosition());
    }
}
