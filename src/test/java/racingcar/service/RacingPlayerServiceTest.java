package racingcar.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;
import racingcar.repository.RacingPlayerRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingPlayerServiceTest {

    @Mock
    private RacingPlayerRepository racingPlayerRepository;
    @InjectMocks
    private RacingPlayerService racingPlayerService;
    @Spy
    private CalculateRaceService calculateRaceService;
    @Mock
    RacingGame racingGame;

    @DisplayName("trialCount가 음수라면")
    @Nested
    class minus {

        List<String> names = List.of("드록바", "존테리", "램파드", "에슐리콜", "체흐");

        @DisplayName("에러를 발생시킨다.")
        @Test
        void createPlayer() {
            // given
            int trialCount = -5;
            BDDMockito.given(racingGame.getTrialCount()).willReturn(trialCount);

            // when

            // then
            Assertions.assertThatThrownBy(() -> racingPlayerService.createRacingPlayers(names, racingGame)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("trialCount가 양수라면")
    @Nested
    class plus {

        @BeforeEach
        void init() {
            int trialCount = 500;
            BDDMockito.given(racingGame.getTrialCount()).willReturn(trialCount);
        }

        @DisplayName("참가자가 0명이라면")
        @Nested
        class zero {

                @DisplayName("에러를 발생시킨다.")
                @Test
                void createPlayer() {
                    // given
                    List<String> names = List.of();

                    // when

                    // then
                    Assertions.assertThatThrownBy(() -> racingPlayerService.createRacingPlayers(names, racingGame)).isInstanceOf(RuntimeException.class);
                }
        }

        @DisplayName("참가자가 다섯명이라면")
        @Nested
        class player5 {


            @BeforeEach
            void init() {
                BDDMockito.given(racingGame.getId()).willReturn(1L);
            }
            @DisplayName("참가자를 다섯 생성한다.")
            @Test
            void createPlayer() {
                // given
                List<String> names = List.of("드록바", "존테리", "램파드", "에슐리콜", "체흐");

                // when
                List<RacingPlayer> racingPlayers = racingPlayerService.createRacingPlayers(names, racingGame);
                // then

                assertThat(racingPlayers.size()).isEqualTo(names.size());
                assertThat(racingPlayers.stream().map(RacingPlayer::getName)).containsOnly("드록바", "존테리", "램파드", "에슐리콜", "체흐");
                assertThat(racingPlayers.stream().allMatch(r -> r.getRacingGameId().equals(racingGame.getId()))).isTrue();
                Integer integer = racingPlayers.stream().map(RacingPlayer::getPosition).max(Integer::compareTo).get();
                assertThat(racingPlayers.stream().filter(r -> racingPlayerService.isWinner(r.getPosition(), integer)).allMatch(RacingPlayer::isWinner)).isTrue();
                assertThat(racingPlayers.stream().filter(r -> !racingPlayerService.isWinner(r.getPosition(), integer)).noneMatch(RacingPlayer::isWinner)).isTrue();
            }
        }
    }


}