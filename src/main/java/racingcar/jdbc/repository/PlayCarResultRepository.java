package racingcar.jdbc.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.jdbc.PlayCarResult;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayCarResultRepository {

    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public PlayCarResultRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_CAR_RESULT")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayCarResult> actorRowMapper =
            (resultSet, rowNum) -> new PlayCarResult(resultSet.getString("name"), resultSet.getInt("position"));



    public PlayCarResult insert(PlayCarResult playCarResult) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", playCarResult.getName());
        parameters.put("position", playCarResult.getPosition());
        parameters.put("play_result_id", playCarResult.getPlayResultId());
        parameters.put("created_at", playCarResult.getCreatedAt());
        long id = insertActor.executeAndReturnKey(parameters).longValue();
        return new PlayCarResult(id, playCarResult.getName(), playCarResult.getPosition(), playCarResult.getPlayResultId(), playCarResult.getCreatedAt());
    }

    public List<PlayCarResult> findByPlayResultId(long playResultId) {
        String sql = "select * from play_car_result where play_result_id= ?";
        return jdbcTemplate.query(sql, actorRowMapper, playResultId);
    }

}
