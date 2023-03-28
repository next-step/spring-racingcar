package racingcar.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.RacingCar;

import java.util.List;

@Repository
public class RacingCarDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public RacingCarDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int racingInfoInsert(int count, String winners) {

        String sql = "SELECT NVL(MAX(ID), 0) + 1 AS NEXT_ID FROM RACING_INFO";
        int nextID = jdbcTemplate.queryForObject(sql, Integer.class);

        jdbcTemplate.update("INSERT INTO RACING_INFO(ID, TRIAL_COUNT, WINNERS) VALUES (?,?,?)", nextID, count, winners);

        return nextID;
    }

    public void racingPlayerInsert(int nextID, List<RacingCar> racingCars) {

        for (RacingCar racingCar : racingCars) {
            jdbcTemplate.update("INSERT INTO RACING_PLAYER(ID, NAME, POSITION) VALUES(?, ?, ?)", nextID, racingCar.getName(), racingCar.getPosition());
        }

    }
}
