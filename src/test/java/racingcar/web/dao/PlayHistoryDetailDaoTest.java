package racingcar.web.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.web.entity.PlayHistory;
import racingcar.web.entity.PlayHistoryDetail;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@JdbcTest
class PlayHistoryDetailDaoTest {

    @Autowired JdbcTemplate jdbcTemplate;
    PlayHistoryDetailDao playHistoryDetailDao;
    PlayHistoryDao playHistoryDao;

    @BeforeEach
    void setUp() {
        playHistoryDetailDao = new PlayHistoryDetailDao(jdbcTemplate);
        playHistoryDao = new PlayHistoryDao(jdbcTemplate);
    }

    @Test
    void insert() {
        Long playHistoryId = playHistoryDao.insert(new PlayHistory(3, "carA"));

        PlayHistoryDetail givenPlayHistoryDetail = new PlayHistoryDetail(playHistoryId, "carA", 3);
        Long id = playHistoryDetailDao.insert(givenPlayHistoryDetail);

        assertThat(id).isNotNull();

        PlayHistoryDetail findPlayHistoryDetail = assertDoesNotThrow(playHistoryDetailDao.findById(id)::get);
        assertThat(findPlayHistoryDetail.getPlayHistoryId()).isEqualTo(playHistoryId);
        assertThat(findPlayHistoryDetail.getName()).isEqualTo("carA");
        assertThat(findPlayHistoryDetail.getPosition()).isEqualTo(3);
    }
}