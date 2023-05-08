package racingcar.game.domain;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
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
