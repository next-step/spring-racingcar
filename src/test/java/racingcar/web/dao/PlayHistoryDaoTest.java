package racingcar.web.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.dao.PlayHistoryDao;
import racingcar.entity.PlayHistory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@JdbcTest
class PlayHistoryDaoTest {

    @Autowired JdbcTemplate jdbcTemplate;
    PlayHistoryDao playHistoryDao;

    @BeforeEach
    void setUp() {
        playHistoryDao = new PlayHistoryDao(jdbcTemplate);
    }

    @Test
    void save() {
        PlayHistory givenPlayHistory = new PlayHistory(3, "carA");
        Long id = playHistoryDao.save(givenPlayHistory);

        assertThat(id).isNotNull();

        PlayHistory findPlayHistory = assertDoesNotThrow(playHistoryDao.findById(id)::get);
        assertThat(findPlayHistory.getTrialCount()).isEqualTo(3);
        assertThat(findPlayHistory.getWinners()).isEqualTo("carA");
    }

    @Test
    void findAll() {
        List<PlayHistory> givenPlayHistorys = List.of(
                new PlayHistory(1, "carA"),
                new PlayHistory(3, "carB"),
                new PlayHistory(2, "carC")
        );
        for (PlayHistory playHistory : givenPlayHistorys) {
            playHistoryDao.save(playHistory);
        }

        List<PlayHistory> findPlayHistorys = playHistoryDao.findAll();
        assertThat(findPlayHistorys).hasSize(3);
        assertThat(findPlayHistorys).flatExtracting(PlayHistory::getTrialCount).containsExactly(1, 3, 2);
        assertThat(findPlayHistorys).flatExtracting(PlayHistory::getWinners).containsExactly("carA", "carB", "carC");
    }

}