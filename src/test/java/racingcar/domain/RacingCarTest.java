package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarTest {

    private static final int MOVE_CAR = 10;

    @DisplayName("4 이상의 숫자를 전달할 경우 자동차가 1칸 이동한다.")
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    @ParameterizedTest(name = "{0}을 입력하면 자동차가 1칸 이동한다.")
    void move(int number) {
        // given
        RacingCar car = new RacingCar("A");

        // when
        car.move(number);

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @DisplayName("4 미만의 숫자를 전달할 경우 자동차가 이동하지 않는다.")
    @ValueSource(ints = {-10, -5, -1, 0, 1, 2, 3})
    @ParameterizedTest(name = "{0}을 입력하면 자동차가 이동하지 않는다..")
    void move_not(int number) {
        // given
        int expect = 0;
        RacingCar car = new RacingCar("A");

        // when
        car.move(number);

        // then
        assertThat(car.getPosition()).isEqualTo(expect);
    }

    @DisplayName("자동차를 여러번 이동시키면 거리가 누적된다.")
    @Test
    void move_() {
        // given
        int expect = 2;
        RacingCar car = new RacingCar("A");

        // when
        car.move(MOVE_CAR);
        car.move(MOVE_CAR);

        // then
        assertThat(car.getPosition()).isEqualTo(expect);
    }

}
