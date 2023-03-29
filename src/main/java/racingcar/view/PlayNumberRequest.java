package racingcar.view;

public class PlayNumberRequest {

    private final int number;

    public PlayNumberRequest(int number) {
        this.number = number;
    }

    public static PlayNumberRequest playNumberRequest(int countParam) {
        int number = countParam;
        return new PlayNumberRequest(number);
    }

    public int getNumber() {
        return number;
    }
}
