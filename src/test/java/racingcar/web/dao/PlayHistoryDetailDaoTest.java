package racingcar.web.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.dao.PlayHistoryDao;
import racingcar.entity.dao.PlayHistoryDetailDao;
import racingcar.entity.PlayHistory;
import racingcar.entity.PlayHistoryDetail;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@JdbcTest
class PlayHistoryDetailDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    PlayHistoryDetailDao playHistoryDetailDao;
    PlayHistoryDao playHistoryDao;

    @BeforeEach
    void setUp() {
        playHistoryDetailDao = new PlayHistoryDetailDao(jdbcTemplate);
        playHistoryDao = new PlayHistoryDao(jdbcTemplate);
    }

    @Test
    void save() {
        Long playHistoryId = playHistoryDao.save(new PlayHistory(3, "carA"));

        PlayHistoryDetail givenPlayHistoryDetail = new PlayHistoryDetail(playHistoryId, "carA", 3);
        Long id = playHistoryDetailDao.save(givenPlayHistoryDetail);

        assertThat(id).isNotNull();

        PlayHistoryDetail findPlayHistoryDetail = assertDoesNotThrow(playHistoryDetailDao.findById(id)::get);
        assertThat(findPlayHistoryDetail.getPlayHistoryId()).isEqualTo(playHistoryId);
        assertThat(findPlayHistoryDetail.getName()).isEqualTo("carA");
        assertThat(findPlayHistoryDetail.getPosition()).isEqualTo(3);
    }

    @Test
    void findByPlayHistoryId() {
        Long playHistoryId = playHistoryDao.save(new PlayHistory(3, "carA"));

        List<PlayHistoryDetail> givenPlayHistoryDetails = List.of(
                new PlayHistoryDetail(playHistoryId, "carA", 3),
                new PlayHistoryDetail(playHistoryId, "carB", 2)
        );
        for (PlayHistoryDetail playHistoryDetail : givenPlayHistoryDetails) {
            playHistoryDetailDao.save(playHistoryDetail);
        }

        List<PlayHistoryDetail> findPlayHistoryDetails = playHistoryDetailDao.findByPlayHistoryId(playHistoryId);
        assertThat(findPlayHistoryDetails).hasSize(2);
        assertThat(findPlayHistoryDetails).flatExtracting(PlayHistoryDetail::getName).containsExactly("carA", "carB");
        assertThat(findPlayHistoryDetails).flatExtracting(PlayHistoryDetail::getPosition).containsExactly(3, 2);

    }
}