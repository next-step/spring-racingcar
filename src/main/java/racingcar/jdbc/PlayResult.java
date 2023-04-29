package racingcar.jdbc;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlayResult {
    private long id;
    private int trialCount;
    private String winners;
    private LocalDateTime createdAt;

    public PlayResult(int trialCount, String winners, LocalDateTime createdAt) {
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public PlayResult(long id, int trialCount, String winners, LocalDateTime createdAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }
}
