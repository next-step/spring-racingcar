package racingcar.game.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.game.domain.PlayerHistory;
import racingcar.game.domain.PlayerHistoryEntity;
import racingcar.game.domain.PlayerHistoryRepository;

@Repository
public class JdbcPlayerHistoryDao implements PlayerHistoryRepository {

    private final SimpleJdbcInsert jdbcInsert;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<PlayerHistoryEntity> playerHistoryRowMapper = (rs, rowNum) ->
        new PlayerHistoryEntity(
            rs.getLong("id"),
            new PlayerHistory(
                rs.getLong("play_result_id"),
                rs.getString("name"),
                rs.getInt("position"),
                rs.getBoolean("is_winner"),
                rs.getTimestamp("created_at").toLocalDateTime()));

    public JdbcPlayerHistoryDao(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("player_history")
            .usingGeneratedKeyColumns("id", "created_at");
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void saveAll(List<PlayerHistory> playerHistories) {
        BeanPropertySqlParameterSource[] param = playerHistories.stream()
            .map(BeanPropertySqlParameterSource::new)
            .toArray(BeanPropertySqlParameterSource[]::new);
        jdbcInsert.executeBatch(param);
    }

    @Override
    public List<PlayerHistoryEntity> findAll() {
        String sql = "SELECT id, play_result_id, name, position, is_winner, created_at FROM player_history";
        return jdbcTemplate.query(sql, playerHistoryRowMapper);
    }
}
