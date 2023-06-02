package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import racingcar.ServiceTest;
import racingcar.entity.RacingGame;
import racingcar.usecase.request.PlayRacingGameRequest;
import racingcar.usecase.response.PlayRacingGameResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
public class PlayRacingGameServiceTest extends PlayRacingGameMockTest {

    @InjectMocks private PlayRacingGameService playRacingGameService;

    @Nested
    @DisplayName("게임을 시작할 때 요청 값이")
    class request {

        @DisplayName("이름 리스트가 null이면 에러가 난다.")
        @Test
        void nameListNull() {
            //given
            List<String> nameList = null;
            int totalCount = 1;
            PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, totalCount);
            //when
            List<ServiceTest.ParameterValid> validate = validate(request);

            //then
            assertThat(validate.size()).isEqualTo(1);
            assertThat(validate).extracting("path").containsExactly("nameList");
        }

        @DisplayName("이름 리스트가 비어 있으면 에러가 난다.")
        @Test
        void nameListEmpty() {
            //given
            List<String> nameList = List.of();
            int totalCount = 1;
            PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, totalCount);
            //when
            List<ServiceTest.ParameterValid> validate = validate(request);

            //then
            assertThat(validate.size()).isEqualTo(1);
            assertThat(validate).extracting("path").containsExactly("nameList");
        }

        @DisplayName("이름이 blank면 에러가 난다.")
        @Test
        void nameBlack() {
            //given
            List<String> nameList = List.of(" ");
            int totalCount = 1;
            PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, totalCount);
            //when
            List<ServiceTest.ParameterValid> validate = validate(request);

            //then
            assertThat(validate.size()).isEqualTo(1);
            assertThat(validate).extracting("path").containsExactly("nameList[0].<list element>");
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        @DisplayName("total count가 0이하면 에러가 난다.")
        void totalCount(int trialCount) {
            //given
            List<String> nameList = List.of("드록바");
            PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, trialCount);
            //when
            List<ServiceTest.ParameterValid> validate = validate(request);

            //then
            assertThat(validate.size()).isEqualTo(1);
            assertThat(validate).extracting("path").containsExactly("trialCount");
        }
    }
    @Nested
    @DisplayName("게임을 시작할 때")
    class start {

        @DisplayName("게임을 시작한다.")
        @ValueSource(ints = {1, 3, 10, 150, 540})
        @ParameterizedTest
        void playRacingGame(int trialCount) {
            //given
            List<String> nameList = List.of("드록바");
            PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, trialCount);

            RacingGame racingGame = this.getRacingGame(trialCount);
            given(racingGameRepository.save(any(RacingGame.class))).willReturn(racingGame);


            //when
            PlayRacingGameResponse response = playRacingGameService.playRacingGame(request);

            //then
            assertThat(response.getPlayers().size()).isEqualTo(1);
            assertThat(response.getPlayers()).extracting("name").containsExactly("드록바");
            assertThat(response.getPlayers()).extracting("isWinner").contains(true);
        }


        @DisplayName("게임을 시작한다.")
        @ValueSource(ints = {1, 3, 10, 150, 540})
        @ParameterizedTest
        void playRacingGames(int trialCount) {
            //given
            List<String> nameList = List.of("드록바", "존테리", "램파드");
            PlayRacingGameRequest request = new PlayRacingGameRequest(nameList, trialCount);

            RacingGame racingGame = this.getRacingGame(trialCount);
            given(racingGameRepository.save(any(RacingGame.class))).willReturn(racingGame);

            //when
            PlayRacingGameResponse response = playRacingGameService.playRacingGame(request);

            //then
            assertThat(response.getPlayers().size()).isEqualTo(3);
            assertThat(response.getPlayers()).extracting("name").containsExactly("드록바", "존테리", "램파드");
            assertThat(response.getPlayers()).extracting("isWinner").contains(true);
        }

        private RacingGame getRacingGame(int trialCount) {
            RacingGame racingGame = RacingGame.create(trialCount);
            return RacingGame.MockFactory.generate(1L, racingGame);
        }
    }
}
