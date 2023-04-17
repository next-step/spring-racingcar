package racingcar.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.RacingResultResponse;

import java.util.List;

@Repository
public class QueryDao {
    private final JdbcTemplate jdbcTemplate;

    public QueryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getNextRound() {
        String sql = "Select MAX(round) from racing_history";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class) + 1;
        } catch (NullPointerException exception) {
            return 1;
        }
    }

    public List<RacingResultResponse> getPlayHistory() {
        String sql = "select * from winner_history";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) -> {
                    int round = resultSet.getInt("round");
                    String car_sql = "select name, position from racing_history where round = ?";
                    Cars cars = new Cars(jdbcTemplate.query(
                            car_sql,
                            (resultSet2, rowNumber2) -> {
                                Car car = new Car(
                                        resultSet2.getString("name"),
                                        resultSet2.getInt("position")
                                );
                                return car;
                            },
                            round));
                    return new RacingResultResponse(resultSet.getString("winners"), cars);
                }
        );
    }
}
