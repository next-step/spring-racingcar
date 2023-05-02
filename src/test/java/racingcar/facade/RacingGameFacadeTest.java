package racingcar.facade;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.entity.RacingGame;
import racingcar.entity.RacingPlayer;
import racingcar.entity.RacingPlayerResponse;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.RacingGameRepositoryJDBC;
import racingcar.repository.RacingPlayerRepository;
import racingcar.service.CalculateRaceService;
import racingcar.service.RacingGameService;
import racingcar.service.RacingPlayerService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingGameFacadeTest {

    RacingPlayerRepository racingPlayerRepository;
    RacingGameRepository racingGameRepository;

    private final RacingGameFacade racingGameFacade;
    private RacingPlayerService racingPlayerService;
    private CalculateRaceService calculateRaceService;
    private RacingGameService racingGameService;
    @Mock
    RacingGame racingGame;

    public RacingGameFacadeTest() {
        this.racingPlayerRepository = Mockito.mock(RacingPlayerRepository.class);
        this.racingGameRepository = Mockito.mock(RacingGameRepository.class);
        this.calculateRaceService = Mockito.spy(CalculateRaceService.class);
        this.racingGameService = new RacingGameService(Mockito.mock(RacingGameRepository.class));
        this.racingPlayerService = new RacingPlayerService(Mockito.mock(RacingPlayerRepository.class), calculateRaceService);
        this.racingGameFacade = new RacingGameFacadeImpl(racingGameService, racingPlayerService);
    }

    @BeforeEach
    void init() {
        BDDMockito.given(racingGameRepository.save(any())).willReturn(racingGame);
    }

    @DisplayName("trialCount가 음수라면")
    @Nested
    class minus {

        String names = "드록바, 존테리, 램파드, 에슐리콜, 체흐";

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

    @DisplayName("trialCount가 양수라면")
    @Nested
    class plus {
        int trialCount = 500;

        @DisplayName("참가자가 0명이라면")
        @Nested
        class zero {

                @DisplayName("에러를 발생시킨다.")
                @Test
                void createPlayer() {
                    // given
                    String names = "";


                    // when

                    // then
                    Assertions.assertThatThrownBy(() -> racingGameFacade.createRacingGame(names, trialCount)).isInstanceOf(RuntimeException.class);
                }
        }

        @DisplayName("참가자가 다섯명이라면")
        @Nested
        class player5 {

            @BeforeEach
            void init() {
                BDDMockito.given(racingGameRepository.save(racingGame)).willReturn(racingGame);
            }

            @DisplayName("참가자를 다섯 생성한다.")
            @Test
            void createPlayer() {
                // given
                String names = "드록바, 존테리, 램파드, 에슐리콜, 체흐";

                // when
                CreateRacingGameResponse response = racingGameFacade.createRacingGame(names, trialCount);
                // then

                List<RacingPlayerResponse> racingPlayers = response.getPlayers();
                assertThat(racingPlayers).hasSize(5);
                assertThat(racingPlayers.stream().map(RacingPlayerResponse::getName)).containsOnly("드록바", "존테리", "램파드", "에슐리콜", "체흐");
                Integer integer = racingPlayers.stream().map(RacingPlayerResponse::getPosition).max(Integer::compareTo).get();
                assertThat(racingPlayers.stream().filter(r -> racingPlayerService.isWinner(r.getPosition(), integer)).allMatch(RacingPlayerResponse::getWinner)).isTrue();
                assertThat(racingPlayers.stream().filter(r -> !racingPlayerService.isWinner(r.getPosition(), integer)).noneMatch(RacingPlayerResponse::getWinner)).isTrue();
            }
        }
    }


}