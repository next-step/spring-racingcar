package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.fixture.TestMoveStrategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarsTest {

    private static final int MOVE_CAR = 10;
    private static final int NOT_MOVE_CAR = 1;

    @DisplayName("모든 자동차를 1칸 움직인다.")
    @Test
    void move() {
        // given
        int expect = 1;
        RacingCars racingCars = RacingCars.of(Arrays.asList("a", "b", "c"));

        // when
        racingCars.move(new TestMoveStrategy(MOVE_CAR));

        // then
        racingCars.getRacingCars()
                .forEach(it -> assertThat(it.getPosition()).isEqualTo(expect));
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
        a.move(NOT_MOVE_CAR);

        RacingCar b = new RacingCar("b");
        b.move(MOVE_CAR);

        RacingCar c = new RacingCar("c");
        c.move(MOVE_CAR);

        RacingCar d = new RacingCar("d");
        d.move(NOT_MOVE_CAR);

        return Arrays.asList(a, b, c, d);
    }

}
