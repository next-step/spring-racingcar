package racingcar.web.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.web.entity.PlayResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class PlayResultDaoTest {

    @Autowired JdbcTemplate jdbcTemplate;
    PlayResultDao playResultDao;

    @BeforeEach
    void setUp() {
        playResultDao = new PlayResultDao(jdbcTemplate);
    }

    @Test
    void insert() {
        PlayResult givenPlayResult = new PlayResult(3, "carA");
        Long id = playResultDao.insert(givenPlayResult);

        assertThat(id).isNotNull();

        PlayResult findPlayResult = playResultDao.findById(id);
        assertThat(findPlayResult.getTrialCount()).isEqualTo(3);
        assertThat(findPlayResult.getWinners()).isEqualTo("carA");
    }
}