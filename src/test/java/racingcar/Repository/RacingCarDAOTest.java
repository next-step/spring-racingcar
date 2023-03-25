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
import racingcar.model.RacingCar;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RacingCarDAOTest {

    @Autowired
    private RacingCarDAO dao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        dao = new RacingCarDAO(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE RACING_CAR IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE RACING_CAR(" +
            "id INT NOT NULL AUTO_INCREMENT, GROUP_ID INT NOT NULL, PERSON_ID INT NOT NULL, NAME VARCHAR(50) NOT NULL"
            + ", POSITION INT NOT NULL DEFAULT 0, created_at DATETIME NOT NULL default current_timestamp)");

        List<Object[]> splitValues = Arrays.asList("1 1 John 10", "1 2 Jerry 15", "1 3 Tom 12",
                "2 4 harry 999").stream()
            .map(result -> result.split(" "))
            .collect(Collectors.toList());

        jdbcTemplate.batchUpdate(
            "INSERT INTO RACING_CAR(GROUP_ID, PERSON_ID,NAME, POSITION) VALUES (?,?,?,?)",
            splitValues);

    }

    @Test
    void findAllRacingCar() {
        int count = dao.findAllRacingCar().size();

        assertThat(count).isEqualTo(4);
    }

    @Test
    void findRacingCarById() {
        List<RacingCar> result = dao.findRacingCarById(1);
        assertThat(result).hasSize(1);
    }

    @Test
    void findRacingCarByPlayResultId() {
    }

    @Test
    void insertRacingCar() {
        RacingCar insert = new RacingCar(1, 1, "John", 30);
        int id = dao.insertRacingCar(insert);

        List<RacingCar> data = dao.findRacingCarById(id);
        assertThat(data).hasSize(1);
    }

    @Test
    void updateRacingCar() {
        RacingCar insert = new RacingCar(33, 1, "John", 30);
        dao.insertRacingCar(insert);
        RacingCar update = new RacingCar(5, 33, 123, "Joooooohn", 1);
        dao.updateRacingCar(update);

        List<RacingCar> data = dao.findRacingCarByGroupId(33);
        assertThat(data.get(0).getPersonId()).isEqualTo(123);
    }

    @Test
    void getGroupIdTest() {
        int id = dao.getGroupId();
        assertThat(id).isEqualTo(3);
    }
}
