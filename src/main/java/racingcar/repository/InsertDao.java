package racingcar.repository;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InsertDao {
    private static SimpleJdbcInsert insertActor;

    public InsertDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    public static PlayResult insertWithMap(PlayResult playResult) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("trial_count", playResult.getCount());
        parameters.put("players", playResult.getRacingCars());
        parameters.put("winners", playResult.getWinners());
        insertActor.execute(parameters);
        return playResult;
    }
}

