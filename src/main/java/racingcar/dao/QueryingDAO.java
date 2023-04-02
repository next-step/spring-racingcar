package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.Car;
import racingcar.Position;
import racingcar.repository.ResultRacing;

import java.util.List;

@Repository
public class QueryingDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ResultRacing> findHistory() {
        String sql = "select round, max(winners) as winner from PLAY_RESULT group by round order by round";

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    ResultRacing resultRacing = new ResultRacing(
                            resultSet.getString("winner"),
                            findRoundPlayer(resultSet.getInt("round"))
                    );
                    return resultRacing;
                }
        );
    }

    public List<Car> findRoundPlayer(int round) {
        String sql = "select name, position from PLAY_RESULT where round = ?";

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    Car car = new Car(
                            resultSet.getString("name"),
                            new Position(resultSet.getInt("position"))
                    );
                    return car;
                }, round);
    }

    public int getRoundNumber() {
        String sql = "select nvl(max(round), 0) + 1 from PLAY_RESULT";

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
