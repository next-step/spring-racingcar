package racingcar.dto;

public class RacingStartDto {
    private final String names;
    private final int trial;

    public RacingStartDto(String names, int trial) {
        this.names = names;
        this.trial = trial;
    }

    public String getNames() {
        return this.names;
    }

    public int getTrial() {
        return this.trial;
    }
}
