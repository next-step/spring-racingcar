package racingcar.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarsTest {

    @Test
    void create() {
        String[] carNames = {"carA", "carB", "carC"};
        assertThat(Cars.carList(carNames))
                .hasSize(3)
                .containsExactly(new Car("carA"), new Car("carB"), new Car("carC"));
    }

}
