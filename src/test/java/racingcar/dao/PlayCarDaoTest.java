package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.dto.RacingCarResultDto;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PlayCarDaoTest {

    @Autowired
    private PlayCarDao playCarDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {

        jdbcTemplate.execute("delete from PLAY_HISTORY");
        jdbcTemplate.execute("delete from PLAY_CARS_HISTORY");
        jdbcTemplate.execute("delete from PLAY_CARS_HISTORY");
    }

    @Test
    void insertPlayCarHistory() {

        int id = playCarDao.insertPlayhistory(5);

        assertThat(id)
                .isEqualTo(1);
    }

    @Test
    void insertCarHistory() {

        Car car = new Car("lucas", 5);

        playCarDao.insertPlayCarHistory(1, car);
    }

    @Test
    void insertCarResult() {
        playCarDao.insertPlayResult(1, "lucas");
    }

    @Test
    void getPlayDtoTest() {
        Car car = new Car("lucas", 5);

        playCarDao.insertPlayCarHistory(1, car);

        List<Car> actual = playCarDao.getPlayDto(1);

        assertThat(actual)
                .hasSize(1)
                .containsAnyElementsOf(Arrays.asList(car));

    }

    @Test
    void getAllCarResultDto() {
        List<Car> cars = Arrays.asList(new Car("lucas", 2)
                , new Car("cyan", 2));

        int id = playCarDao.insertPlayhistory(3);

        cars.stream().forEach(car -> playCarDao.insertPlayCarHistory(id, car));

        playCarDao.insertPlayResult(id, "lucas");

        List<RacingCarResultDto> list = playCarDao.getAllRacingResult();

        assertThat(list).hasSize(1);

    }
}
