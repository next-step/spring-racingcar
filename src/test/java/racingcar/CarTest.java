package racingcar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.Car;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.TestRandomNumberGenerator;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CarTest {


    @ParameterizedTest
    @ValueSource(strings = {"abcdef"})
    void 자동차_이름은_5자리를_초과하지않음(String strings) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Car(strings));
    }

    @Test
    void 랜덤_수에_따라_이동이_나눠진다() {
        Car car = new Car("sean");
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        car.move(numberGenerator);
        assertThat(Arrays.asList(0, 1).contains(car.getPosition()));
    }

    @Test
    void 자동차는_한칸씩_이동을_한다() {
        Car car = new Car("sean");
        NumberGenerator numberGenerator = new TestRandomNumberGenerator();
        car.move(numberGenerator);
        assertThat(1).isEqualTo(car.getPosition());
    }
}
