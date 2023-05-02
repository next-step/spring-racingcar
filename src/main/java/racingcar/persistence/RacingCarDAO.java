package racingcar.persistence;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.RacingCar;
import racingcar.domain.repository.RacingCarRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;

@Repository
public class RacingCarDAO implements RacingCarRepository {
    private SimpleJdbcInsert insertActor;

    public RacingCarDAO(DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("RACING_CAR")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(RacingCar racingCar) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("play_result_id", racingCar.getPlayResultId());
        parameters.put("player", racingCar.getName());
        parameters.put("position", racingCar.getPosition());
        parameters.put("created_at", LocalDateTime.now());
        return insertActor.executeAndReturnKey(parameters).intValue();
    }
}
