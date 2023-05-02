package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

@Repository
public class PlayHistoryDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayHistoryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Integer playResultId, RacingCars racingCars) {
        String sql = "INSERT INTO PLAY_HISTORY (PLAY_RESULT_ID, NAME, POSITION) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                racingCars.getRacingCars(),
                racingCars.getRacingCars().size(),
                (PreparedStatement ps, RacingCar racingCar) -> {
                    ps.setInt(1, playResultId);
                    ps.setString(2, racingCar.getName());
                    ps.setInt(3, racingCar.getPosition());
                });
    }
}
