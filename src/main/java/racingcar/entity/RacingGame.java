package racingcar.entity;

public class RacingGame extends BaseEntity {
    private final Integer trialCount;

    public RacingGame(Integer trialCount) {
        this.trialCount = trialCount;
    }

    public Integer getTrialCount() {
        return trialCount;
    }
}
