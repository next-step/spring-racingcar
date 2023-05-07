package racingcar.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.data.entity.PlayResult;
import racingcar.presentation.dto.GamePlayHistory;
import racingcar.presentation.dto.PlayHistory;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PlayResultRepositoryImpl implements PlayResultRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public PlayResultRepositoryImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public long insertGameResult(PlayResult playResult) {
        if (playResult.getWinners() == null || playResult.getWinners().isEmpty()) {
            throw new RuntimeException("winners 가 비어있습니다.");
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("winners", playResult.getWinners());
        parameters.put("created_at", LocalDateTime.now());
        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    @Override
    public List<PlayResult> getPlayResults() {
        String sql = "select id, winners, created_at " +
                "from PLAY_RESULT " +
                "order by created_at asc";
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String winners = rs.getString("winners");
            return new PlayResult(id, winners);
        });
    }
}
