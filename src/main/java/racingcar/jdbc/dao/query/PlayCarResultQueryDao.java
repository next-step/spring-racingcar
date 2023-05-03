package racingcar.jdbc.dao.query;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.jdbc.PlayCarResult;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlayCarResultQueryDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PlayCarResult> actorRowMapper = (resultSet, rowNum) -> {
        PlayCarResult playCarResult = new PlayCarResult(
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
        return playCarResult;
    };

    public List<PlayCarResult> findByPlayResultId(long playResultId) {
        String sql = "select name, position from play_car_result where play_result_id= ?";
        return jdbcTemplate.query(sql, actorRowMapper, playResultId);
    }
}
