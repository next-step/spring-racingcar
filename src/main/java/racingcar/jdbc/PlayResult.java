package racingcar.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlayResult {
    private long play_id;
    private int trialCount;
    private String winners;
    private LocalDateTime createdAt;

    public PlayResult(long play_id, int trialCount, String winners) {
        this.play_id = play_id;
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public PlayResult(String winners) {
        this.winners = winners;
    }

    public PlayResult(int trialCount, String winners, LocalDateTime createdAt) {
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }
}
