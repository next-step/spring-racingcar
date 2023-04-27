package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.fixture.TestMoveStrategy;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarsTest {

    private static final int MOVE_DISTANCE = 1;
    private RacingCars racingCars;
    private MoveStrategy moveStrategy;

    @BeforeEach
    void setUp() {
        racingCars = RacingCars.of(Arrays.asList("a", "b", "c", "d"));
        moveStrategy = new TestMoveStrategy(MOVE_DISTANCE);
    }

    @DisplayName("자동차들이 움직이는지 확인")
    @Test
    void move() {
        // when
        racingCars.move(moveStrategy);

        // then
        racingCars.getRacingCars()
                .forEach(it -> assertThat(it.getPosition()).isEqualTo(MOVE_DISTANCE));
    }

}
