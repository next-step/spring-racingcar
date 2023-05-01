package racingcar.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.presentation.dto.GameResultDto;
import racingcar.presentation.dto.GameStartDto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest
class RacingCarApiTest {

    @Autowired
    private RacingCarApi racingCarApi;

    @DisplayName("프레젠테이션 레이어 성공 케이스")
    @Test
    void racingCarApiTest() {
        // given
        GameStartDto gameStartDto = new GameStartDto("a,b,c", 10);

        // when
        GameResultDto gameResultDto = racingCarApi.playRacingCar(gameStartDto);

        // then
        assertThat(gameResultDto).isNotNull();
    }

    @DisplayName("프레젠테이션 레이어 실패 케이스")
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