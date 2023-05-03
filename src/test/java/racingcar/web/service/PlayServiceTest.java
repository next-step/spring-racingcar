package racingcar.web.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.PlayResult;
import racingcar.web.dao.PlayHistoryDao;
import racingcar.web.dao.PlayHistoryDetailDao;
import racingcar.web.entity.PlayHistory;
import racingcar.web.entity.PlayHistoryDetail;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlayServiceTest {

    @Autowired
    private PlayService playService;
    @Autowired
    private PlayHistoryDao playHistoryDao;
    @Autowired
    private PlayHistoryDetailDao playHistoryDetailDao;

    @Test
    void play() {
        List<PlayResult> playResults = playService.play(new String[]{"carA", "carB", "carC"}, 3);

        assertThat(playResults).isNotNull();
        assertThat(playResults).hasSize(3);
        assertThat(playResults.stream().map(PlayResult::getNameValue)).containsOnly("carA", "carB", "carC");
        assertThat(playResults.stream().map(PlayResult::getPositionValue)).containsOnly(3);
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("playResultsProvider")
    void findWinners(List<PlayResult> playResults, String expected, String displayMessage) {
        assertThat(playService.findWinners(playResults)).isEqualTo(expected);
    }

    @Test
    @Transactional
    void savePlayResults() {
        List<PlayResult> playResults = List.of(
                new PlayResult(3, "carA"),
                new PlayResult(2, "carB")
        );
        int playCount = 10;

        Long playHistoryId = playService.savePlayResults(playResults, playCount);

        PlayHistory playHistory = playHistoryDao.findById(playHistoryId);
        List<PlayHistoryDetail> playHistoryDetails = playHistoryDetailDao.findByPlayHistoryId(playHistoryId);

        assertThat(playHistory.getWinners()).isEqualTo("carA");
        assertThat(playHistory.getTrialCount()).isEqualTo(playCount);
        assertThat(playHistoryDetails.stream().map(PlayHistoryDetail::getName)).containsOnly("carA", "carB");
        assertThat(playHistoryDetails.stream().map(PlayHistoryDetail::getPosition)).containsOnly(3, 2);
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
