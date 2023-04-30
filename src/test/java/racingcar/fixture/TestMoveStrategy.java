package racingcar.fixture;

import racingcar.domain.MoveStrategy;

public class TestMoveStrategy implements MoveStrategy {

    private final int distance;

    public TestMoveStrategy(int distance) {
        this.distance = distance;
    }

    @Override
    public int move() {
        return distance;
    }

}
