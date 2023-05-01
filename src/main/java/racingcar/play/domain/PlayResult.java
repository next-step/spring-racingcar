package racingcar.play.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlayResult {

    private final Long id;
    private final String winners;
    private final Integer trialCount;
    private final LocalDateTime createdAt;

    public static PlayResult of(String winners, Integer trialCount) {
        return new PlayResult(null, winners, trialCount, null);
    }
}
