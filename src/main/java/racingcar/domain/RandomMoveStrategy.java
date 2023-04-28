package racingcar.domain;

import java.util.Random;

public class RandomMoveStrategy implements MoveStrategy {

    private static final Random random = new Random();
    private static final int MAX = 10;

    @Override
    public int move() {
        return random.nextInt(MAX);
    }

}
