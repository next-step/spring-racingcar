package racingcar.domain;

import org.junit.jupiter.api.Test;
import racingcar.domain.dto.PlayResultDto;

import static org.assertj.core.api.Assertions.assertThat;

class PlayResultDtoTest {

    @Test
    void getValue() {
        PlayResultDto playResultDto = new PlayResultDto(3, "carA");

        assertThat(playResultDto.getPositionValue()).isEqualTo(3);
        assertThat(playResultDto.getNameValue()).isEqualTo("carA");
    }

    @Test
    void isPositionEquals() {
        assertThat(new PlayResultDto(5, "carA").isPositionEquals(new Position(5)))
                .isTrue();
    }

}