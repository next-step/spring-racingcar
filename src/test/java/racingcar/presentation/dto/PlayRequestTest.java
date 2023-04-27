package racingcar.presentation.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayRequestTest {

    @DisplayName("요청받은 이름을 , 로 구분하여 반환한다.")
    @Test
    void getPlayers() {
        // given
        String names = "이름1,이름2";
        PlayRequest playRequest = new PlayRequest(names, 1);

        // when
        List<String> players = playRequest.getPlayers();

        // then
        assertThat(players).containsExactly("이름1", "이름2");
    }

}
