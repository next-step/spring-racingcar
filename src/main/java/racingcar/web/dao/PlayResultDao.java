package racingcar.web.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.web.entity.PlayResult;

import java.sql.PreparedStatement;

@Repository
public class PlayResultDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PlayResult findById(Long id) {
        String sql = "SELECT * FROM play_result WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, playResultRowMapper(), id);
    }

    public Long insert(PlayResult playResult) {
        String sql = "INSERT INTO PLAY_RESULT (trial_count, winners) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, playResult.getTrialCount());
            ps.setString(2, playResult.getWinners());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private RowMapper<PlayResult> playResultRowMapper() {
        return (resultSet, rowNum) -> new PlayResult(
                resultSet.getLong("id"),
                resultSet.getInt("trial_count"),
                resultSet.getString("winners"),
                resultSet.getTimestamp("played_at").toLocalDateTime()
        );
    }

}
