package racingcar.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.data.entity.PlayHistory;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PlayHistoryRepositoryImpl implements PlayHistoryRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public PlayHistoryRepositoryImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_HISTORY")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void insertHistory(List<PlayHistory> playHistories) {
        LocalDateTime now = LocalDateTime.now();
        List<Map<String, Object>> histories = playHistories.stream().map(racingCar -> {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("play_result_id", racingCar.getPlayResultId());
            parameters.put("player_name", racingCar.getPlayerName());
            parameters.put("position", racingCar.getPosition());
            parameters.put("created_at", now);
            return parameters;
        }).collect(Collectors.toList());
        simpleJdbcInsert.executeBatch(SqlParameterSourceUtils.createBatch(histories));
    }

    @Override
    public List<PlayHistory> getPlayHistoriesByPlayResultId(long playResultId) {
        JdbcTemplate jdbcTemplate = simpleJdbcInsert.getJdbcTemplate();
        String sql = "select id, play_result_id, player_name, position, " +
                "from PLAY_HISTORY " +
                "where play_result_id = ?";
        return jdbcTemplate.query(sql, new Object[]{playResultId}, (rs, rowNum) -> {
            long id = rs.getLong("id");
            long playResultId1 = rs.getLong("play_result_id");
            String playerName = rs.getString("player_name");
            long position = rs.getLong("position");
            return new PlayHistory(id, playResultId1, playerName, position);
        });
    }
}
