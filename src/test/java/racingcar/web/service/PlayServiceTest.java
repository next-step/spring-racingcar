package racingcar.web.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.domain.PlayResult;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayServiceTest {

    private PlayService playService;

    @BeforeEach
    void setUp() {
        playService = new PlayService();
    }

    @Test
    void play() {
        List<PlayResult> playResults = playService.play("carA, carB, carC", 3);

        assertThat(playResults).isNotNull();
        assertThat(playResults).hasSize(3);
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("playResultsProvider")
    void findWinners(List<PlayResult> playResults, String expected, String displayMessage) {
        assertThat(playService.findWinners(playResults)).isEqualTo(expected);
    }

    private static Stream<Arguments> playResultsProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        new PlayResult(3, "carA"),
                        new PlayResult(2, "carB")), "carA", "findWinners - 우승자가 한명인 경우"),
                Arguments.of(List.of(
                        new PlayResult(3, "carA"),
                        new PlayResult(3, "carB")), "carA,carB", "findWinners - 우승자가 여러명인 경우")
        );
    }
}