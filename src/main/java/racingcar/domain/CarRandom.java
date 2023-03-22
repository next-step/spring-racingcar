package racingcar.domain;

public class CarRandom {

    final static int RANDOM_PIVOT_NUMBER = 4;

    private CarRandom() {
    }

    public static int getRandomNumber() {
        return (int) (Math.random() * 10);
    }

    public static boolean isMove() {
        return getRandomNumber() >= RANDOM_PIVOT_NUMBER;
    }

}
