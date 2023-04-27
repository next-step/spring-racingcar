package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarTest {

    @DisplayName("자동차를 입력한 거리 만큼 이동시킬 수 있다")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @ParameterizedTest(name = "자동차를 {0} 만큼 이동시킬 수 있다")
    void move(int distance) {
        // given
        RacingCar car = new RacingCar("A");

        // when
        car.move(distance);

        // then
        assertThat(car.getPosition()).isEqualTo(distance);
    }

    @DisplayName("자동차를 여러번 이동시키면 거리가 누적된다.")
    @Test
    void move_() {
        // given
        int first = 5;
        int second = 10;
        int expect = first + second;
        RacingCar car = new RacingCar("A");

        // when
        car.move(first);
        car.move(second);

        // then
        assertThat(car.getPosition()).isEqualTo(expect);
    }

}
