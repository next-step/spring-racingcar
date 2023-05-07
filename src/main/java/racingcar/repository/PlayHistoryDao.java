package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayHistory;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<PlayHistory> findByHistoryById(List<Integer> playResultIds) {
        if (playResultIds.isEmpty()) {
            return null;
        }

        String sql = "SELECT * " +
                "FROM PLAY_HISTORY " +
                "WHERE PLAY_RESULT_ID " +
                "IN (" + String.join(", ", playResultIds.stream().map(id -> id.toString()).collect(Collectors.toList())) + ")";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PlayHistory playHistory = new PlayHistory();

            playHistory.setPlayResultId(rs.getInt("PLAY_RESULT_ID"));
            playHistory.setName(rs.getString("NAME"));
            playHistory.setPosition(rs.getInt("POSITION"));

            return playHistory;
        });
    }
}
