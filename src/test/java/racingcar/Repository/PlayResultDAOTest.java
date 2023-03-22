package racingcar.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.model.PlayResult;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlayResultDAOTest {

    @Autowired
    private PlayResultDAO dao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        dao = new PlayResultDAO(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE PLAY_RESULT IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE PLAY_RESULT(" +
            "id INT NOT NULL AUTO_INCREMENT, GROUP_ID INT NOT NULL, TRIAL_COUNT INT NOT NULL"
            + ", winners VARCHAR(50) NOT NULL, created_at DATETIME NOT NULL default current_timestamp)");

        List<Object[]> splitValues = Arrays.asList("1 1 John", "2 10 Jerry", "2 10 Tom",
                "4 923 harry").stream()
            .map(result -> result.split(" "))
            .collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO PLAY_RESULT(RACING_CAR_ID,TRIAL_COUNT, winners) VALUES (?,?,?)",
            splitValues);

    }

    @Test
    void findAllPlayResult() {
        int count = dao.findAllPlayResult().size();

        assertThat(count).isEqualTo(4);
    }

    @Test
    void findPlayResultById() {
        List<PlayResult> result = dao.findPlayResultById(1);
        assertThat(result).hasSize(1);
    }

    @Test
    void insertPlayResult() {
        PlayResult insert = new PlayResult(333, 1, "lars");
        int id = dao.insertPlayResult(insert);

        List<PlayResult> data = dao.findPlayResultByGroupId(333);
        assertThat(data).hasSize(1);
    }

    @Test
    void updatePlayResult() {
        PlayResult insert = new PlayResult(1, 1, "lars");
        dao.insertPlayResult(insert);
        PlayResult update = new PlayResult(1, 123123, 33, "sral");
        dao.updatePlayResult(update);

        List<PlayResult> data = dao.findPlayResultByGroupId(123123);
        assertThat(data).hasSize(1);
    }
}
