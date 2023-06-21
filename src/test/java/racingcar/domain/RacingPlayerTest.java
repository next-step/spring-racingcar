package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.entity.RacingGame;
import racingcar.ParentTest;
import racingcar.entity.RacingPlayer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingPlayerTest extends ParentTest {

    @Nested
    @DisplayName("참가자를 참가시킬 때")
    class create {

        @Test
        @DisplayName("게임이 사전에 저장되어 있지 않으면 에러가 발생한다.")
        void joinBeforeSave() {
            //given
            RacingGame racingGame = createNewRacingGame();
            String name = "드록바";
            int position = 0;

            //when
            Assertions.assertThatThrownBy(() -> racingGame.createRacingPlayer(name, position)).isInstanceOf(NullPointerException.class);

            //then
        }

        @Test
        @DisplayName("게임에 승리 조건이 결정되어 있지 않으면 에러가 발생한다.")
        void joinBeforeDecideCondition() {
            //given
            RacingGame racingGame = getExistRacingGame();
            String name = "드록바";
            int position = 0;

            //when
            Assertions.assertThatThrownBy(() -> racingGame.createRacingPlayer(name, position)).isInstanceOf(NullPointerException.class);

            //then
        }

        @Test
        @DisplayName("승리 조건을 숫자로 결정할 수 있다.")
        void join() {
            //given
            int condition = 5;
            RacingGame racingGame = getExistRacingGame();
            racingGame.updateWinnerCondition(condition);
            String name = "드록바";
            int position = 0;

            //when
            RacingPlayer racingPlayer = racingGame.createRacingPlayer(name, position);

            //then
            assertThat(racingPlayer.getName()).isEqualTo(name);
            assertThat(racingPlayer.getPosition()).isEqualTo(position);
            assertThat(racingPlayer.isWinner()).isFalse();
            assertThat(racingPlayer.getRacingGameId()).isEqualTo(racingGame.getId());
        }


        @Test
        @DisplayName("승리 조건을 직접 결정할 수 있다.")
        void setCondition() {
            //given
            int condition = 5;
            RacingGame racingGame = getExistRacingGame();
            racingGame.updateWinnerCondition(position -> position <= condition);
            String name = "드록바";
            int position = -5;

            //when
            RacingPlayer racingPlayer = racingGame.createRacingPlayer(name, position);

            //then
            assertThat(racingPlayer.getName()).isEqualTo(name);
            assertThat(racingPlayer.getPosition()).isEqualTo(position);
            assertThat(racingPlayer.isWinner()).isTrue();
            assertThat(racingPlayer.getRacingGameId()).isEqualTo(racingGame.getId());
        }
    }

    private RacingGame createNewRacingGame() {
        RacingGame racingGame = RacingGame.create(5);
        return RacingGame.MockFactory.generate(1L, racingGame);
    }

    private RacingGame getExistRacingGame() {
        RacingGame racingGame = RacingGame.create(5);
        return RacingGame.MockFactory.generate(1L, racingGame);
    }
}