package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.dto.PlayResultDto;

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
        PlayResultDto playResultDto1 = new PlayResultDto(2, "carA");
        PlayResultDto playResultDto2 = new PlayResultDto(3, "carB");
        PlayResultDto playResultDto3 = new PlayResultDto(3, "carC");
        List<PlayResultDto> playResultDtos = Arrays.asList(playResultDto1, playResultDto2, playResultDto3);

        assertThat(RacingCarGame.findWinners(playResultDtos)).containsExactly(playResultDto2, playResultDto3);
    }

    @Test
    void play() {
        RacingCarGame racingCarGame = new RacingCarGame(new String[]{"carA", "carB"}, 1);
        racingCarGame.play(() -> true);

        assertThat(racingCarGame.getPlayResults())
                .flatExtracting(PlayResultDto::getPosition)
                .containsOnly(new Position(1));
    }


}
