package racingcar.domain;

import java.util.Random;

public class RandomCarMoveEntropy implements CarMoveEntropy {
    private static final int CHECK_MAX_RANDOM_NUMBER = 10;
    private final Random random;

    public RandomCarMoveEntropy() {
        this.random = new Random();
    }

    @Override
    public int getInt() {
        return random.nextInt(CHECK_MAX_RANDOM_NUMBER);
    }
}
