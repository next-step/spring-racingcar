package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.controller.dto.request.PlayRacingCarRequest;
import racingcar.controller.dto.response.PlayRacingCarResponse;

import java.util.List;

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

    @Test
    @DisplayName("자동차 경주 게임의 결과가 조회된다.")
    void playResult() {
        // given
        racingCarService.play(new PlayRacingCarRequest("test1,test2,test3", 5));
        racingCarService.play(new PlayRacingCarRequest("test1,test2,test3", 5));

        // when
        List<PlayRacingCarResponse> response = racingCarService.playResult();

        // then
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0).getWinners()).isNotEmpty();
        assertThat(response.get(0).getRacingCars().size()).isEqualTo(3);
    }
}
