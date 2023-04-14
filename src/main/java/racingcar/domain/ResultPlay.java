package racingcar.domain;

public class ResultPlay {
    private final int id;
    private final int trialCount;

    public ResultPlay(int id, int trialCount) {
        this.id = id;
        this.trialCount = trialCount;
    }

    public int getId() {
        return this.id;
    }

    public int getTrialCount() {
        return this.trialCount;
    }
}
