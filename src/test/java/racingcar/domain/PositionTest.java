package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("score 동등 비교")
    @Test
    void equals() {
        Position position = new Position();
        Position samePosition = new Position(1);
        assertThat(position.getAddedPosition().equals(samePosition)).isTrue();
    }
}
