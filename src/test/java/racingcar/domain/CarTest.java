package racingcar.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    public void makeCarTest() {
        Car car = new Car("volvo");
        assertThat(car.getName(), is("volvo"));
    }

    @Test
    public void moveTest() {
        Car car = new Car("volvo");
        car.move(true);
        car.move(true);
        assertThat(car.getPosition(), is(2));
    }
}
