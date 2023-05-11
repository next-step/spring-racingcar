package racingcar.domain.strategy;

import java.util.Random;

public class RandomMovingStrategy implements MovingStrategy {

    private static final int RANDOM_NUMBER_LIMIT = 10;
    private static final int RANDOM_MOVE_THRESHOLD = 4;

    private static final Random random = new Random();

    @Override
    public boolean shouldMove() {
        return generateRandomNumber(RANDOM_NUMBER_LIMIT) >= RANDOM_MOVE_THRESHOLD;
    }

    public int generateRandomNumber(int limit) {
        return random.nextInt(limit);
    }
}
