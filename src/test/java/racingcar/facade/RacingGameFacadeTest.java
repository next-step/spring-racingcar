package racingcar.facade;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.entity.RacingPlayerResponse;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.RacingPlayerRepository;
import racingcar.service.RandomNumberGenerator;
import racingcar.service.RacingGameService;
import racingcar.service.RacingPlayerService;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingGameFacadeTest {

    @Autowired
    private RacingPlayerRepository racingPlayerRepository;
    @Autowired
    private RacingGameRepository racingGameRepository;

    @Autowired
    private RacingGameFacade racingGameFacade;
    @Autowired
    private RacingPlayerService racingPlayerService;
    @Autowired
    private RandomNumberGenerator randomNumberGenerator;
    @Autowired
    private RacingGameService racingGameService;

    @DisplayName("trialCount가 음수일 때")
    @Nested
    class minus {

        List<String> names = List.of("드록바", "존테리", "램파드", "에슐리콜", "체흐");

        @DisplayName("에러를 발생시킨다.")
        @Test
        void createPlayer() {
            // given
            int trialCount = -5;

            // when

            // then
            Assertions.assertThatThrownBy(() -> racingGameFacade.createRacingGame(names, trialCount)).isInstanceOf(IllegalArgumentException.class);
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


                // when

                // then
                Assertions.assertThatThrownBy(() -> racingGameFacade.createRacingGame(names, trialCount)).isInstanceOf(RuntimeException.class);
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

                // when
                CreateRacingGameResponse response = racingGameFacade.createRacingGame(names, trialCount);
                // then

                List<RacingPlayerResponse> racingPlayers = response.getPlayers();
                assertThat(racingPlayers).hasSize(5);
                assertThat(racingPlayers.stream().map(RacingPlayerResponse::getName)).containsOnly("드록바", "존테리", "램파드", "에슐리콜", "체흐");
                List<Integer> positions = racingPlayers.stream().map(RacingPlayerResponse::getPosition).collect(Collectors.toList());
                assertThat(racingPlayers.stream().filter(r -> racingPlayerService.isWinner(r.getPosition(), positions)).allMatch(RacingPlayerResponse::getWinner)).isTrue();
                assertThat(racingPlayers.stream().filter(r -> !racingPlayerService.isWinner(r.getPosition(), positions)).noneMatch(RacingPlayerResponse::getWinner)).isTrue();
            }
        }
    }
}