package racingcar.repository;

public class PlayResult {
    private int trialCount;
    private String name;
    private int position;
    private String winners;

    public PlayResult(int trialCount, String name, int position, String winners) {
        this.trialCount = trialCount;
        this.name = name;
        this.position = position;
        this.winners = winners;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public String getWinners() {
        return winners;
    }
}
