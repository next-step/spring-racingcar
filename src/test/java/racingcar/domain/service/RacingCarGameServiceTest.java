package racingcar.domain.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.domain.dto.PlayResultDto;
import racingcar.domain.strategy.MovingStrategy;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarGameServiceTest {

    @ParameterizedTest(name = "{2}")
    @MethodSource("movingStrategyProvider")
    void play(MovingStrategy movingStrategy, int expectedPosition, String displayMessage) {
        RacingCarGameService racingCarGameService = new RacingCarGameService(movingStrategy);
        List<PlayResultDto> playResultDtos = racingCarGameService.play(new String[]{"carA", "carB", "carC"}, 3);

        assertThat(playResultDtos).isNotNull();
        assertThat(playResultDtos).hasSize(3);
        assertThat(playResultDtos).flatExtracting(PlayResultDto::getNameValue).containsOnly("carA", "carB", "carC");
        assertThat(playResultDtos).flatExtracting(PlayResultDto::getPositionValue).containsOnly(expectedPosition);
    }

    private static Stream<Arguments> movingStrategyProvider() {
        return Stream.of(
                Arguments.of((MovingStrategy) () -> true, 3, "play - movingStrategy_true"),
                Arguments.of((MovingStrategy) () -> false, 0, "play - movingStrategy_false")
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("playResultsProvider")
    void findWinners(List<PlayResultDto> playResultDtos, String[] expected, String displayMessage) {
        assertThat(new RacingCarGameService(() -> true).findWinners(playResultDtos)).isEqualTo(expected);
    }

    private static Stream<Arguments> playResultsProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        new PlayResultDto(3, "carA"),
                        new PlayResultDto(2, "carB")), new String[]{"carA"}, "findWinners - 우승자가 한명인 경우"),
                Arguments.of(List.of(
                        new PlayResultDto(3, "carA"),
                        new PlayResultDto(3, "carB")), new String[]{"carA", "carB"}, "findWinners - 우승자가 여러명인 경우")
        );
    }
}