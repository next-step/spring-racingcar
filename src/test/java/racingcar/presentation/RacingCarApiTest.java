package racingcar.presentation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import racingcar.presentation.dto.GameResultDto;
import racingcar.presentation.dto.GameStartDto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RacingCarApiTest {

    @Autowired
    private RacingCarApi racingCarApi;

    @Test
    void racingCarApiTest() {
        // given
        GameStartDto gameStartDto = new GameStartDto("a,b,c", 10);

        // when
        GameResultDto gameResultDto = racingCarApi.playRacingCar(gameStartDto);

        // then
        assertThat(gameResultDto).isNotNull();
    }

    @Test
    void racingCarApiFailTest() {
        // given
        GameStartDto gameStartDto = new GameStartDto("", 0);

        // when
        // then
        assertThrowsExactly(IllegalArgumentException.class,
                () -> racingCarApi.playRacingCar(gameStartDto));
    }

}