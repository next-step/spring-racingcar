package racingcar.domain;

public class RacingInput {
    private String names;
    private int count;

    public RacingInput() {

    }

    public RacingInput(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
