package racingcar.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import racingcar.presentation.dto.GameStartDto;

@WebMvcTest(RacingService.class)
class RacingServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RacingService racingService;

    @DisplayName("비즈니스 레이어 테스트")
    @Test
    void gameTest() {
        // given
        GameStartDto gameStartDto = new GameStartDto("A,B,C", 100);
        // when
//        given(racingService.game(gameStartDto))
//                .willReturn(new GameResultDto())
        // then
    }
}