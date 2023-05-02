package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.controller.dto.request.PlayRacingCarRequest;
import racingcar.controller.dto.response.PlayRacingCarResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class RacingCarServiceTest {

    @Autowired
    private RacingCarService racingCarService;

    @Test
    @DisplayName("자동차 경주 게임이 실행된다.")
    void play() {
        // given
        PlayRacingCarRequest request = new PlayRacingCarRequest("test1,test2,test3", 5);

        // when
        PlayRacingCarResponse response = racingCarService.play(request);

        // then
        assertThat(response.getWinners()).isNotEmpty();
        assertThat(response.getRacingCars().size()).isEqualTo(5);
    }
}