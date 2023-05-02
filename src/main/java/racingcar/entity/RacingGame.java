package racingcar.entity;

import javax.validation.constraints.NotNull;

public class RacingGame extends BaseEntity {
    @NotNull
    private final Integer trialCount;

    public RacingGame(Integer trialCount) {
        this.trialCount = trialCount;
    }

    public Integer getTrialCount() {
        return trialCount;
    }
}
