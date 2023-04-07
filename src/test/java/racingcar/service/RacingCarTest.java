package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingCarTest {
    RacingCarService racing;

    @BeforeEach
    void setUp() {
        racing = new RacingCarService();
    }

    @Test
    @DisplayName("우승자 구하기")
    void getWinner(){

        List<Car> cars = new ArrayList<>();
        racing.racingCars.add(new Car("cyan", 150));
        racing.racingCars.add(new Car("jade", 100));
        racing.racingCars.add(new Car("bomi", 20));

        assertThat("cyan").isEqualTo(racing.getWinner());
    }

}
