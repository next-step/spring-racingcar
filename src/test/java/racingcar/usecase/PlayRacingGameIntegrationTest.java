package racingcar.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import racingcar.usecase.response.RacingPlayerResponse;
import racingcar.usecase.request.PlayRacingGameRequest;
import racingcar.usecase.response.PlayRacingGameResponse;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PlayRacingGameIntegrationTest extends PlayRacingGameUseCaseTest {

    @Autowired
    private PlayRacingGameUseCase playRacingGameUseCase;

    @DisplayName("trialCount가 음수일 때")
    @Nested
    class minus {

        List<String> names = List.of("드록바", "존테리", "램파드", "에슐리콜", "체흐");

        @DisplayName("에러를 발생시킨다.")
        @Test
        void createPlayer() {
            // given
            int trialCount = -5;

            PlayRacingGameRequest request = new PlayRacingGameRequest(names, trialCount);

            // when

            // then
            Assertions.assertThatThrownBy(() -> playRacingGameUseCase.playRacingGame(request)).isInstanceOf(ConstraintViolationException.class);
        }
    }

    @DisplayName("trialCount가 양수이며")
    @Nested
    class plus {
        int trialCount = 500;

        @DisplayName("참가자가 0명일 때")
        @Nested
        class zero {

            @DisplayName("에러를 발생시킨다.")
            @Test
            void createPlayer() {
                // given
                List<String> names = List.of();
                PlayRacingGameRequest request = new PlayRacingGameRequest(names, trialCount);
                // when
                Assertions.assertThatThrownBy(() -> playRacingGameUseCase.playRacingGame(request)).isInstanceOf(RuntimeException.class);

                // then
            }
        }

        @DisplayName("참가자가 다섯명일 때")
        @Nested
        class player5 {

            @DisplayName("참가자를 다섯 생성한다.")
            @Test
            void createPlayer() {
                // given
                List<String> names = List.of("드록바", "존테리", "램파드", "에슐리콜", "체흐");
                PlayRacingGameRequest request = new PlayRacingGameRequest(names, trialCount);


                // when
                PlayRacingGameResponse response = playRacingGameUseCase.playRacingGame(request);
                // then

                List<RacingPlayerResponse> racingPlayers = response.getPlayers();
                assertThat(racingPlayers).hasSize(5);
                assertThat(racingPlayers.stream().map(RacingPlayerResponse::getName)).containsOnly("드록바", "존테리", "램파드", "에슐리콜", "체흐");
                List<Integer> positions = racingPlayers.stream().map(RacingPlayerResponse::getPosition).collect(Collectors.toList());
                positions.forEach(position -> assertThat(position).isLessThanOrEqualTo(trialCount));
            }
        }
    }
}