package racingcar.domain;

public class CarMoveUtil {

    private final static int RANDOM_PIVOT_NUMBER = 4;

    private CarMoveUtil() {
    }

    public static int getRandomNumber() {
        return (int) (Math.random() * 10);
    }

    public static boolean isMove() {
        return getRandomNumber() >= RANDOM_PIVOT_NUMBER;
    }

}
