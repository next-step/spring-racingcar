package racingcar.game.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.game.domain.PlayerHistory;

@Repository
public class PlayerHistoryDao {

    private final SimpleJdbcInsert jdbcInsert;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<PlayerHistory> playerHistoryRowMapper = (rs, rowNum) ->
        new PlayerHistory(
            rs.getLong("id"),
            rs.getLong("play_result_id"),
            rs.getString("name"),
            rs.getInt("position"),
            rs.getBoolean("is_winner"),
            rs.getTimestamp("created_at").toLocalDateTime());

    public PlayerHistoryDao(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("player_history")
            .usingGeneratedKeyColumns("id", "created_at");
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void saveAll(List<PlayerHistory> playerHistories) {
        BeanPropertySqlParameterSource[] param = playerHistories.stream()
            .map(BeanPropertySqlParameterSource::new)
            .toArray(BeanPropertySqlParameterSource[]::new);
        jdbcInsert.executeBatch(param);
    }

    public List<PlayerHistory> findAll() {
        String sql = "SELECT id, play_result_id, name, position, is_winner, created_at FROM player_history";
        return jdbcTemplate.query(sql, playerHistoryRowMapper);
    }
}
