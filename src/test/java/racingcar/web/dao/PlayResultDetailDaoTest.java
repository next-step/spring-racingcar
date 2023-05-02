package racingcar.web.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.web.entity.PlayResult;
import racingcar.web.entity.PlayResultDetail;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class PlayResultDetailDaoTest {

    @Autowired JdbcTemplate jdbcTemplate;
    PlayResultDetailDao playResultDetailDao;
    PlayResultDao playResultDao;

    @BeforeEach
    void setUp() {
        playResultDetailDao = new PlayResultDetailDao(jdbcTemplate);
        playResultDao = new PlayResultDao(jdbcTemplate);
    }

    @Test
    void insert() {
        Long playResultId = playResultDao.insert(new PlayResult(3, "carA"));

        PlayResultDetail givenPlayResultDetail = new PlayResultDetail(playResultId, "carA", 3);
        Long id = playResultDetailDao.insert(givenPlayResultDetail);

        assertThat(id).isNotNull();

        PlayResultDetail findPlayResultDetail = playResultDetailDao.findById(id);
        assertThat(findPlayResultDetail.getPlayResultId()).isEqualTo(playResultId);
        assertThat(findPlayResultDetail.getName()).isEqualTo("carA");
        assertThat(findPlayResultDetail.getPosition()).isEqualTo(3);
    }
}