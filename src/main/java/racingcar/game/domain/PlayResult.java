package racingcar.game.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PlayResult {

    private final Integer trialCount;
    private final LocalDateTime createdAt;

    public static PlayResult of(Integer trialCount) {
        return new PlayResult(trialCount, LocalDateTime.now());
    }

    public PlayResultEntity toPlayResultEntity(Long id) {
        return new PlayResultEntity(id, this);
    }
}
