package racingcar.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PlayResultDao {

    private int id;

    private String winners;

    private int trialCount;

    private Date createdAt;

    public PlayResultDao(int id, String winners, int trialCount, Date createdAt) {
        this.id = id;
        this.winners = winners;
        this.trialCount = trialCount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getWinners() {
        return winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}