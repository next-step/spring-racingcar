package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RacingCarsTest {

    @Test
    @DisplayName("가장 멀리간 포지션를 반환한다.")
    void getMaxPosition() {
        // given
        RacingCars racingCars = RacingCars.of(List.of("test1,test2,test3"));

        // when
        racingCars.getRacingCars().get(0).move(5);

        // then
        assertEquals(1, racingCars.getMaxPosition());
    }
}