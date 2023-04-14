package racingcar.domain;

import java.util.Random;

public class RandomCarMoveDeterminer implements CarMoveDeterminer {
    private static final int CHECK_MAX_RANDOM_NUMBER = 10;
    private static final int MOVABLE_MIN_NUMBER = 4;
    private final Random random;

    public RandomCarMoveDeterminer() {
        this.random = new Random();
    }

    @Override
    public boolean isMove() {
        return random.nextInt(CHECK_MAX_RANDOM_NUMBER) >= MOVABLE_MIN_NUMBER;
    }
}
