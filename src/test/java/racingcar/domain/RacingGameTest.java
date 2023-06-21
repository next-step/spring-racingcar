package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.entity.RacingGame;
import racingcar.ParentTest;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest extends ParentTest {

    @Test
    @DisplayName("게임을 시작한다.")
    void start() {
        //given
        int trialCount = 5;

        //when
        RacingGame racingGame = RacingGame.create(trialCount);

        //then
        assertThat(racingGame.getTrialCount()).isEqualTo(trialCount);
        assertThat(racingGame.isNew()).isTrue();
    }
}