package racingcar.game.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PlayerHistory {

    private final Long id;
    private final Long playResultId;
    private final String name;
    private final Integer position;
    private final Boolean is_winner;
    private final LocalDateTime createdAt;

    public static PlayerHistory of(Long playResultId, RacingCar racingCar, Boolean is_winner) {
        return new PlayerHistory(null, playResultId, racingCar.getName(), racingCar.getPosition(), is_winner, null);
    }
}
