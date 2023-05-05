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
import racingcar.game.domain.PlayResultEntity;
import racingcar.game.domain.PlayResultRepository;

@Repository
public class JdbcPlayResultDao implements PlayResultRepository {

    private final SimpleJdbcInsert jdbcInsert;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<PlayResultEntity> playResultRowMapper = (rs, rowNum) ->
        new PlayResultEntity(
            rs.getLong("id"),
            new PlayResult(
                rs.getInt("trial_count"),
                rs.getTimestamp("created_at").toLocalDateTime()));

    public JdbcPlayResultDao(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("play_result")
            .usingGeneratedKeyColumns("id", "created_at");
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long save(PlayResult playerResult) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(playerResult);
        return (Long) jdbcInsert.executeAndReturnKeyHolder(param)
            .getKeys()
            .get("id");
    }

    @Override
    public List<PlayResultEntity> findAll() {
        String sql = "SELECT id, trial_count, created_at FROM play_result";
        return jdbcTemplate.query(sql, playResultRowMapper);
    }
}
