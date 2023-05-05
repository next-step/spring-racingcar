package racingcar.game.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PlayerHistoryEntity {

    private final Long id;
    private final PlayerHistory playerHistory;

    public Long getPlayResultId() {
        return playerHistory.getPlayResultId();
    }

    public String getName() {
        return playerHistory.getName();
    }

    public Integer getPosition() {
        return playerHistory.getPosition();
    }

    public Boolean isWinner() {
        return playerHistory.getIsWinner();
    }

    public LocalDateTime getCreatedAt() {
        return playerHistory.getCreatedAt();
    }
}
