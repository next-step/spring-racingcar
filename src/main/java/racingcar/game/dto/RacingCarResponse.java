package racingcar.game.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import racingcar.game.domain.PlayerHistoryEntity;
import racingcar.game.domain.RacingCar;

@RequiredArgsConstructor
@Getter
public class RacingCarResponse {

    private final String name;
    private final int position;

    public static RacingCarResponse from(RacingCar racingCar) {
        return new RacingCarResponse(racingCar.getName(), racingCar.getPosition());
    }

    public static RacingCarResponse from(PlayerHistoryEntity playerHistoryEntity) {
        return new RacingCarResponse(playerHistoryEntity.getName(), playerHistoryEntity.getPosition());
    }
}
