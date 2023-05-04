package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RacingCarGameTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void create_invalid(int playCount) {
        assertThatThrownBy(() -> new RacingCarGame(new String[]{"carA"}, playCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isEnd() {
        RacingCarGame racingCarGame = new RacingCarGame(new String[]{"carA", "carB"}, 1);
        assertThat(racingCarGame.isEnd()).isFalse();

        racingCarGame.play(() -> true);
        assertThat(racingCarGame.isEnd()).isTrue();
    }

    @Test
    void findWinners() {
        PlayResult playResult1 = new PlayResult(2, "carA");
        PlayResult playResult2 = new PlayResult(3, "carB");
        PlayResult playResult3 = new PlayResult(3, "carC");
        List<PlayResult> playResults = Arrays.asList(playResult1, playResult2, playResult3);

        assertThat(RacingCarGame.findWinners(playResults)).containsExactly(playResult2, playResult3);
    }

    @Test
    void play() {
        RacingCarGame racingCarGame = new RacingCarGame(new String[]{"carA", "carB"}, 1);
        racingCarGame.play(() -> true);

        assertThat(racingCarGame.getPlayResults())
                .flatExtracting(PlayResult::getPosition)
                .containsOnly(new Position(1));
    }


}
