package racingcar.web.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.web.entity.PlayHistory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    void insert() {
        PlayHistory givenPlayHistory = new PlayHistory(3, "carA");
        Long id = playHistoryDao.insert(givenPlayHistory);

        assertThat(id).isNotNull();

        PlayHistory findPlayHistory = assertDoesNotThrow(playHistoryDao.findById(id)::get);
        assertThat(findPlayHistory.getTrialCount()).isEqualTo(3);
        assertThat(findPlayHistory.getWinners()).isEqualTo("carA");
    }
}