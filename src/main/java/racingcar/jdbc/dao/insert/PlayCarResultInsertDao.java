package racingcar.jdbc.dao.insert;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.jdbc.PlayCarResult;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PlayCarResultInsertDao {

    private SimpleJdbcInsert insertActor;

    public PlayCarResultInsertDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_CAR_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    public PlayCarResult insert(PlayCarResult playCarResult) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", playCarResult.getName());
        parameters.put("position", playCarResult.getPosition());
        parameters.put("play_result_id", playCarResult.getPlay_result_id());
        parameters.put("created_at", playCarResult.getCreatedAt());

        long id = insertActor.executeAndReturnKey(parameters).longValue();
        return new PlayCarResult(id, playCarResult.getName(), playCarResult.getPosition(), playCarResult.getPlay_result_id(), playCarResult.getCreatedAt());
    }

}
