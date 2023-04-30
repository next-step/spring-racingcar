package racingcar.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;

@Repository
public class PlayAllResultDAO {
    private SimpleJdbcInsert insertActor;

    public PlayAllResultDAO(DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_ALL_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(int newId, String player, int position) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("play_result_id", newId);
        parameters.put("player", player);
        parameters.put("position", position);
        parameters.put("created_at", LocalDateTime.now());
        return insertActor.executeAndReturnKey(parameters).intValue();
    }
}
