package racingcar.domain;

import java.util.Random;

public class RandomMoveStrategy implements MoveStrategy {

    private static final int MAX_MOVE_DISTANCE = 10;

    @Override
    public int move() {
        Random random = new Random();
        return random.nextInt(MAX_MOVE_DISTANCE);
    }

}
