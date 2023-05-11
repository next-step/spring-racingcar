package racingcar.strategy;

import org.junit.jupiter.api.Test;
import racingcar.domain.strategy.MovingStrategyType;
import racingcar.domain.strategy.RandomMovingStrategy;

import static org.assertj.core.api.Assertions.assertThat;

class MovingStrategyTypeTest {

    @Test
    void RandomMovingStrategy() {
        assertThat(MovingStrategyType.getStrategy(MovingStrategyType.RANDOM))
                .isInstanceOf(RandomMovingStrategy.class);
    }

}