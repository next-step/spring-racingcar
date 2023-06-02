package racingcar.usecase.response;

public class RacingGameResponse {
    private final Long id;
    private final Integer trialCount;

    public RacingGameResponse(Long id, Integer trialCount) {
        this.id = id;
        this.trialCount = trialCount;
    }

    public Long getId() {
        return id;
    }

    public Integer getTrialCount() {
        return trialCount;
    }
}
