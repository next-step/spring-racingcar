package racingcar.domain;

import java.util.Random;

public class RandomMoveStrategy implements MoveStrategy {

    private static final int MAX = 10;

    @Override
    public int move() {
        Random random = new Random();
        return random.nextInt(MAX);
    }

}
