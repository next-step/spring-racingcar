package racingcar.api.request;

import racingcar.domain.Cars;

public class PlayRequest {
    public PlayRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    private String names;
    private int count;

    public Cars makeCars() {
        return new Cars(this.names.split(","));
    }

    public int getCount() {
        return count;
    }
}
