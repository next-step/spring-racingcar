package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.PlayResult;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RacingCarDao {

    private final JdbcTemplate jdbcTemplate;
    private static SimpleJdbcInsert insertPlayResult;
    private static SimpleJdbcInsert insertPlayHistory;

    public RacingCarDao(DataSource dataSource) {
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertPlayHistory = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_HISTORY")
                .usingGeneratedKeyColumns("id");

        this.insertPlayResult = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
    }
    public static void setPlayHistory(List<Car> car) {
        for (Car racingCar : car) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", racingCar.getName());
            parameters.put("move", racingCar.getPosition());
            parameters.put("created_at", LocalDateTime.now());
            insertPlayHistory.execute(parameters);
        }
    }

    public static PlayResult getPlayResult(PlayResult playResult) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", playResult.getCount());
        parameters.put("winners", playResult.getWinners().toString());
        parameters.put("created_at", LocalDateTime.now());
        insertPlayResult.execute(parameters);
        return playResult;
    }
}