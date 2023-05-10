package racingcar.global;

public class NameEmptyException extends RuntimeException {

    private static final String message = "이름을 입력해주세요.";

    public NameEmptyException() {
        super(message);
    }
}
