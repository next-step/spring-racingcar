package racingcar.web.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class PlayHistory {

    private long id;
    private int trialCount;
    private String winners;
    private LocalDateTime playedAt;

    public PlayHistory(long id, int trialCount, String winners, LocalDateTime playedAt) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
        this.playedAt = playedAt;
    }

    public PlayHistory(int trialCount, String winners) {
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayHistory that = (PlayHistory) o;
        return getId() == that.getId() && getTrialCount() == that.getTrialCount() && Objects.equals(getWinners(), that.getWinners()) && Objects.equals(getPlayedAt(), that.getPlayedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTrialCount(), getWinners(), getPlayedAt());
    }

}
