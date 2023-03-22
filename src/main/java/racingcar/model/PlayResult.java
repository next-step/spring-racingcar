package racingcar.model;

public class PlayResult {

    private int id;
    private int groupId;
    private int trialCount;
    private String winners;
    private String createdAt;

    public PlayResult(int id, int groupId, int trialCount, String winners, String createdAt) {
        this.id = id;
        this.groupId = groupId;
        this.trialCount = trialCount;
        this.winners = winners;
        this.createdAt = createdAt;
    }

    public PlayResult(int id, int groupId, int trialCount, String winners) {
        this.id = id;
        this.groupId = groupId;
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public PlayResult(int groupId, int trialCount, String winners) {
        this.groupId = groupId;
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public void setTrialCount(int trialCount) {
        this.trialCount = trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
