package racingcar.domain;

public class RacingCarRequest {

    private String names;
    private int count = 0;

    public RacingCarRequest(String names, int count) {
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
