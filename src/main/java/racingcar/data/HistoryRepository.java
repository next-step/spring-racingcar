package racingcar.data;

import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.RacingCars;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class HistoryRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public HistoryRepository(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_HISTORY")
                .usingGeneratedKeyColumns("id");
    }

    public void insertHistory(long gameResultId, RacingCars cars) {
        LocalDateTime now = LocalDateTime.now();
        List<Map<String, Object>> histories = cars.getCars().stream().map(racingCar -> {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("play_result_id", gameResultId);
            parameters.put("player_name", racingCar.getName());
            parameters.put("position", racingCar.getPosition());
            parameters.put("created_at", now);
            return parameters;
        }).collect(Collectors.toList());
        simpleJdbcInsert.executeBatch(SqlParameterSourceUtils.createBatch(histories));
    }
}
