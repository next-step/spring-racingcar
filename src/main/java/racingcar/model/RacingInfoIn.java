package racingcar.model;

public class RacingInfoIn {

    private int id;
    private int trialCount;
    private String winners;

    public int getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrialCount(int trialCount) {
        this.trialCount = trialCount;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }
}
