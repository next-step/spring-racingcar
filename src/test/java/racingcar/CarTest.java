package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {
    @ParameterizedTest
    @ValueSource(strings = {"Tommy", "tommy", "bear"})
    @DisplayName("자동차 이름을 가진다")
    void setCarName(String name) {
        final Car car = new Car(name);
        Assertions.assertThat(car.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"TommyBear", "tommybear", "bear.tommy"})
    @DisplayName(("자동차 이름은 5글자를 초과할 수 없다"))
    void impossibleCarName(String name) {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Car car = new Car(name);
        });
    }

    @Test
    @DisplayName("자동차가 전진하는지 확인")
    void moveCar() {
        final Car car = new Car("tommy");
        Assertions.assertThat(car.getPosition()).isZero();

        car.move(true);
        Assertions.assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("자동차가 전진하지 않는지 확인")
    void notMoveCar() {
        final Car car = new Car("tommy");
        Assertions.assertThat(car.getPosition()).isZero();

        car.move(false);
        Assertions.assertThat(car.getPosition()).isEqualTo(0);
    }
}
