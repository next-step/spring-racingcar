package racingcar.game.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PlayerHistory {

    private final Long playResultId;
    private final String name;
    private final Integer position;
    private final Boolean isWinner;
    private final LocalDateTime createdAt;

    public static PlayerHistory of(Long playResultId, RacingCar racingCar, Boolean isWinner) {
        return new PlayerHistory(playResultId, racingCar.getName(), racingCar.getPosition(),
            isWinner, LocalDateTime.now());
    }

    public PlayerHistoryEntity toPlayerHistoryEntity(Long id) {
        return new PlayerHistoryEntity(id, this);
    }
}
