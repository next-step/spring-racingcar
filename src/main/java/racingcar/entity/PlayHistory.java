package racingcar.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class PlayHistory {

    private long id;
    private int trialCount;
    private String winners;
    private LocalDateTime playedAt;

    public PlayHistory(int trialCount, String winners) {
        this.trialCount = trialCount;
        this.winners = winners;
    }

}
