package racingcar.web.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.web.entity.PlayHistory;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PlayHistoryDao {

    private final JdbcTemplate jdbcTemplate;

    public Optional<PlayHistory> findById(Long id) {
        String sql = "SELECT * FROM play_history WHERE ID = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, playHistoryRowMapper(), id));
    }

    public List<PlayHistory> findAll() {
        String sql = "SELECT * FROM play_history";

        return jdbcTemplate.query(sql, playHistoryRowMapper());
    }

    public Long save(PlayHistory playHistory) {
        String sql = "INSERT INTO play_history (trial_count, winners) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, playHistory.getTrialCount());
            ps.setString(2, playHistory.getWinners());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private RowMapper<PlayHistory> playHistoryRowMapper() {
        return (resultSet, rowNum) -> new PlayHistory(
                resultSet.getLong("id"),
                resultSet.getInt("trial_count"),
                resultSet.getString("winners"),
                resultSet.getTimestamp("played_at").toLocalDateTime()
        );
    }

}
