package racingcar.game.domain;

import java.util.Random;

public class RandomMoveStrategy implements MoveStrategy {

    private static final Random RANDOM;
    private static final int MAX_RANGE;

    static {
        RANDOM = new Random();
        MAX_RANGE = 10;
    }

    @Override
    public int move() {
        return RANDOM.nextInt(MAX_RANGE);
    }
}
