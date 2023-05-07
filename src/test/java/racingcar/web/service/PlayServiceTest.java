package racingcar.web.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.dto.PlayResultDto;
import racingcar.strategy.TestMovingStrategy;
import racingcar.web.dao.PlayHistoryDao;
import racingcar.web.dao.PlayHistoryDetailDao;
import racingcar.web.dto.PlayHistoryDto;
import racingcar.web.entity.PlayHistory;
import racingcar.web.entity.PlayHistoryDetail;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class PlayServiceTest {

    @Autowired
    private PlayService playService;
    @Autowired
    private TestMovingStrategy movingStrategy;
    @Autowired
    private PlayHistoryDao playHistoryDao;
    @Autowired
    private PlayHistoryDetailDao playHistoryDetailDao;

    @ParameterizedTest(name = "{2}")
    @MethodSource("movingStrategyProvider")
    void play(boolean shouldMove, int expectedPosition, String displayMessage) {
        movingStrategy.setShouldMove(shouldMove);
        List<PlayResultDto> playResultDtos = playService.play(new String[]{"carA", "carB", "carC"}, 3);

        assertThat(playResultDtos).isNotNull();
        assertThat(playResultDtos).hasSize(3);
        assertThat(playResultDtos).flatExtracting(PlayResultDto::getNameValue).containsOnly("carA", "carB", "carC");
        assertThat(playResultDtos).flatExtracting(PlayResultDto::getPositionValue).containsOnly(expectedPosition);
    }

    private static Stream<Arguments> movingStrategyProvider() {
        return Stream.of(
                Arguments.of(true, 3, "play - movingStrategy_true"),
                Arguments.of(false, 0, "play - movingStrategy_false")
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("playResultsProvider")
    void findWinners(List<PlayResultDto> playResultDtos, String[] expected, String displayMessage) {
        assertThat(playService.findWinners(playResultDtos)).isEqualTo(expected);
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

    @Test
    @Transactional
    void savePlayResults() {
        List<PlayResultDto> playResultDtos = List.of(
                new PlayResultDto(3, "carA"),
                new PlayResultDto(2, "carB")
        );
        int playCount = 10;
        Long playHistoryId = playService.savePlayResults(playCount, "carA", playResultDtos);

        PlayHistory playHistory = assertDoesNotThrow(playHistoryDao.findById(playHistoryId)::get);
        List<PlayHistoryDetail> playHistoryDetails = playHistoryDetailDao.findByPlayHistoryId(playHistoryId);

        assertThat(playHistory.getWinners()).isEqualTo("carA");
        assertThat(playHistory.getTrialCount()).isEqualTo(playCount);
        assertThat(playHistoryDetails.stream().map(PlayHistoryDetail::getName)).containsOnly("carA", "carB");
        assertThat(playHistoryDetails.stream().map(PlayHistoryDetail::getPosition)).containsOnly(3, 2);
    }

    @Test
    @Transactional
    void history() {
        List<PlayResultDto> playResultDtos = List.of(
                new PlayResultDto(3, "carA"),
                new PlayResultDto(2, "carB")
        );
        int playCount = 10;
        playService.savePlayResults(playCount, "carA", playResultDtos);

        PlayHistoryDto playHistoryDto = playService.history().get(0);

        assertThat(playHistoryDto.getWinners()).isEqualTo("carA");
        assertThat(playHistoryDto.getRacingCars())
                .flatExtracting(PlayHistoryDto.RacingCar::getName).containsExactly("carA", "carB");
        assertThat(playHistoryDto.getRacingCars())
                .flatExtracting(PlayHistoryDto.RacingCar::getPosition).containsExactly(3, 2);
    }

}
