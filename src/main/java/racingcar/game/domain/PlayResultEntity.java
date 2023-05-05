package racingcar.game.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PlayResultEntity {

    private final Long id;
    private final PlayResult playResult;


    public Integer getTrialCount() {
        return playResult.getTrialCount();
    }

    public LocalDateTime getCreatedAt() {
        return playResult.getCreatedAt();
    }
}
