package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.fixture.TestMoveStrategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @DisplayName("우승자를 확인")
    @Test
    void getMaxDistanceCar() {
        // given
        RacingCars racingCars = new RacingCars(MaxDistanceCarFixture());

        // when
        List<String> winnerName = racingCars.getMaxDistanceCarName();

        // then
        assertThat(winnerName).hasSize(2);
        assertThat(winnerName).containsExactly("b", "c");
    }

    @DisplayName("우승자가 없는 경우 예외를 반환한다.")
    @Test
    void getMaxDistanceCar_fail() {
        // given
        RacingCars racingCars = new RacingCars(Collections.emptyList());

        // then
        Assertions.assertThatThrownBy(() -> racingCars.getMaxDistanceCarName())
                .isInstanceOf(RuntimeException.class);
    }

    private List<RacingCar> MaxDistanceCarFixture() {
        RacingCar a = new RacingCar("a");
        a.move(1);

        RacingCar b = new RacingCar("b");
        b.move(10);

        RacingCar c = new RacingCar("c");
        c.move(10);

        RacingCar d = new RacingCar("d");
        d.move(5);

        return Arrays.asList(a, b, c, d);
    }

}
