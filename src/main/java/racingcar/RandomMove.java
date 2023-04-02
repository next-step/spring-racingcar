package racingcar;

import java.util.Random;

public class RandomMove {
    private static final int MIN_MOVE_NUMBER = 4;       // 전진하기 위한 최소 허들 숫자

    public boolean movable() {
        if (getRandomNumber() >= MIN_MOVE_NUMBER) {
            return true;
        }

        return false;
    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(9);
    }
}
