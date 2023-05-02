package racingcar.entity;

public class RacingGameResponse {
    private final Long id;

    public Long getId() {
        return id;
    }

    public Integer getTrialCount() {
        return trialCount;
    }

    public RacingGameResponse(Long id, Integer trialCount) {
        this.id = id;
        this.trialCount = trialCount;
    }

    private final Integer trialCount;

}
