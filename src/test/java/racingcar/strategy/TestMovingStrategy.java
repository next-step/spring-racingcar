package racingcar.strategy;

import org.springframework.stereotype.Component;
import racingcar.domain.strategy.MovingStrategy;

@Component
public class TestMovingStrategy implements MovingStrategy {

    private boolean shouldMove = true;

    @Override
    public boolean shouldMove() {
        return shouldMove;
    }

    public void setShouldMove(boolean shouldMove) {
        this.shouldMove = shouldMove;
    }
}
