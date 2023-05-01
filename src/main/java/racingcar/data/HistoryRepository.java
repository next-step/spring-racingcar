package racingcar.data;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.RacingCars;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class HistoryRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public HistoryRepository(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_HISTORY")
                .usingGeneratedKeyColumns("id");
    }

    public int insertHistory(long gameResultId, RacingCars cars) {
        AtomicInteger insertCount = new AtomicInteger();
        cars.getCars().forEach(racingCar -> {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("play_result_id", gameResultId);
            parameters.put("player_name", racingCar.getName());
            parameters.put("position", racingCar.getPosition());
            parameters.put("created_at", LocalDateTime.now());

            if (simpleJdbcInsert.execute(parameters) == 1) {
                insertCount.incrementAndGet();
            }
        });
        return insertCount.get();
    }
}
