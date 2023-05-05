package racingcar.game.dao;


import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.game.domain.PlayResult;

@Repository
public class PlayResultDao {

    private final SimpleJdbcInsert jdbcInsert;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<PlayResult> playResultRowMapper = (rs, rowNum) ->
        new PlayResult(
            rs.getLong("id"),
            rs.getInt("trial_count"),
            rs.getTimestamp("created_at").toLocalDateTime());

    public PlayResultDao(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("play_result")
            .usingGeneratedKeyColumns("id", "created_at");
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Long save(PlayResult playerResult) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(playerResult);
        return (Long) jdbcInsert.executeAndReturnKeyHolder(param)
            .getKeys()
            .get("id");
    }

    public List<PlayResult> findAll() {
        String sql = "SELECT id, trial_count, created_at FROM play_result";
        return jdbcTemplate.query(sql, playResultRowMapper);
    }
}
