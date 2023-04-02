package racingcar.repository;

public class PlayResult {
    private int round;
    private int trialCount;
    private String name;
    private int position;
    private String winners;

    public PlayResult(int round, int trialCount, String name, int position, String winners) {
        this.round = round;
        this.trialCount = trialCount;
        this.name = name;
        this.position = position;
        this.winners = winners;
    }

    public int getRound() {
        return round;
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
