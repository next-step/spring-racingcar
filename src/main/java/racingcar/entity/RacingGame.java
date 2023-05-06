package racingcar.entity;

import racingcar.support.GeneratedValue;
import racingcar.support.IdField;

import javax.validation.constraints.NotNull;

public class RacingGame extends BaseEntity {

    @IdField
    @GeneratedValue
    private Long id;
    @NotNull
    private final Integer trialCount;

    public RacingGame(Integer trialCount) {
        this.trialCount = trialCount;
    }

    public Integer getTrialCount() {
        return trialCount;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
