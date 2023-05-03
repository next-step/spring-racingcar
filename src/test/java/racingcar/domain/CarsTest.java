package racingcar.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CarsTest {

    @Test
    void create() {
        Cars cars = new Cars(new String[]{"carA", "carB", "carC"});

        assertThat(cars).isEqualTo(new Cars(List.of(
                new Car("carA"),
                new Car("carB"),
                new Car("carC")
        )));
    }

}
